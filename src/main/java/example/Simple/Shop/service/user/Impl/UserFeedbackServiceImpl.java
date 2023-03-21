package example.Simple.Shop.service.user.Impl;

import example.Simple.Shop.exception.AccessDeniedException;
import example.Simple.Shop.mappers.MarkMapper;
import example.Simple.Shop.mappers.ReviewMapper;
import example.Simple.Shop.model.mark.Mark;
import example.Simple.Shop.model.mark.dto.MarkDto;
import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.purchase.Purchase;
import example.Simple.Shop.model.review.Review;
import example.Simple.Shop.model.review.dto.ReviewDto;
import example.Simple.Shop.model.user.User;
import example.Simple.Shop.repository.*;
import example.Simple.Shop.service.user.UserFeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFeedbackServiceImpl implements UserFeedbackService {

    private final ReviewRepository reviewRepo;
    private final MarkRepository markRepo;
    private final PurchaseRepository purchaseRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    @Override
    public Review addReview(ReviewDto dto) {
        User author = userRepo.getReferenceById(dto.getAuthorId());
        Product product = productRepo.getReferenceById(dto.getProductId());
        Purchase purchase = purchaseRepo.getPurchaseByProductIdAndBuyerId(dto.getProductId(), dto.getAuthorId());

        checkHistory(author, purchase);

        Review review = ReviewMapper.toReview(dto, author, product);
        reviewRepo.save(review);
        return review;
    }

    private static void checkHistory(User author, Purchase purchase) {
        if (author.getId() != purchase.getBuyer().getId()) {
            throw new AccessDeniedException("Вы не можете оставлять комментарии к данному товару, так как он отсутствует" +
                    "в вашей истории покупок");
        }
    }

    @Override
    public Mark addMark(MarkDto dto) {
        if (dto.getValue() < 0 || dto.getValue() > 10) {
            throw new IllegalStateException("Оценка не может быть меньше 0 и больше 10");
        }
        User author = userRepo.getReferenceById(dto.getAuthorId());
        Product product = productRepo.getReferenceById(dto.getProductId());
        Purchase purchase = purchaseRepo.getPurchaseByProductIdAndBuyerId(dto.getProductId(), dto.getAuthorId());

        checkHistory(author, purchase);

        Mark mark = MarkMapper.toMark(dto, author, product);
        markRepo.save(mark);
        return mark;
    }
}
