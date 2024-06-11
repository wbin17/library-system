package library.management.web.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Size;
import library.management.domain.member.Member;
import library.management.service.MemberService;
import library.management.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("{memberId}")
    public String myPage(HttpServletRequest request,
                         Model model){
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        log.info("member = {}", loginMember);
        Member member = memberService.myPage(loginMember.getMemberId());
        model.addAttribute("member", member);
        return "/myPage/myPage";
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member")Member member){
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("member")Member member, Model model){

        if (memberService.isMemberIdAvailable(member.getMemberId())) {
            model.addAttribute("duplicateMessage", "이미 사용 중인 아이디입니다.");
            model.addAttribute("member", member);  // 입력된 회원 정보를 다시 전달
            return "members/addMemberForm";  // 다시 회원가입 페이지로 이동
        }
        log.info("member = {}", member);
        memberService.save(member);
        return "redirect:/";

    }

    @PostMapping("/checkDuplicate")
    public String checkDuplicate(@RequestParam String memberId, Model model) {
        boolean exists = memberService.isMemberIdAvailable(memberId);
        if (exists) {
            model.addAttribute("duplicateMessage", "이미 사용 중인 아이디입니다.");
            model.addAttribute("memberId", memberId);  // 입력된 회원 아이디를 다시 전달
//            return "members/addMemberForm";  // 회원가입 폼 페이지로 이동
        }
        model.addAttribute("memberId", memberId);  // 입력된 회원 아이디를 다시 전달
        return "/members/addMemberForm";
    }



    @GetMapping("/{memberId}/edit")
    public String editForm(@PathVariable("memberId")String memberId,
                           Model model){
        Member byMemberId = memberService.findByMemberId(memberId);
        model.addAttribute("member", byMemberId);
        return "members/editMemberForm";
    }

    @PostMapping("/{memberId}/edit")
    public String editMember(@ModelAttribute("member")Member member){
        memberService.editMember(member);
        return "redirect:/members/" + member.getMemberId();
    }

    @PostMapping("/delete")
    public String deleteMember(@RequestParam String memberId, Model model) {
        if (memberService.isMemberRentingBook(memberId)) {
            Member member = memberService.myPage(memberId);
            model.addAttribute("member", member);
            model.addAttribute("message", "대여 중인 책이 있어 회원 탈퇴를 할 수 없습니다.");
            return "myPage/myPage"; // 탈퇴 폼 페이지로 이동
        }

        memberService.deleteMember(memberId);
        return "redirect:/login"; // 탈퇴 성공 시 로그인 페이지로 이동
    }

}
