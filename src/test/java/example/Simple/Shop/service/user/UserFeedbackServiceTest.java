package example.Simple.Shop.service.user;

import example.Simple.Shop.model.mark.Mark;
import example.Simple.Shop.model.mark.dto.MarkDto;
import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.purchase.Purchase;
import example.Simple.Shop.model.review.Review;
import example.Simple.Shop.model.review.dto.ReviewDto;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserFeedbackServiceTest {

    private final UserFeedbackService service;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;
    private final OrganizationRepository organizationRepo;
    private final PurchaseRepository purchaseRepo;

    @Autowired
    UserFeedbackServiceTest(UserFeedbackService service, UserRepository userRepo, ProductRepository productRepo, OrganizationRepository organizationRepo, PurchaseRepository purchaseRepo) {
        this.service = service;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.organizationRepo = organizationRepo;
        this.purchaseRepo = purchaseRepo;
    }

    User user = new User();
    Product product = new Product();
    Organization organization = new Organization();
    Purchase purchase = new Purchase();

    @BeforeEach
    void setUp() {
        user.setUsername("author");
        user.setEmail("sada@addasd.com");
        user.setPassword("ssss");
        user.setRole(Role.ADMIN);
        userRepo.save(user);

        organization.setName("org");
        organization.setOwner(user);
        organization.setBlocked(false);
        organization.setProducts(List.of(product));
        organizationRepo.save(organization);

        product.setName("product");
        product.setPrice(BigDecimal.valueOf(20));
        product.setOrganization(organization);
        productRepo.save(product);

        purchase.setProduct(product);
        purchase.setSeller(organization);
        purchase.setBuyer(user);
        purchase.setBuyTime(LocalDateTime.now().minusDays(1));
        purchase.setPrice(product.getPrice());
        purchase.setAmount(2);
        purchaseRepo.save(purchase);
    }

    @Test
    void addReview() {
        ReviewDto dto = new ReviewDto(1L, 1L, "message");
        Review review = service.addReview(dto);
        assertEquals("message", review.getText());
    }

    @Test
    void addMark() {
        MarkDto dto = new MarkDto(1L, 1L, 8);
        Mark mark = service.addMark(dto);
        assertEquals(8, mark.getValue());
    }

    @Test
    void addIncorrectMark() {
        MarkDto dto = new MarkDto(1L, 1L, 88);
        assertThrows(IllegalStateException.class, () -> service.addMark(dto));
    }
}