package example.Simple.Shop.service.admin;

import example.Simple.Shop.model.mark.Mark;
import example.Simple.Shop.model.review.Review;

import java.util.List;

public interface AdminFeedbackService {

    List<Review> findReviewsForProduct(Long productId, int from, int size);
    List<Mark> findMarksForProduct(Long productId, int from, int size);
    void deleteReviews(List<Long> reviewsIds);
    void deleteMarks(List<Long> marksIds);
}
