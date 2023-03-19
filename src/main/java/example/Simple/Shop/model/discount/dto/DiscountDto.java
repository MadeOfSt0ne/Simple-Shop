package example.Simple.Shop.model.discount.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDto {
    private String title;
    private List<Long> productsIds;
    private double value;
    private LocalDate startDate;
    private LocalDate endDate;
}
