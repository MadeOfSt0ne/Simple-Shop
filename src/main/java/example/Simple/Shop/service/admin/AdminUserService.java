package example.Simple.Shop.service.admin;

import example.Simple.Shop.model.user.dto.UserInfo;

import java.math.BigDecimal;

public interface AdminUserService {

    UserInfo getUserInfo(Long userId);
    void deleteUser(Long userId);
    UserInfo blockUser(Long userId);
    UserInfo adjustBalance(BigDecimal value, Long userId);
}
