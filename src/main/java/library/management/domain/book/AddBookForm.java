package library.management.domain.book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class AddBookForm {
    private String isbn;
    private String title;
    private int price;
}
