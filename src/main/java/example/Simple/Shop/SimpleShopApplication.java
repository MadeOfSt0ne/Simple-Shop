package example.Simple.Shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SimpleShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleShopApplication.class, args);
	}

}
