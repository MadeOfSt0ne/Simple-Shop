package example.Simple.Shop.mappers;

import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.organization.dto.OrganizationDto;
import example.Simple.Shop.model.user.User;

import java.util.ArrayList;

public class OrganizationMapper {

    public static Organization toOrganization(OrganizationDto dto, User owner) {
        Organization organization = new Organization();
        organization.setBlocked(true);
        organization.setOwner(owner);
        organization.setName(dto.getName());
        organization.setDescription(dto.getDescription());
        organization.setLogoUrl(dto.getLogoUrl());
        organization.setProducts(new ArrayList<>());
        return organization;
    }
}
