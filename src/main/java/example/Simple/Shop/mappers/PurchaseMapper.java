package example.Simple.Shop.mappers;

import example.Simple.Shop.model.purchase.Purchase;
import example.Simple.Shop.model.purchase.dto.PurchaseDto;
import example.Simple.Shop.model.purchase.dto.SelfPurchaseDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PurchaseMapper {

    public static SelfPurchaseDto toSelfPurchaseDto(Purchase purchase) {
        return SelfPurchaseDto.builder()
                .purchaseId(purchase.getId())
                .product(new SelfPurchaseDto.Product(
                        purchase.getProduct().getId(),
                        purchase.getProduct().getName()))
                .organization(new SelfPurchaseDto.Organization(
                        purchase.getSeller().getId(),
                        purchase.getSeller().getName()))
                .price(purchase.getPrice())
                .buyTime(purchase.getBuyTime())
                .build();
    }

    public static PurchaseDto toPurchaseDto(Purchase purchase) {
        return PurchaseDto.builder()
                .purchaseId(purchase.getId())
                .product(new PurchaseDto.Product(
                        purchase.getProduct().getId(),
                        purchase.getProduct().getName()))
                .organization(new PurchaseDto.Organization(
                        purchase.getSeller().getId(),
                        purchase.getSeller().getName(),
                        purchase.getSeller().getOwner().getId()))
                .buyer(new PurchaseDto.Buyer(
                        purchase.getBuyer().getId(),
                        purchase.getBuyer().getUsername()))
                .price(purchase.getPrice())
                .buyTime(purchase.getBuyTime())
                .build();
    }
}
