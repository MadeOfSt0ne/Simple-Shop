package example.Simple.Shop.controller.admin;

import example.Simple.Shop.model.user.dto.UserInfo;
import example.Simple.Shop.service.admin.AdminUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping(path = "/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService service;

    @GetMapping("/{id}")
    public UserInfo getUserInfo(@PathVariable(name = "id") Long userId) {
        log.info("Get info about user {}", userId);
        return service.getUserInfo(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") Long userId) {
        log.info("Delete user {}", userId);
        service.deleteUser(userId);
    }

    @PatchMapping("/block/{id}")
    public UserInfo blockUser(@PathVariable(name = "id") Long userId) {
        log.info("Block user {}", userId);
        return service.blockUser(userId);
    }

    @PatchMapping("/balance/{id}, {balance}")
    public UserInfo adjustBalance(@PathVariable(name = "id") Long userId,
                                  @PathVariable(name = "balance") BigDecimal add) {
        log.info("Adjust balance for user {} with {}", userId, add);
        return service.adjustBalance(add, userId);
    }
}
