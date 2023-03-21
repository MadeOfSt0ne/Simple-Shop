package example.Simple.Shop.controller.user;

import example.Simple.Shop.model.purchase.dto.SelfPurchaseDto;
import example.Simple.Shop.service.user.PurchaseHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/user/history")
@RequiredArgsConstructor
public class UserPurchaseHistoryController {

    private final PurchaseHistoryService service;

    @GetMapping("/{id}")
    public List<SelfPurchaseDto> viewHistory(@PathVariable(name = "id") Long userId,
                                     @RequestParam(value = "from", required = false, defaultValue = "0") int from,
                                     @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        log.info("Get history for user {}", userId);
        return service.viewHistory(userId, from, size);
    }
}
