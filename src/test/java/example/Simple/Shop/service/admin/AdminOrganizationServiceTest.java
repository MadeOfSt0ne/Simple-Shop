package example.Simple.Shop.service.admin;

import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.user.Role;
import example.Simple.Shop.model.user.User;
import example.Simple.Shop.repository.OrganizationRepository;
import example.Simple.Shop.repository.UserRepository;
import example.Simple.Shop.service.user.PostgresContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AdminOrganizationServiceTest extends PostgresContainer {

    private final AdminOrganizationService service;
    private final UserRepository userRepo;
    private final OrganizationRepository organizationRepo;

    @Autowired
    AdminOrganizationServiceTest(AdminOrganizationService service,
                                 UserRepository userRepo,
                                 OrganizationRepository organizationRepo) {
        this.service = service;
        this.userRepo = userRepo;
        this.organizationRepo = organizationRepo;
    }

    User owner = new User();
    Organization blocked = new Organization();
    Organization unlocked = new Organization();

    @BeforeEach
    void setUp() {
        owner.setUsername("author");
        owner.setEmail("sada@addasd.com");
        owner.setPassword("ssss");
        owner.setRole(Role.ADMIN);
        userRepo.save(owner);

        blocked.setName("blocked");
        blocked.setOwner(owner);
        blocked.setBlocked(true);
        organizationRepo.save(blocked);

        unlocked.setName("unlocked");
        unlocked.setOwner(owner);
        unlocked.setBlocked(false);
        organizationRepo.save(unlocked);
    }

    @Test
    void contextLoads() {
        assertNotNull(service);
    }

    @Test
    void registerOrganization() {
        service.registerOrganization(1L);
        Organization organization = organizationRepo.getOrganizationById(1L);
        assertFalse(organization.isBlocked());
    }

    @Test
    void blockOrganization() {
        service.blockOrganization(2L);
        Organization organization = organizationRepo.getOrganizationById(2L);
        assertTrue(organization.isBlocked());
    }

    @Test
    void deleteOrganization() {
        List<Organization> orgs = organizationRepo.findAll();
        assertEquals(2, orgs.size());

        service.deleteOrganization(blocked.getId());
        List<Organization> afterDelete = organizationRepo.findAll();
        assertEquals(1, afterDelete.size());
    }
}