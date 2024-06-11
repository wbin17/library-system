package library.management.domain.member;

import library.management.domain.book.Book;
import library.management.domain.loan.Loan;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Member {

    private String memberId;
    private String memberPass;
    private String memberName;
    private String memberBirth;
    private String phone;
    private boolean is_renting;
    //역할은 일반회원 디폴트값
    private MemberRole memberRole = MemberRole.user;
    private Loan loan;

}
