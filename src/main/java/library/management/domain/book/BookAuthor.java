package library.management.domain.book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class BookAuthor {
    private String isbn;
    private Integer authorId;
}
