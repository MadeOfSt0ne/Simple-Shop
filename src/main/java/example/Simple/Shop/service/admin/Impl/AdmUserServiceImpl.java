package example.Simple.Shop.service.admin.Impl;

import example.Simple.Shop.mappers.UserInfoMapper;
import example.Simple.Shop.model.user.User;
import example.Simple.Shop.model.user.dto.UserInfo;
import example.Simple.Shop.repository.UserRepository;
import example.Simple.Shop.service.admin.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AdmUserServiceImpl implements AdminUserService {

    private final UserRepository userRepo;

    /**
     * Просмотр информации о пользователе
     */
    @Override
    public UserInfo getUserInfo(Long userId) {
        User user = userRepo.getUserById(userId);
        return UserInfoMapper.toUserInfo(user);
    }

    /**
     * Удаление пользователя
     */
    @Override
    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }

    /**
     * Блокировка пользователя
     */
    @Override
    public UserInfo blockUser(Long userId) {
        User user = userRepo.getUserById(userId);
        user.setBlocked(true);
        return UserInfoMapper.toUserInfo(userRepo.save(user));
    }

    /**
     * Пополнение баланса пользователя
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public UserInfo adjustBalance(BigDecimal value, Long userId) {
        User user = userRepo.getUserById(userId);
        BigDecimal updatedBalance = user.getBalance().add(value);
        user.setBalance(updatedBalance);
        return UserInfoMapper.toUserInfo(userRepo.save(user));
    }
}
