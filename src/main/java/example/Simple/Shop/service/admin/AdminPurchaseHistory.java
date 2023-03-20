package example.Simple.Shop.service.admin;

import example.Simple.Shop.model.purchase.dto.PurchaseDto;

import java.util.List;

public interface AdminPurchaseHistory {

    List<PurchaseDto> viewHistory(Long userId, int from, int size);
}
