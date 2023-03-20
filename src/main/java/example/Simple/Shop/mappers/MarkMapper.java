package example.Simple.Shop.mappers;

import example.Simple.Shop.model.mark.Mark;
import example.Simple.Shop.model.mark.dto.MarkDto;
import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.user.User;

import java.time.LocalDate;

public class MarkMapper {

    public static Mark toMark(MarkDto dto, User author, Product product) {
        Mark mark = new Mark();
        mark.setProduct(product);
        mark.setAuthor(author);
        mark.setValue(dto.getValue());
        mark.setCreated(LocalDate.now());
        return mark;
    }
}
