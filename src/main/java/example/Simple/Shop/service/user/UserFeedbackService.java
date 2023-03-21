package example.Simple.Shop.service.user;

import example.Simple.Shop.model.mark.Mark;
import example.Simple.Shop.model.mark.dto.MarkDto;
import example.Simple.Shop.model.review.Review;
import example.Simple.Shop.model.review.dto.ReviewDto;

public interface UserFeedbackService {

    Review addReview(ReviewDto dto);
    Mark addMark(MarkDto dto);
}
