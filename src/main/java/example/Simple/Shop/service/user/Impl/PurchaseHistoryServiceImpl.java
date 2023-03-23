package example.Simple.Shop.service.user.Impl;

import example.Simple.Shop.mappers.PurchaseMapper;
import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.purchase.Purchase;
import example.Simple.Shop.model.purchase.dto.SelfPurchaseDto;
import example.Simple.Shop.model.user.User;
import example.Simple.Shop.repository.PurchaseRepository;
import example.Simple.Shop.service.user.PurchaseHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService {

    private final PurchaseRepository purchaseRepo;

    /**
     * Просмотр истории покупок (с постраничным отображением)
     * @param userId идентификатор пользователя
     */
    @Override
    public List<SelfPurchaseDto> viewHistory(Long userId, int from, int size) {
        Pageable pageable = PageRequest.of(from, size);
        List<Purchase> purchases = purchaseRepo.getPurchasesByBuyerId(userId, pageable);
        return purchases.stream()
                .map(PurchaseMapper::toSelfPurchaseDto)
                .collect(Collectors.toList());
    }

    /**
     * Сохранение покупки в историю
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveToPurchaseHistory(int amount, User buyer, Product product, Organization organization) {
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Purchase getPurchaseById(Long purchaseId) {
        return purchaseRepo.getPurchaseById(purchaseId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteById(Long purchaseId) {
        purchaseRepo.deleteById(purchaseId);
    }
}
