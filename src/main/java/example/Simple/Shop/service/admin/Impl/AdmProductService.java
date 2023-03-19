package example.Simple.Shop.service.admin.Impl;

import example.Simple.Shop.mappers.DiscountMapper;
import example.Simple.Shop.model.discount.dto.DiscountDto;
import example.Simple.Shop.model.keyword.Keyword;
import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.product.dto.ProductInfoDto;
import example.Simple.Shop.model.specification.Specification;
import example.Simple.Shop.repository.DiscountRepository;
import example.Simple.Shop.repository.KeywordRepository;
import example.Simple.Shop.repository.OrganizationRepository;
import example.Simple.Shop.repository.ProductRepository;
import example.Simple.Shop.service.admin.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AdmProductService implements AdminProductService {

    private final ProductRepository productRepo;
    private final DiscountRepository discountRepo;
    private final OrganizationRepository organizationRepo;
    private final KeywordRepository keywordRepo;

    @Override
    public Product addKeywords(Long productId, List<Long> keywordsIds) {
        Product product = productRepo.getReferenceById(productId);
        List<Keyword> addedKeywords = keywordsIds.stream()
                .map(keywordRepo::getReferenceById)
                .toList();
        List<Keyword> oldKeywords = product.getKeywords();
        List<Keyword> newKeywords = Stream.concat(addedKeywords.stream(), oldKeywords.stream())
                .distinct()
                .toList();
        product.setKeywords(newKeywords);
        return productRepo.save(product);
    }

    @Override
    public Product addSpecifications(Long productId, List<Specification> specifications) {
        Product product = productRepo.getReferenceById(productId);
        product.setSpecifications(specifications);
        return productRepo.save(product);
    }

    @Override
    public Product updateProductInfo(ProductInfoDto dto) {
        Product product = productRepo.getReferenceById(dto.getProductId());
        Organization organization = organizationRepo.getReferenceById(dto.getOrganizationId());
        product.setName(dto.getName());
        product.setOrganization(organization);
        product.setPrice(dto.getPrice());
        product.setWarehouseAmount(dto.getWarehouseAmount());
        return productRepo.save(product);
    }

    @Override
    public void setDiscount(DiscountDto dto) {
        List<Product> products = dto.getProductsIds().stream()
                .map(productRepo::getReferenceById)
                .toList();
        discountRepo.save(DiscountMapper.toDiscount(dto, products));
    }

    @Override
    public void blockProduct(Long productId) {
        Product product = productRepo.getReferenceById(productId);
        product.setBlocked(true);
        productRepo.save(product);
    }

    @Override
    public void unlockProduct(Long productId) {
        Product product = productRepo.getReferenceById(productId);
        product.setBlocked(false);
        productRepo.save(product);
    }

}
