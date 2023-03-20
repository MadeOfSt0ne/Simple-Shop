package example.Simple.Shop.model.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddProductDto {
    private String productName;
    private Long organizationId;
    private BigDecimal price;
    private Long warehouseAmount;
}
