package library.management.domain.review;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Review {
    private int reviewId;
    private String memberId;
    private String isbn;
    private int rating;
    private String reviewText;
    private String reviewDate;
}
