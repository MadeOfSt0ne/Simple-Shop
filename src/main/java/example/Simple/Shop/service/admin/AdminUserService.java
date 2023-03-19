package example.Simple.Shop.service.admin;

import example.Simple.Shop.model.purchase.Purchase;
import example.Simple.Shop.model.user.dto.UserInfo;

import java.math.BigDecimal;
import java.util.List;

public interface AdminUserService {

    List<Purchase> getPurchaseHistoryForUser(Long userId, int from, int size);
    UserInfo getUserInfo(Long userId);
    void deleteUser(Long userId);
    void blockUser(Long userId);
    void adjustBalance(BigDecimal value, Long userId);
}
