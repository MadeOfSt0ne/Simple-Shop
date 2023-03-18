package example.Simple.Shop.model;

import java.time.LocalDate;
import java.util.Set;

public class Discount {

    private long id;
    private Set<Product> products;
    private double value;
    private LocalDate start;
    private LocalDate end;
}
