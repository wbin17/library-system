package library.management.domain.loan;

import library.management.domain.book.Book;
import library.management.domain.member.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Loan {
    private String bookIsbn;
    private String memberId;
    private String loanDate;
    private String dueDate;
    private Member member;
    private Book book;
    private boolean is_returned;
}
