package example.Simple.Shop.model.organization.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {

    private String name;
    private String description;
    private String logoUrl;

}
