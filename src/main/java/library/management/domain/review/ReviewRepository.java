package library.management.domain.review;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ReviewRepository {
    private final SqlSessionTemplate sql;

    public void addReview(Review review){
        sql.insert("Review.insertReview", review);
    }

    public List<Review> findReviewByIsbn(String bookIsbn){
        return sql.selectList("Review.selectReviewsByBookIsbn", bookIsbn);
    }

    public void updateReview(Review review){
         sql.update("Review.updateReview", review);
    }

    public void deleteReview(Integer reviewId){
        sql.delete("Review.deleteReview", reviewId);
    }
}
