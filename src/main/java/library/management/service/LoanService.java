package library.management.service;

import library.management.domain.book.Book;
import library.management.domain.loan.LoanRepository;
import library.management.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanService {
    private final LoanRepository loanRepository;
    private final MemberService memberService;
    private final BookService bookService;


    @Transactional
    public void loan(Member loginMember, String bookIsbn){

//        if (isMemberAlreadyHasBook(loginMember.getMemberId(), bookIsbn)) {
//            throw new RuntimeException("회원이 이미 이 책을 대여 중입니다.");
//        }
        memberService.updateRentState1(loginMember.getMemberId());
        bookService.updateRentedState1(bookIsbn);
        loanRepository.addLoan(loginMember, bookIsbn);
    }

    private boolean isMemberAlreadyHasBook(String memberId, String bookIsbn) {
        return true;
    }

    public void returnBook(Member loginMember, String bookIsbn){
        memberService.updateRentState0(loginMember.getMemberId());
        bookService.updateRentedState0(bookIsbn);
        loanRepository.returnBook(loginMember, bookIsbn);
    }
}
