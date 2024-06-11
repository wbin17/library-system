package library.management.domain.book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
public class Book {
    private String isbn;
    private String title;
    private List<Author> authors;
    private int price;
    private boolean is_rented;
}
