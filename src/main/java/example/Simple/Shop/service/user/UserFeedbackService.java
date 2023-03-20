package example.Simple.Shop.service.user;

import example.Simple.Shop.model.mark.dto.MarkDto;
import example.Simple.Shop.model.review.dto.ReviewDto;

public interface UserFeedbackService {

    void addReview(ReviewDto dto);
    void addMark(MarkDto dto);
}
