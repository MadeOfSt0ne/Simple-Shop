package example.Simple.Shop.service.admin.Impl;

import example.Simple.Shop.mappers.DiscountMapper;
import example.Simple.Shop.model.discount.dto.DiscountDto;
import example.Simple.Shop.model.keyword.Keyword;
import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.product.dto.ProductInfoDto;
import example.Simple.Shop.model.specification.Specification;
import example.Simple.Shop.repository.*;
import example.Simple.Shop.service.admin.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AdmProductServiceImpl implements AdminProductService {

    private final ProductRepository productRepo;
    private final DiscountRepository discountRepo;
    private final OrganizationRepository organizationRepo;
    private final KeywordRepository keywordRepo;
    private final SpecificationRepository specificationRepo;

    @Override
    public Product addKeywords(Long productId, List<Long> keywordsIds) {
        Product product = productRepo.getProductById(productId);
        List<Keyword> addedKeywords = keywordsIds.stream()
                .map(keywordRepo::getKeywordById)
                .toList();
        List<Keyword> oldKeywords = product.getKeywords();
        List<Keyword> newKeywords = Stream.concat(addedKeywords.stream(), oldKeywords.stream())
                .distinct()
                .toList();
        product.setKeywords(newKeywords);
        return productRepo.save(product);
    }

    @Override
    public void removeKeywords(Long productId, List<Long> keywordsIds) {
        Product product = productRepo.getProductById(productId);
        List<Keyword> keywords = product.getKeywords();
        for (Long key : keywordsIds) {
            keywords.removeIf(kw -> key.equals(kw.getId()));
        }
        product.setKeywords(keywords);
        productRepo.save(product);
    }

    @Override
    public List<Specification> addSpecifications(Long productId, Map<String, String> specifications) {
        Product product = productRepo.getProductById(productId);
        for (Map.Entry<String, String> s : specifications.entrySet()) {
            Specification sp = new Specification();
            sp.setName(s.getKey());
            sp.setValue(s.getValue());
            sp.setProduct(product);
            specificationRepo.save(sp);
        }
        productRepo.save(product);
        return specificationRepo.findSpecificationsByProductId(productId);
    }

    @Override
    public void removeSpecifications(Long productId, List<Long> specsToRemove) {
        Product product = productRepo.getProductById(productId);
        for (Long id : specsToRemove) {
            specificationRepo.deleteSpecificationByIdAndProductId(id, productId);
        }
    }

    @Override
    public Product updateProductInfo(ProductInfoDto dto) {
        Product product = productRepo.getProductById(dto.getProductId());
        Organization organization = organizationRepo.getOrganizationById(dto.getOrganizationId());
        product.setName(dto.getName());
        product.setOrganization(organization);
        product.setPrice(dto.getPrice());
        product.setWarehouseAmount(dto.getWarehouseAmount());
        return productRepo.save(product);
    }

    @Override
    public void setDiscount(DiscountDto dto) {
        List<Product> products = dto.getProductsIds().stream()
                .map(productRepo::getProductById)
                .toList();
        discountRepo.save(DiscountMapper.toDiscount(dto, products));
    }

    @Override
    public void blockProduct(Long productId) {
        Product product = productRepo.getProductById(productId);
        product.setBlocked(true);
        productRepo.save(product);
    }

    @Override
    public void unlockProduct(Long productId) {
        Product product = productRepo.getProductById(productId);
        product.setBlocked(false);
        productRepo.save(product);
    }

    @Override
    public List<Product> getProductsForModeration(int from, int size) {
        Pageable pageable = PageRequest.of(from, size);
        return productRepo.getBlockedProducts(pageable);
    }
}
