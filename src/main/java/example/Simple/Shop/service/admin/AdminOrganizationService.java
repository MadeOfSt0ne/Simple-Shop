package example.Simple.Shop.service.admin;

public interface AdminOrganizationService {

    void registerOrganization(Long organizationId);
    void blockOrganization(Long organizationId);
    void deleteOrganization(Long organizationId);
}
