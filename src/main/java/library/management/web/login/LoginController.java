package library.management.web.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import library.management.domain.member.Member;
import library.management.service.LoginService;
import library.management.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(){
        return "login/loginForm";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute("loginForm")LoginForm loginForm, HttpServletRequest request){
        log.info("loginForm = {}", loginForm);
        Member loginMember = loginService.login(loginForm);
        if (loginMember == null) {
            return "login/loginForm";
        }
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        return "redirect:/";
    }


}
