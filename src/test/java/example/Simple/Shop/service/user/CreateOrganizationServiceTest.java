package example.Simple.Shop.service.user;

import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.organization.dto.OrganizationDto;
import example.Simple.Shop.model.user.Role;
import example.Simple.Shop.model.user.User;
import example.Simple.Shop.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CreateOrganizationServiceTest {

    private final CreateOrganizationService service;
    private final UserRepository userRepo;

    @Autowired
    CreateOrganizationServiceTest(CreateOrganizationService service, UserRepository userRepo) {
        this.service = service;
        this.userRepo = userRepo;
    }

    User user = new User();

    @BeforeEach
    void setUp() {
        user.setUsername("author");
        user.setEmail("sada@addasd.com");
        user.setPassword("ssss");
        user.setRole(Role.ADMIN);
        userRepo.save(user);

    }

    @Test
    void createOrganization() {
        OrganizationDto dto = new OrganizationDto("name", "description", "logoUrl");
        Organization organization = service.createOrganization(1L, dto);
        assertEquals("name", organization.getName());
        assertEquals("logoUrl", organization.getLogoUrl());
    }
}