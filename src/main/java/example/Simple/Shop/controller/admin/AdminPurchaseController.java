package example.Simple.Shop.controller.admin;

import example.Simple.Shop.model.purchase.dto.PurchaseDto;
import example.Simple.Shop.service.admin.AdminPurchaseHistory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/admin/purchases")
@RequiredArgsConstructor
public class AdminPurchaseController {

    private final AdminPurchaseHistory service;

    @GetMapping("/{id}")
    public List<PurchaseDto> getHistoryForUser(@PathVariable(name = "id") Long userId,
                                   @RequestParam(value = "from", required = false, defaultValue = "0") int from,
                                   @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        log.info("Get purchase history for user {}", userId);
        return service.viewHistory(userId, from, size);
    }
}
