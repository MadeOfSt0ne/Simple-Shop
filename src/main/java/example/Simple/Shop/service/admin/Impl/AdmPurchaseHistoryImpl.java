package example.Simple.Shop.service.admin.Impl;

import example.Simple.Shop.mappers.PurchaseMapper;
import example.Simple.Shop.model.purchase.Purchase;
import example.Simple.Shop.model.purchase.dto.PurchaseDto;
import example.Simple.Shop.repository.PurchaseRepository;
import example.Simple.Shop.service.admin.AdminPurchaseHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdmPurchaseHistoryImpl implements AdminPurchaseHistory {

    private final PurchaseRepository purchaseRepo;

    @Override
    public List<PurchaseDto> viewHistory(Long userId, int from, int size) {
        Pageable pageable = PageRequest.of(from, size);
        List<Purchase> purchases = purchaseRepo.getPurchasesByBuyerId(userId, pageable);
        return purchases.stream()
                .map(PurchaseMapper::toPurchaseDto)
                .collect(Collectors.toList());
    }
}
