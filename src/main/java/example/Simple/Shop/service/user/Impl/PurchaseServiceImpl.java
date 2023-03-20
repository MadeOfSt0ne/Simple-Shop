package example.Simple.Shop.service.user.Impl;

import example.Simple.Shop.exception.InsufficientAmountException;
import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.purchase.Purchase;
import example.Simple.Shop.model.user.User;
import example.Simple.Shop.repository.OrganizationRepository;
import example.Simple.Shop.repository.ProductRepository;
import example.Simple.Shop.repository.PurchaseRepository;
import example.Simple.Shop.repository.UserRepository;
import example.Simple.Shop.service.user.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Port;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final UserRepository userRepo;
    private final ProductRepository productRepo;
    private final OrganizationRepository organizationRepo;
    private final PurchaseRepository purchaseRepo;

    private final static BigDecimal SALES_COMMISSION = new BigDecimal("0.05");

    @Override
    public void purchaseProduct(Long buyerId, Long productId, int amount) {
        User buyer = userRepo.getReferenceById(buyerId);
        Product product = productRepo.getReferenceById(productId);
        Organization organization = organizationRepo.getReferenceById(product.getOrganization().getId());
        User seller = userRepo.getReferenceById(organization.getOwner().getId());

        if (buyer.getBalance()
                .compareTo(product
                        .getPrice()
                        .multiply(BigDecimal.valueOf(amount))) < 0) {
            throw new InsufficientAmountException("Недостаточно средств для покупки: " + buyer.getBalance());
        }
        if (product.getWarehouseAmount() >= amount) {
            throw new InsufficientAmountException("Недостаточно товара на складе: " + product.getWarehouseAmount());
        }

        BigDecimal newBuyerBalance = buyer.getBalance()
                .subtract(product.getPrice().multiply(BigDecimal.valueOf(amount)));

        BigDecimal shopCommission = product.getPrice()
                .multiply(BigDecimal.valueOf(amount))
                .multiply(SALES_COMMISSION);

        BigDecimal sellersProfit = product.getPrice()
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

        saveToPurchaseHistory(amount, buyer, product, organization);
    }

    private void saveToPurchaseHistory(int amount, User buyer, Product product, Organization organization) {
        Purchase purchase = new Purchase();
        purchase.setProduct(product);
        purchase.setSeller(organization);
        purchase.setBuyer(buyer);
        purchase.setPrice(product.getPrice());
        purchase.setBuyTime(LocalDateTime.now());
        purchase.setAmount(amount);
        purchaseRepo.save(purchase);
    }

    @Override
    public void refund(Long purchaseId) {
        Purchase purchase = purchaseRepo.getReferenceById(purchaseId);
        if (LocalDateTime.now().isAfter(purchase.getBuyTime().plusDays(1))) {
            throw new IllegalStateException("Вернуть товар можно только в течение суток с момента покупки");
        }
        User buyer = userRepo.getReferenceById(purchase.getBuyer().getId());
        User seller = userRepo.getReferenceById(purchase.getSeller().getOwner().getId());
        Product product = productRepo.getReferenceById(purchase.getProduct().getId());

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
    }
}
