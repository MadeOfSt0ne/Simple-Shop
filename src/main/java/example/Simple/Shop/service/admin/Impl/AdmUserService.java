package example.Simple.Shop.service.admin.Impl;

import example.Simple.Shop.mappers.UserInfoMapper;
import example.Simple.Shop.model.purchase.Purchase;
import example.Simple.Shop.model.user.User;
import example.Simple.Shop.model.user.dto.UserInfo;
import example.Simple.Shop.repository.PurchaseRepository;
import example.Simple.Shop.repository.UserRepository;
import example.Simple.Shop.service.admin.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmUserService implements AdminUserService {

    private final UserRepository userRepo;
    private final PurchaseRepository purchaseRepo;

    @Override
    public List<Purchase> getPurchaseHistoryForUser(Long userId, int from, int size) {
        Pageable pageable = PageRequest.of(from, size);
        return purchaseRepo.getPurchasesByBuyerId(userId, pageable);
    }

    @Override
    public UserInfo getUserInfo(Long userId) {
        User user = userRepo.getReferenceById(userId);
        return UserInfoMapper.toUserInfo(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public void blockUser(Long userId) {
        User user = userRepo.getReferenceById(userId);
        user.setBlocked(true);
        userRepo.save(user);
    }

    @Override
    public void adjustBalance(BigDecimal value, Long userId) {
        User user = userRepo.getReferenceById(userId);
        BigDecimal updatedBalance = user.getBalance().add(value);
        user.setBalance(updatedBalance);
        userRepo.save(user);
    }
}
