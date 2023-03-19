package example.Simple.Shop.mappers;

import example.Simple.Shop.model.discount.Discount;
import example.Simple.Shop.model.discount.dto.DiscountDto;
import example.Simple.Shop.model.product.Product;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class DiscountMapper {

    public static Discount toDiscount(DiscountDto dto, List<Product> products) {
        Discount discount = new Discount();
        discount.setTitle(dto.getTitle());
        discount.setProducts(products);
        discount.setValue(dto.getValue());
        discount.setStart(dto.getStartDate());
        discount.setEnd(dto.getEndDate());
        return discount;
    }
}
