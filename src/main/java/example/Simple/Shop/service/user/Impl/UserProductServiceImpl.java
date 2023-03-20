package example.Simple.Shop.service.user.Impl;

import example.Simple.Shop.exception.AccessDeniedException;
import example.Simple.Shop.mappers.ProductMapper;
import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.product.dto.AddProductDto;
import example.Simple.Shop.repository.OrganizationRepository;
import example.Simple.Shop.repository.ProductRepository;
import example.Simple.Shop.service.user.UserProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProductServiceImpl implements UserProductService {

    private final ProductRepository productRepo;
    private final OrganizationRepository organizationRepo;

    @Override
    public Product addNewProduct(Long userId, AddProductDto dto) {
        Organization seller = organizationRepo.getReferenceById(dto.getOrganizationId());
        if (seller.getOwner().getId() != userId) {
            throw new AccessDeniedException("У вас нет прав для добавления продукта в список товаров данной организации");
        }
        Product product = ProductMapper.toProduct(dto, seller);
        return productRepo.save(product);
    }
}
