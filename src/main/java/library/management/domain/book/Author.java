package library.management.domain.book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Author {
    private Integer authorId;
    private String authorName;
    private String authorBirth;
    private List<Book> books;
}
