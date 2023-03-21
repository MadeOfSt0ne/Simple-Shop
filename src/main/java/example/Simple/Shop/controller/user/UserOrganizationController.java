package example.Simple.Shop.controller.user;

import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.organization.dto.OrganizationDto;
import example.Simple.Shop.service.user.UserOrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/user/organizations")
@RequiredArgsConstructor
public class UserOrganizationController {

    private final UserOrganizationService service;

    @PostMapping
    public Organization createOrganization(@RequestParam Long userId,
                                           @RequestBody OrganizationDto dto) {
        log.info("User {} creating organization {}", userId, dto);
        return service.createOrganization(userId, dto);
    }
}
