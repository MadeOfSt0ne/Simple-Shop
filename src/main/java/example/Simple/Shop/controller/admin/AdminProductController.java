package example.Simple.Shop.controller.admin;

import example.Simple.Shop.model.discount.dto.DiscountDto;
import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.product.dto.ProductInfoDto;
import example.Simple.Shop.model.specification.Specification;
import example.Simple.Shop.service.admin.AdminProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(path = "/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductService service;

    @PostMapping("/keywords/{id}")
    public Product addKeywords(@PathVariable(value = "id") Long productId,
                               @RequestParam(name = "keywordsIds") List<Long> keywords) {
        log.info("Add keywords {} to product {}", keywords, productId);
        return service.addKeywords(productId, keywords);
    }

    @DeleteMapping("/keywords/{id}")
    public void deleteKeywords(@PathVariable(value = "id") Long productId,
                               @RequestParam(name = "keywordsIds") List<Long> keywords) {
        log.info("Delete keywords {} from product {}", keywords, productId);
        service.removeKeywords(productId, keywords);
    }

    @PostMapping("/specifications/{id}")
    public List<Specification> addSpecifications(@PathVariable(value = "id") Long productId,
                                                 @RequestParam(name = "keywordsIds") Map<String, String> specs) {
        log.info("Add specifications {} to product {}", specs, productId);
        return service.addSpecifications(productId, specs);
    }

    @DeleteMapping("/specifications/{id}")
    public void deleteSpecifications(@PathVariable(value = "id") Long productId,
                               @RequestParam(name = "specIds") List<Long> specIds) {
        log.info("Delete specifications {} from product {}", specIds, productId);
        service.removeSpecifications(productId, specIds);
    }

    @PatchMapping
    public Product updateProduct(@RequestParam(name = "dto") ProductInfoDto dto) {
        log.info("Update product with {}", dto);
        return service.updateProductInfo(dto);
    }

    @PostMapping("/discount")
    public void addDiscount(@RequestParam(name = "dto")DiscountDto dto) {
        log.info("Add discount {}", dto);
        service.setDiscount(dto);
    }

    @PatchMapping("/block/{id}")
    public void blockProduct(@PathVariable(value = "id") Long productId) {
        log.info("Block product id={}", productId);
        service.blockProduct(productId);
    }

    @PatchMapping("/unlock/{id}")
    public void unlockProduct(@PathVariable(value = "id") Long productId) {
        log.info("Unlock product id={}", productId);
        service.unlockProduct(productId);
    }

    @GetMapping
    public List<Product> getProductsForModeration(
            @RequestParam(value = "from", required = false, defaultValue = "0") int from,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        log.info("Get product for moderation");
        return service.getProductsForModeration(from, size);
    }
}
