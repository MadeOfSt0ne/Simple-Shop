package example.Simple.Shop.service.admin;

import example.Simple.Shop.model.mark.Mark;
import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.review.Review;
import example.Simple.Shop.model.user.Role;
import example.Simple.Shop.model.user.User;
import example.Simple.Shop.repository.*;
import example.Simple.Shop.service.user.PostgresContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AdminFeedbackServiceTest extends PostgresContainer {

    private final AdminFeedbackService service;
    private final ReviewRepository reviewRepo;
    private final MarkRepository markRepo;
    private final UserRepository userRepo;
    private final OrganizationRepository organizationRepo;
    private final ProductRepository productRepo;

    @Autowired
    AdminFeedbackServiceTest(AdminFeedbackService service,
                             ReviewRepository reviewRepo,
                             MarkRepository markRepo,
                             UserRepository userRepo,
                             OrganizationRepository organizationRepo,
                             ProductRepository productRepo) {
        this.service = service;
        this.reviewRepo = reviewRepo;
        this.markRepo = markRepo;
        this.userRepo = userRepo;
        this.organizationRepo = organizationRepo;
        this.productRepo = productRepo;
    }

    Review review = new Review();
    Mark mark = new Mark();
    User author1 = new User();
    Product product = new Product();
    Organization organization = new Organization();

    @BeforeEach
    void setUp() {
        author1.setUsername("author");
        author1.setEmail("sada@addasd.com");
        author1.setPassword("ssss");
        author1.setRole(Role.ADMIN);
        author1.setBalance(BigDecimal.valueOf(100));
        author1.setBlocked(false);
        userRepo.save(author1);

        organization.setName("org");
        organization.setOwner(author1);
        organization.setBlocked(false);
        organization.setProducts(List.of(product));
        organizationRepo.save(organization);

        product.setName("product");
        product.setPrice(BigDecimal.valueOf(20));
        product.setOrganization(organization);
        productRepo.save(product);

        review.setAuthor(author1);
        review.setProduct(product);
        review.setCreated(LocalDate.now());
        review.setText("Some review");

        mark.setAuthor(author1);
        mark.setProduct(product);
        mark.setCreated(LocalDate.now());
        mark.setValue(5);
    }

    @Test
    void contextLoads() {
        assertNotNull(service);
    }

    @Test
    void findReviewsForProduct() {
        reviewRepo.save(review);
        List<Review> reviews = service.findReviewsForProduct(1L, 0, 10);
        assertEquals(1, reviews.size());
        assertEquals("Some review", reviews.get(0).getText());
    }

    @Test
    void findMarksForProduct() {
        markRepo.save(mark);
        List<Mark> marks = service.findMarksForProduct(1L, 0, 10);
        assertEquals(1, marks.size());
        assertEquals(5, marks.get(0).getValue());
    }

    @Test
    void deleteReviews() {
        Review saved = reviewRepo.save(review);
        assertNotNull(saved);

        service.deleteReviews(List.of(review.getId()));
        List<Review> reviews = reviewRepo.findAll();
        assertEquals(0, reviews.size());
    }

    @Test
    void deleteMarks() {
        Mark saved = markRepo.save(mark);
        assertNotNull(saved);

        service.deleteMarks(List.of(mark.getId()));
        List<Mark> marks = markRepo.findAll();
        assertEquals(0, marks.size());
    }
}