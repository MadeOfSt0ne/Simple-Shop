package example.Simple.Shop.service.user;

import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.purchase.Purchase;
import example.Simple.Shop.model.purchase.dto.SelfPurchaseDto;
import example.Simple.Shop.model.user.User;

import java.util.List;

public interface PurchaseHistoryService {

    List<SelfPurchaseDto> viewHistory(Long userId, int from, int size);

    void saveToPurchaseHistory(int amount, User buyer, Product product, Organization organization);

    Purchase getPurchaseById(Long purchaseId);

    void deleteById(Long purchaseId);

}
