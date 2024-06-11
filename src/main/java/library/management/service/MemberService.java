package library.management.service;

import library.management.domain.member.Member;
import library.management.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public void save(Member member){
        memberRepository.save(member);
    }

    public void updateRentState1(String memberId){
        memberRepository.updateRentState1(memberId);
    }

    public void updateRentState0(String memberId){
        memberRepository.updateRentState0(memberId);
    }

    public Member myPage(String memberId){
        return memberRepository.selectMembersWithLoan(memberId);
    }

    public Member findByMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    public void editMember(Member member) {
        memberRepository.editMember(member);
    }

    public void adminSave(Member member) {
        memberRepository.adminSave(member);
    }

    public boolean isMemberIdAvailable(String memberId) {
        return memberRepository.isMemberIdAvailable(memberId) > 0;
    }

    public boolean isMemberRentingBook(String memberId) {
        return memberRepository.isMemberRentingBook(memberId);
    }

    public void deleteMember(String memberId) {
        memberRepository.deleteMember(memberId);
    }
}
