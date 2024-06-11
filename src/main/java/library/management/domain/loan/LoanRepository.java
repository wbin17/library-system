package library.management.domain.loan;

import library.management.domain.member.Member;
import library.management.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoanRepository {
    private final SqlSessionTemplate sql;

    public void addLoan(Member loginMember, String bookIsbn){
        Loan loan = new Loan();
        loan.setMemberId(loginMember.getMemberId());
        loan.setBookIsbn(bookIsbn);
        sql.insert("Loan.insertLoan", loan);
    }

    public void returnBook(Member member ,String bookIsbn){
        Loan loan = new Loan();
        loan.setBookIsbn(bookIsbn);
        loan.setMemberId(member.getMemberId());
        sql.delete("Loan.returnBook", loan);
    }
}
