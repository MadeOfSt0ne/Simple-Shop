package example.Simple.Shop.service.user;

import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.product.dto.AddProductDto;
import example.Simple.Shop.model.user.Role;
import example.Simple.Shop.model.user.User;
import example.Simple.Shop.repository.OrganizationRepository;
import example.Simple.Shop.repository.ProductRepository;
import example.Simple.Shop.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserProductServiceTest {

    private final UserProductService service;
    private final UserRepository userRepo;
    private final OrganizationRepository organizationRepo;

    @Autowired
    UserProductServiceTest(UserProductService service, UserRepository userRepo, ProductRepository productRepo, OrganizationRepository organizationRepo) {
        this.service = service;
        this.userRepo = userRepo;
        this.organizationRepo = organizationRepo;
    }

    User user = new User();
    Organization seller = new Organization();

    @BeforeEach
    void setUp() {
        user.setUsername("author");
        user.setEmail("sada@addasd.com");
        user.setPassword("ssss");
        user.setRole(Role.ADMIN);
        userRepo.save(user);

        seller.setName("org");
        seller.setOwner(user);
        seller.setBlocked(false);
        organizationRepo.save(seller);
    }

    @Test
    void addNewProduct() {
        AddProductDto dto = new AddProductDto("product", 1L, BigDecimal.valueOf(22), 11L);
        Product product = service.addNewProduct(1L, dto);
        assertEquals("product", product.getName());
        assertEquals(11L, product.getWarehouseAmount());
    }
}