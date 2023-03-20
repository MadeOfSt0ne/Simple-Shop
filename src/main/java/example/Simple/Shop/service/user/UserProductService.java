package example.Simple.Shop.service.user;

import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.product.dto.AddProductDto;

public interface UserProductService {

    Product addNewProduct(Long userId, AddProductDto dto);
}
