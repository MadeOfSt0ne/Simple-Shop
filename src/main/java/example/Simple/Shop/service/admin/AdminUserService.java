package example.Simple.Shop.service.admin;

import example.Simple.Shop.model.user.User;

import java.math.BigDecimal;

public interface AdminUserService {

    void getPurchaseHistoryForUser(Long userId);
    User getUserInfo(Long userId);
    void deleteUser(Long userId);
    void blockUser(Long userId);
    void adjustBalance(BigDecimal value, Long userId);
}
