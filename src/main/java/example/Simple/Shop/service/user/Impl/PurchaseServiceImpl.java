package example.Simple.Shop.service.user.Impl;

import example.Simple.Shop.exception.AccessDeniedException;
import example.Simple.Shop.exception.InsufficientAmountException;
import example.Simple.Shop.model.discount.Discount;
import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.purchase.Purchase;
import example.Simple.Shop.model.user.User;
import example.Simple.Shop.repository.OrganizationRepository;
import example.Simple.Shop.repository.ProductRepository;
import example.Simple.Shop.repository.UserRepository;
import example.Simple.Shop.service.user.PurchaseHistoryService;
import example.Simple.Shop.service.user.PurchaseService;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@PersistenceContext(type = PersistenceContextType.EXTENDED)
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final UserRepository userRepo;
    private final ProductRepository productRepo;
    private final OrganizationRepository organizationRepo;
    private final PurchaseHistoryService historyService;

    private final static BigDecimal SALES_COMMISSION = new BigDecimal("0.05");

    /**
     * Совершение покупки с сохранением покупки в историю
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void purchaseProduct(Long buyerId, Long productId, int amount) {
        User buyer = userRepo.getUserById(buyerId);
        Product product = productRepo.getProductById(productId);
        Organization organization = organizationRepo.getOrganizationById(product.getOrganization().getId());
        User seller = userRepo.getUserById(organization.getOwner().getId());

        if (buyer.getBalance()
                .compareTo(product
                        .getPrice()
                        .multiply(BigDecimal.valueOf(amount))) < 0) {
            throw new InsufficientAmountException("Недостаточно средств для покупки: " + buyer.getBalance());
        }
        if (product.getWarehouseAmount() < amount) {
            throw new InsufficientAmountException("Недостаточно товара на складе: " + product.getWarehouseAmount());
        }

        checkBlocks(buyer, product, organization);

        BigDecimal price = product.getPrice();
        if (product.getDiscounts().size() > 0) {
            List<Discount> discounts = product.getDiscounts();
            double discountValue = discounts.stream()
                   .filter(d -> (d.getStart().isBefore(LocalDate.now()) && d.getEnd().isAfter(LocalDate.now())))
                   .findFirst()
                   .map(Discount::getValue)
                   .orElse(0.0);

                    double priceModifier = 1 - discountValue;
                    price = price.multiply(BigDecimal.valueOf(priceModifier));
        }

        BigDecimal newBuyerBalance = buyer.getBalance()
                .subtract(price.multiply(BigDecimal.valueOf(amount)));

        BigDecimal shopCommission = price
                .multiply(BigDecimal.valueOf(amount))
                .multiply(SALES_COMMISSION);

        BigDecimal sellersProfit = price
                .multiply(BigDecimal.valueOf(amount))
                .subtract(shopCommission);

        BigDecimal newSellerBalance = seller.getBalance()
                .add(sellersProfit);

        buyer.setBalance(newBuyerBalance);
        userRepo.save(buyer);

        seller.setBalance(newSellerBalance);
        userRepo.save(seller);

        product.setWarehouseAmount(product.getWarehouseAmount() - amount);
        productRepo.save(product);

        historyService.saveToPurchaseHistory(amount, buyer, product, organization);
    }

    /**
     * Проверка блокировок
     */
    private void checkBlocks(User buyer, Product product, Organization organization) {
        if (buyer.isBlocked()) {
            throw new AccessDeniedException("Пользователь заблокирован и не может совершить покупку");
        }
        if (product.isBlocked()) {
            throw new AccessDeniedException("Продукт заблокирован и не может быть куплен");
        }
        if (organization.isBlocked()) {
            throw new AccessDeniedException("Организация заблокирована и не может совершить продажу");
        }
    }

    /**
     * Совершение возврата с удалением покупки из истории
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void refund(Long purchaseId) {

        Purchase purchase = historyService.getPurchaseById(purchaseId);

        if (LocalDateTime.now().isAfter(purchase.getBuyTime().plusDays(1))) {
            throw new IllegalStateException("Вернуть товар можно только в течение суток с момента покупки");
        }
        User buyer = userRepo.getUserById(purchase.getBuyer().getId());
        User seller = userRepo.getUserById(purchase.getSeller().getOwner().getId());
        Product product = productRepo.getProductById(purchase.getProduct().getId());

        // Для упрощения не будем рассматривать ситуацию, когда продавец уже потратил заработанные средства и не
        // может сделать возврат средств. Будем считать, что продавец может уйти в "минус" в таком случае.
        /*if (seller.getBalance()
                .compareTo(purchase
                        .getPrice()
                        .multiply(BigDecimal.valueOf(purchase.getAmount()))) < 0) {
            throw new InsufficientAmountException("Недостаточно средств для возврата: " + seller.getBalance());
        }*/

        BigDecimal shopCommission = product.getPrice()
                .multiply(BigDecimal.valueOf(purchase.getAmount()))
                .multiply(SALES_COMMISSION);

        BigDecimal moneyToRefund = purchase.getPrice()
                .multiply(BigDecimal.valueOf(purchase.getAmount()))
                .subtract(shopCommission);

        BigDecimal sellerBalance = seller.getBalance().subtract(moneyToRefund);
            seller.setBalance(sellerBalance);
        userRepo.save(seller);

        BigDecimal buyerBalance = buyer.getBalance().add(moneyToRefund);
            buyer.setBalance(buyerBalance);
        userRepo.save(buyer);

        product.setWarehouseAmount(product.getWarehouseAmount() + purchase.getAmount());
        productRepo.save(product);

        historyService.deleteById(purchaseId);
    }
}
