package example.Simple.Shop.service.user;

import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.organization.dto.OrganizationDto;

public interface CreateOrganizationService {

    Organization createOrganization(Long userId, OrganizationDto dto);
}
