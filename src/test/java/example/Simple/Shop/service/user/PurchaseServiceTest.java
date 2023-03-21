package example.Simple.Shop.service.user;

import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.product.Product;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PurchaseServiceTest {

    private final PurchaseService service;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;
    private final OrganizationRepository organizationRepo;
    private final PurchaseRepository purchaseRepo;

    @Autowired
    PurchaseServiceTest(PurchaseService service, UserRepository userRepo, ProductRepository productRepo, OrganizationRepository organizationRepo, PurchaseRepository purchaseRepo) {
        this.service = service;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.organizationRepo = organizationRepo;
        this.purchaseRepo = purchaseRepo;
    }

    User seller = new User();
    Product product = new Product();
    Organization organization = new Organization();
    User buyer = new User();

    @BeforeEach
    void setUp() {
        seller.setUsername("seller");
        seller.setEmail("sada@addasd.com");
        seller.setPassword("ssss");
        seller.setBalance(BigDecimal.valueOf(100));
        seller.setRole(Role.ADMIN);
        userRepo.save(seller);

        buyer.setUsername("buyer");
        buyer.setEmail("aaa@aaa.com");
        buyer.setPassword("wwww");
        buyer.setBalance(BigDecimal.valueOf(200));
        buyer.setRole(Role.USER);
        userRepo.save(buyer);

        organization.setName("org");
        organization.setOwner(seller);
        organization.setBlocked(false);
        organization.setProducts(List.of(product));
        organizationRepo.save(organization);

        product.setName("product");
        product.setPrice(BigDecimal.valueOf(20));
        product.setOrganization(organization);
        product.setWarehouseAmount(26);
        productRepo.save(product);
    }

    @Test
    void purchaseProduct() {
        service.purchaseProduct(2L, 1L, 7);
        assertEquals(19, productRepo.getReferenceById(1L).getWarehouseAmount());
        assertEquals(BigDecimal.valueOf(60), userRepo.getReferenceById(2L).getBalance());
        assertEquals(BigDecimal.valueOf(233), userRepo.getReferenceById(1L).getBalance());

        assertEquals(1, purchaseRepo.findAll().size());
    }

    @Test
    void refund() {
        service.purchaseProduct(2L, 1L, 7);
        service.refund(1L);
        assertEquals(26, productRepo.getReferenceById(1L).getWarehouseAmount());
        assertEquals(BigDecimal.valueOf(193), userRepo.getReferenceById(2L).getBalance());
        assertEquals(BigDecimal.valueOf(100), userRepo.getReferenceById(1L).getBalance());

        assertEquals(0, purchaseRepo.findAll().size());
    }
}