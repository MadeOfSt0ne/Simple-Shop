package example.Simple.Shop.controller.user;

import example.Simple.Shop.model.mark.Mark;
import example.Simple.Shop.model.mark.dto.MarkDto;
import example.Simple.Shop.model.review.Review;
import example.Simple.Shop.model.review.dto.ReviewDto;
import example.Simple.Shop.service.user.UserFeedbackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/user/feedback")
@RequiredArgsConstructor
public class UserFeedbackController {

    private final UserFeedbackService service;

    @PostMapping("/review")
    public Review addReview(@RequestBody ReviewDto dto) {
        log.info("Add review {}", dto);
        return service.addReview(dto);
    }

    @PostMapping("/mark")
    public Mark addMark(@RequestBody MarkDto dto) {
        log.info("Add mark {}", dto);
        return service.addMark(dto);
    }
}
