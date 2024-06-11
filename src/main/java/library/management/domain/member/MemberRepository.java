package library.management.domain.member;

import library.management.web.login.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberRepository {
    private final SqlSessionTemplate sql;
    public void save(Member member){
        sql.insert("Member.save", member);//객체 두개이상 넘기려면 해쉬맵으로 넘겨
    }

    public Member findById(LoginForm loginForm) {
        return sql.selectOne("Member.findById", loginForm);
    }

    public void updateRentState1(String memberId) {
        sql.update("Member.updateRentState1", memberId);
    }

    public void updateRentState0(String memberId) {
        sql.update("Member.updateRentState0", memberId);
    }

    public Member selectMembersWithLoan(String memberId){
        Member member = (Member) sql.selectOne("Member.selectMembersWithLoan", memberId);
        log.info("member = {}", member);
        return member;
    }

    public Member findByMemberId(String memberId) {
        return sql.selectOne("Member.findByMemberId", memberId);
    }

    public void editMember(Member member) {
        sql.update("Member.editMember", member);
    }

    public void adminSave(Member member) {
        sql.insert("Member.adminSave", member);
    }

    public int isMemberIdAvailable(String memberId) {
        return sql.selectOne("Member.isMemberIdAvailable", memberId);
    }

    public boolean isMemberRentingBook(String memberId) {
        return sql.selectOne("Member.isMemberRentingBook", memberId);
    }

    public void deleteMember(String memberId) {
        sql.delete("Member.deleteMember", memberId);
    }
}
