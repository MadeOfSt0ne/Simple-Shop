package example.Simple.Shop.model.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SelfPurchaseDto {
    private long purchaseId;
    private Product product;
    private Organization organization;
    private BigDecimal price;
    private LocalDateTime buyTime;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Product {
        private long productId;
        private String name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Organization {
        private long organizationId;
        private String name;
    }

}
