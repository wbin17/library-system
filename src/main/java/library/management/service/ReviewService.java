package library.management.service;

import library.management.domain.review.Review;
import library.management.domain.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Transactional
    public void addReview(Review review){
        reviewRepository.addReview(review);
    }

    @Transactional(readOnly = true)
    public List<Review> findReviewByIsbn(String isbn){
        return reviewRepository.findReviewByIsbn(isbn);
    }

    @Transactional
    public void updateReview(Review review){
        reviewRepository.updateReview(review);
    }

    @Transactional
    public void deleteReview(Integer reviewId){
        reviewRepository.deleteReview(reviewId);
    }
}
