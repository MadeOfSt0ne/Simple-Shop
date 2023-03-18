package example.Simple.Shop.service.admin;

import example.Simple.Shop.model.Product;

import java.util.Set;

public interface AdminProductService {

    Product addProductInfo();
    Product updateProductInfo();
    void setDiscount();
}
