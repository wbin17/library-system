package library.management.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import library.management.domain.member.Member;
import library.management.domain.member.MemberRole;
import library.management.web.SessionConst;
import org.springframework.web.servlet.HandlerInterceptor;

public class AdminCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if(member != null && MemberRole.admin == member.getMemberRole()){
            //로그인으로 리다이렉트
            return true;
        }

        response.sendRedirect("/admin/accessDenied");
        return false;
    }
}
