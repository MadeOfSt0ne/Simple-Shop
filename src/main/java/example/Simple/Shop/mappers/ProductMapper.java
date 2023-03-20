package example.Simple.Shop.mappers;

import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.product.dto.AddProductDto;

import java.util.ArrayList;

public class ProductMapper {

    public static Product toProduct(AddProductDto dto, Organization organization) {
        Product product = new Product();
        product.setBlocked(true);
        product.setName(dto.getProductName());
        product.setOrganization(organization);
        product.setWarehouseAmount(dto.getWarehouseAmount());
        product.setPrice(dto.getPrice());
        product.setKeywords(new ArrayList<>());
        product.setDiscounts(new ArrayList<>());
        product.setSpecifications(new ArrayList<>());
        product.setMarks(new ArrayList<>());
        product.setReviews(new ArrayList<>());
        return product;
    }
}
