package example.Simple.Shop.controller.user;

import example.Simple.Shop.service.user.PurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/user/purchases")
@RequiredArgsConstructor
public class UserPurchaseController {

    private final PurchaseService service;

    @PostMapping("/{buyer},{product},{amount}")
    public void purchaseProduct(@PathVariable(name = "buyer") Long userId,
                                @PathVariable(name = "product") Long productId,
                                @PathVariable(name = "amount") int amount) {
        log.info("User {} purchases product {}, amount {}", userId, productId, amount);
        service.purchaseProduct(userId, productId, amount);
    }

    @GetMapping("/{id}")
    public void refund(@PathVariable(name = "id") Long purchaseId) {
        log.info("Making refund for purchase {}", purchaseId);
        service.refund(purchaseId);
    }
}
