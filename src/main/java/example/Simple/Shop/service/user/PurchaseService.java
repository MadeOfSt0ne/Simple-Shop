package example.Simple.Shop.service.user;

public interface PurchaseService {

    void purchaseProduct(Long buyerId, Long productId, int amount);
    void refund(Long purchaseId);
}
