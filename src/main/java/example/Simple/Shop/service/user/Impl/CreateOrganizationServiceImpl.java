package example.Simple.Shop.service.user.Impl;

import example.Simple.Shop.mappers.OrganizationMapper;
import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.organization.dto.OrganizationDto;
import example.Simple.Shop.model.user.User;
import example.Simple.Shop.repository.OrganizationRepository;
import example.Simple.Shop.repository.UserRepository;
import example.Simple.Shop.service.user.CreateOrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOrganizationServiceImpl implements CreateOrganizationService {

    private final OrganizationRepository organizationRepo;
    private final UserRepository userRepo;

    /**
     * Добавление новой организации
     */
    @Override
    public Organization createOrganization(Long userId, OrganizationDto dto) {
        User owner = userRepo.getReferenceById(userId);
        Organization organization = OrganizationMapper.toOrganization(dto, owner);
        return organizationRepo.save(organization);
    }

}
