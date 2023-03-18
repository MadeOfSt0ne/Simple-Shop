package example.Simple.Shop.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Product {

    private long id;
    private String name;
    private Organization organization;
    private BigDecimal price;
    private long warehouseAmount;
    private List<Discount> discounts;
    private List<Review> reviews;
    private Set<String> keyWords;
    private Map<String, String> specifications;
    private List<Integer> scores;
}
