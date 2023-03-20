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
public class PurchaseDto {
    private long purchaseId;
    private Product product;
    private Organization organization;
    private Buyer buyer;
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
        private long ownerId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Buyer {
        private long buyerId;
        private String name;
    }
}
