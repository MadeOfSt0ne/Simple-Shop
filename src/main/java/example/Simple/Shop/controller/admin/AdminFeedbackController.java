package example.Simple.Shop.controller.admin;

import example.Simple.Shop.model.mark.Mark;
import example.Simple.Shop.model.review.Review;
import example.Simple.Shop.service.admin.AdminFeedbackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/admin/feedback")
@RequiredArgsConstructor
public class AdminFeedbackController {

    private final AdminFeedbackService service;

    @GetMapping("/reviews/{productId}")
    public List<Review> getReviewsForProduct(@PathVariable(name = "productId") Long productId,
                                 @RequestParam(value = "from", required = false, defaultValue = "0") int from,
                                 @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        log.info("Get reviews for product ={}", productId);
        return service.findReviewsForProduct(productId, from, size);
    }

    @GetMapping("/marks/{productId}")
    public List<Mark> getMarksForProduct(@PathVariable(name = "productId") Long productId,
                                 @RequestParam(value = "from", required = false, defaultValue = "0") int from,
                                 @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        log.info("Get marks for product ={}", productId);
        return service.findMarksForProduct(productId, from, size);
    }

    @DeleteMapping("/reviews")
    public void deleteReviews(@RequestParam(value = "reviewsIds") List<Long> reviewsIds) {
        log.info("Delete reviews {}", reviewsIds);
        service.deleteReviews(reviewsIds);
    }

    @DeleteMapping("/marks")
    public void deleteMarks(@RequestParam(value = "marksIds") List<Long> marksIds) {
        log.info("Delete reviews {}", marksIds);
        service.deleteMarks(marksIds);
    }
}
