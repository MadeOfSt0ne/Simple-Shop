package example.Simple.Shop.mappers;

import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.review.Review;
import example.Simple.Shop.model.review.dto.ReviewDto;
import example.Simple.Shop.model.user.User;

import java.time.LocalDate;

public class ReviewMapper {

    public static Review toReview(ReviewDto dto, User author, Product product) {
        Review review = new Review();
        review.setAuthor(author);
        review.setProduct(product);
        review.setCreated(LocalDate.now());
        review.setText(dto.getText());
        return review;
    }
}
