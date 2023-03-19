package example.Simple.Shop.service.admin;

import example.Simple.Shop.model.discount.dto.DiscountDto;
import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.product.dto.ProductInfoDto;
import example.Simple.Shop.model.specification.Specification;

import java.util.List;

public interface AdminProductService {

    Product addKeywords(Long productId, List<Long> keywordsIds);
    Product addSpecifications(Long productId, List<Specification> specifications);
    Product updateProductInfo(ProductInfoDto dto);
    void setDiscount(DiscountDto dto);
    void blockProduct(Long productId);
    void unlockProduct(Long productId);
}
