package library.management.web.loan;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import library.management.domain.member.Member;
import library.management.service.BookService;
import library.management.service.LoanService;
import library.management.service.MemberService;
import library.management.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/loans")
public class LoanController {
    private final BookService bookService;
    private final MemberService memberService;
    private final LoanService loanService;

    @PostMapping("/add")
    public String addLoans(HttpServletRequest request,
                           @RequestParam("bookIsbn")String bookIsbn,
                           Model model){
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        model.addAttribute("loginMember", loginMember);
        loanService.loan(loginMember, bookIsbn);
        return "redirect:/";
    }

    @PostMapping("/return")
    public String returnBook(HttpServletRequest request,
                             @RequestParam("bookIsbn")String bookIsbn,
                             Model model){
        Member member = (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        loanService.returnBook(member, bookIsbn);
        return "redirect:/members/" + member.getMemberId();
    }

}
