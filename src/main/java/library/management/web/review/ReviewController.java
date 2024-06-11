package library.management.web.review;

import jakarta.servlet.http.HttpSession;
import library.management.domain.member.Member;
import library.management.domain.review.Review;
import library.management.service.ReviewService;
import library.management.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reviews")
@Slf4j
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/add")
    public String addReview(@ModelAttribute Review review, Model model) {

        reviewService.addReview(review);
        return "redirect:/books/" + review.getIsbn(); // 책 상세 페이지로 리다이렉트
    }

    @PostMapping("/update")
    public String updateReview(Review review, HttpSession session, Model model) {
        Member loggedInMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (!loggedInMember.getMemberId().equals(review.getMemberId())) {
            model.addAttribute("error", "수정 권한이 없습니다.");
            return "error";
        }
        reviewService.updateReview(review);
        return "redirect:/books/" + review.getIsbn();
    }

    @PostMapping("/delete")
    public String deleteReview(@RequestParam("reviewId")Integer reviewId,
                               @RequestParam("isbn")String isbn){
        reviewService.deleteReview(reviewId);

        return "redirect:/books/" + isbn;
    }

}
