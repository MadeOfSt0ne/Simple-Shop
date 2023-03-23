package example.Simple.Shop.service.admin.Impl;

import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.repository.OrganizationRepository;
import example.Simple.Shop.service.admin.AdminOrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdmOrganizationServiceImpl implements AdminOrganizationService {

    private final OrganizationRepository orgRepo;

    /**
     * Регистрация (разблокировка) организации
     */
    @Override
    public void registerOrganization(Long organizationId) {
        Organization org = orgRepo.getOrganizationById(organizationId);
        org.setBlocked(false);
        orgRepo.save(org);
    }

    /**
     * Блокировка организации
     */
    @Override
    public void blockOrganization(Long organizationId) {
        Organization org = orgRepo.getOrganizationById(organizationId);
        org.setBlocked(true);
        orgRepo.save(org);
    }

    /**
     * Удаление организации
     */
    @Override
    public void deleteOrganization(Long organizationId) {
        orgRepo.deleteById(organizationId);
    }
}
