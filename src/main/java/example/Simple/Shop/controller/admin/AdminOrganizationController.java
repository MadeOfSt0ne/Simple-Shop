package example.Simple.Shop.controller.admin;

import example.Simple.Shop.service.admin.AdminOrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/admin/organization")
@RequiredArgsConstructor
public class AdminOrganizationController {

    private final AdminOrganizationService service;

    @PatchMapping("/register/{id}")
    public void registerOrganization(@PathVariable(value = "id") Long organizationId) {
        log.info("Register organization id={}", organizationId);
        service.registerOrganization(organizationId);
    }

    @PatchMapping("/block/{id}")
    public void blockOrganization(@PathVariable(value = "id") Long organizationId) {
        log.info("Block organization id={}", organizationId);
        service.blockOrganization(organizationId);
    }

    @DeleteMapping("/{id}")
    public void deleteOrganization(@PathVariable(value = "id") Long organizationId) {
        log.info("Delete organization id={}", organizationId);
        service.deleteOrganization(organizationId);
    }
}
