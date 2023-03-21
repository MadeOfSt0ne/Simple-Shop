package example.Simple.Shop.service.admin;

import example.Simple.Shop.model.discount.dto.DiscountDto;
import example.Simple.Shop.model.keyword.Keyword;
import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.product.dto.ProductInfoDto;
import example.Simple.Shop.model.specification.Specification;
import example.Simple.Shop.model.user.Role;
import example.Simple.Shop.model.user.User;
import example.Simple.Shop.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AdminProductServiceTest {

    private final AdminProductService service;
    private final ProductRepository productRepo;
    private final DiscountRepository discountRepo;
    private final OrganizationRepository organizationRepo;
    private final KeywordRepository keywordRepo;
    private final UserRepository userRepo;

    @Autowired
    AdminProductServiceTest(AdminProductService service,
                            ProductRepository productRepo,
                            DiscountRepository discountRepo,
                            OrganizationRepository organizationRepo,
                            KeywordRepository keywordRepo,
                            UserRepository userRepo) {
        this.service = service;
        this.productRepo = productRepo;
        this.discountRepo = discountRepo;
        this.organizationRepo = organizationRepo;
        this.keywordRepo = keywordRepo;
        this.userRepo = userRepo;
    }

    Keyword keyword1 = new Keyword(1L, "first word");
    Keyword keyword2 = new Keyword(2L, "second word");
    User owner = new User();
    Product product = new Product();
    Product product2 = new Product();
    Organization organization = new Organization();
    Map<String, String> specs = new HashMap<>();

    @BeforeEach
    void setUp() {
        keywordRepo.save(keyword1);
        keywordRepo.save(keyword2);

        owner.setUsername("author");
        owner.setEmail("sada@addasd.com");
        owner.setPassword("ssss");
        owner.setRole(Role.ADMIN);
        userRepo.save(owner);

        organization.setName("org");
        organization.setOwner(owner);
        organization.setBlocked(false);
        organization.setProducts(List.of(product));
        organizationRepo.save(organization);

        product.setName("product");
        product.setPrice(BigDecimal.valueOf(20));
        product.setOrganization(organization);
        product.setBlocked(true);
        productRepo.save(product);

        product2.setName("second");
        product2.setPrice(BigDecimal.valueOf(11));
        product2.setOrganization(organization);
        product2.setBlocked(false);
        productRepo.save(product2);

        specs.put("one", "one");
        specs.put("two", "two");
    }

    @Test
    void addKeywords() {
        Product product1 = service.addKeywords(1L, List.of(1L, 2L));
        assertEquals(2, product1.getKeywords().size());
    }

    @Test
    void removeKeywords() {
        Product product1 = service.addKeywords(1L, List.of(1L, 2L));
        service.removeKeywords(1L, List.of(1L));
        assertEquals(1, productRepo.getProductById(1L).getKeywords().size());
    }

    @Test
    void addSpecifications() {
        List<Specification> list = service.addSpecifications(1L, specs);
        assertEquals(2, list.size());
    }

    @Test
    void updateProductInfo() {
        ProductInfoDto dto = new ProductInfoDto(1L,
                "new name",
                1L,
                BigDecimal.valueOf(999),
                200L);
        Product product1 = service.updateProductInfo(dto);
        assertEquals("new name", product1.getName());
        assertEquals(BigDecimal.valueOf(999), product1.getPrice());
    }

    @Test
    void setDiscount() {
        DiscountDto dto = new DiscountDto("discount",
                List.of(1L),
                0.1,
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(10));
        service.setDiscount(dto);
        assertEquals(1, productRepo.getProductById(1L).getDiscounts().size());
        assertEquals(1, discountRepo.findAll().size());
    }

    @Test
    void blockProduct() {
        service.blockProduct(2L);
        assertTrue(productRepo.getProductById(2L).isBlocked());
    }

    @Test
    void unlockProduct() {
        service.unlockProduct(1L);
        assertFalse(productRepo.getProductById(1L).isBlocked());
    }

    @Test
    void getProductsForModeration() {
        List<Product> list = service.getProductsForModeration(0, 10);
        assertEquals("product", list.get(0).getName());
    }
}