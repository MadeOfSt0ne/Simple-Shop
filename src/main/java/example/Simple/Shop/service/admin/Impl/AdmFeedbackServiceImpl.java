package example.Simple.Shop.service.admin.Impl;

import example.Simple.Shop.model.mark.Mark;
import example.Simple.Shop.model.review.Review;
import example.Simple.Shop.repository.MarkRepository;
import example.Simple.Shop.repository.ReviewRepository;
import example.Simple.Shop.service.admin.AdminFeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmFeedbackServiceImpl implements AdminFeedbackService {

    private final ReviewRepository reviewRepo;
    private final MarkRepository markRepo;

    /**
     * Просмотр отзывов на продукт (постранично)
     */
    @Override
    public List<Review> findReviewsForProduct(Long productId, int from, int size) {
        Pageable pageable = PageRequest.of(from, size);
        return reviewRepo.findByProductId(productId, pageable);
    }

    /**
     * Просмотр оценок продукта (постранично)
     */
    @Override
    public List<Mark> findMarksForProduct(Long productId, int from, int size) {
        Pageable pageable = PageRequest.of(from, size);
        return markRepo.findByProductId(productId, pageable);
    }

    /**
     * Удаление отзывов по идентификатору
     */
    @Override
    public void deleteReviews(List<Long> reviewsIds) {
        for (Long id : reviewsIds) {
            reviewRepo.deleteById(id);
        }
    }

    /**
     * Удаление оценок по идентификатору
     */
    @Override
    public void deleteMarks(List<Long> marksIds) {
        for (Long id : marksIds) {
            markRepo.deleteById(id);
        }
    }
}
