package example.Simple.Shop.service.admin;

import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.purchase.Purchase;
import example.Simple.Shop.model.purchase.dto.PurchaseDto;
import example.Simple.Shop.model.user.Role;
import example.Simple.Shop.model.user.User;
import example.Simple.Shop.repository.OrganizationRepository;
import example.Simple.Shop.repository.ProductRepository;
import example.Simple.Shop.repository.PurchaseRepository;
import example.Simple.Shop.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AdminPurchaseHistoryTest {

    private final AdminPurchaseHistory service;
    private final UserRepository userRepo;
    private final PurchaseRepository purchaseRepo;
    private final OrganizationRepository organizationRepo;
    private final ProductRepository productRepo;

    @Autowired
    AdminPurchaseHistoryTest(AdminPurchaseHistory service, UserRepository userRepo, PurchaseRepository purchaseRepo, OrganizationRepository organizationRepo, ProductRepository productRepo) {
        this.service = service;
        this.userRepo = userRepo;
        this.purchaseRepo = purchaseRepo;
        this.organizationRepo = organizationRepo;
        this.productRepo = productRepo;
    }

    Purchase purchase = new Purchase();
    User buyer = new User();
    Organization seller = new Organization();
    Product product = new Product();

    @BeforeEach
    void setUp() {
        buyer.setUsername("author");
        buyer.setEmail("sada@addasd.com");
        buyer.setPassword("ssss");
        buyer.setRole(Role.ADMIN);
        userRepo.save(buyer);

        seller.setName("org");
        seller.setOwner(buyer);
        seller.setBlocked(false);
        seller.setProducts(List.of(product));
        organizationRepo.save(seller);

        product.setName("product");
        product.setPrice(BigDecimal.valueOf(20));
        product.setOrganization(seller);
        product.setBlocked(true);
        productRepo.save(product);

        purchase.setAmount(11);
        purchase.setPrice(BigDecimal.valueOf(10));
        purchase.setSeller(seller);
        purchase.setBuyer(buyer);
        purchase.setProduct(product);
        purchase.setBuyTime(LocalDateTime.now().minusDays(1));
        purchaseRepo.save(purchase);
    }

    @Test
    void viewHistory() {
        List<PurchaseDto> list = service.viewHistory(1L, 0, 10);
        assertEquals("product", list.get(0).getProduct().getName());
    }
}