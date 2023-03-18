package example.Simple.Shop.service.admin;

public interface AdminOrganizationService {

    void registerOrganization();
    void blockOrganization(Long organizationId);
    void deleteOrganization(Long organizationId);
}
