package library.management;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import library.management.domain.member.Member;
import library.management.domain.member.MemberRole;
import library.management.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {
    //@GetMapping
    public String home(){
        return "/home/home";
    }

    @GetMapping()
    public String homeLogin(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
            Member loginMember,
            Model model) {
//세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "home/home";
        }else {
            model.addAttribute("member", loginMember);
            if(loginMember.getMemberRole() == MemberRole.admin){
                return "home/adminLoginHome";
            }else {
                return "home/loginHome";
            }
        }
    }

    @PostMapping("logout")
    public String logout(HttpServletRequest request) {
//세션을 삭제한다.
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
