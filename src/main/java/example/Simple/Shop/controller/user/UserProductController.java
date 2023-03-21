package example.Simple.Shop.controller.user;

import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.product.dto.AddProductDto;
import example.Simple.Shop.service.user.UserProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/user/products")
@RequiredArgsConstructor
public class UserProductController {

    private final UserProductService service;

    @PostMapping
    public Product addNewProduct(@RequestParam Long userId,
                                 @RequestBody AddProductDto dto) {
        log.info("User {} add product {}", userId, dto);
        return service.addNewProduct(userId, dto);
    }
}
