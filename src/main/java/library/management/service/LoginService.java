package library.management.service;

import library.management.domain.member.Member;
import library.management.domain.member.MemberRepository;
import library.management.web.login.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    public Member login(LoginForm loginForm){
        Member findMember = memberRepository.findById(loginForm);
        if(findMember != null){
            return findMember;
        } else{
            return null;
        }
    }

}
