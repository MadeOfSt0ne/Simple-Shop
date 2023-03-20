package example.Simple.Shop.service.user;

import example.Simple.Shop.model.purchase.dto.SelfPurchaseDto;

import java.util.List;

public interface PurchaseHistoryService {

    List<SelfPurchaseDto> viewHistory(Long userId, int from, int size);
}
