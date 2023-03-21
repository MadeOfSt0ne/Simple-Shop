package example.Simple.Shop.service.admin;

import example.Simple.Shop.model.user.Role;
import example.Simple.Shop.model.user.User;
import example.Simple.Shop.model.user.dto.UserInfo;
import example.Simple.Shop.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AdminUserServiceTest {

    private final AdminUserService service;
    private final UserRepository userRepo;

    @Autowired
    AdminUserServiceTest(AdminUserService service, UserRepository userRepo) {
        this.service = service;
        this.userRepo = userRepo;
    }

    User user = new User();

    @BeforeEach
    void setUp() {
        user.setUsername("USER");
        user.setEmail("sada@addasd.com");
        user.setPassword("ssss");
        user.setRole(Role.USER);
        user.setBalance(BigDecimal.valueOf(33));
        user.setBlocked(false);
        userRepo.save(user);
    }

    @Test
    void getUserInfo() {
        UserInfo info = service.getUserInfo(1L);
        assertEquals("USER", info.getUsername());
    }

    @Test
    void deleteUser() {
        service.deleteUser(1L);
        List<User> list = userRepo.findAll();
        assertEquals(0, list.size());
    }

    @Test
    void blockUser() {
        UserInfo info = service.blockUser(1L);
        assertTrue(info.isBlocked());
    }

    @Test
    void adjustBalance() {
        UserInfo info = service.adjustBalance(BigDecimal.valueOf(22), 1L);
        assertEquals(BigDecimal.valueOf(55), info.getBalance());
    }
}