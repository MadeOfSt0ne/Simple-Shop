package example.Simple.Shop.service.admin;

import example.Simple.Shop.model.discount.dto.DiscountDto;
import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.product.dto.ProductInfoDto;
import example.Simple.Shop.model.specification.Specification;

import java.util.List;
import java.util.Map;

public interface AdminProductService {

    Product addKeywords(Long productId, List<Long> keywordsIds);
    void removeKeywords(Long productId, List<Long> keywordsIds);
    Product addSpecifications(Long productId, Map<String, String> specifications);
    void removeSpecifications(Long productId, List<String> specsToRemove);
    Product updateProductInfo(ProductInfoDto dto);
    void setDiscount(DiscountDto dto);
    void blockProduct(Long productId);
    void unlockProduct(Long productId);
    List<Product> getProductsForModeration(int from, int size);
}
