package example.Simple.Shop.repository;

import example.Simple.Shop.model.organization.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Organization getOrganizationById(Long organizationId);
}
