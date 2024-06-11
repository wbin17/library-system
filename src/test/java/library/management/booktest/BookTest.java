package library.management.booktest;

import library.management.domain.book.Author;
import library.management.domain.book.Book;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BookTest {

    @Test
    public void test(){
        Book book = new Book();
        book.setIsbn("dasd");
        book.setTitle("asd");
        book.setPrice(123);

//        Author author = new Author();
        List<String> authorNames = new ArrayList<>();
        authorNames.add("박원빈");
        authorNames.add("김연지");

        List<Author> authors = new ArrayList<>();
        for (String author1 : authorNames) {
            if(!author1.isEmpty()){
                Author author = new Author();
                author.setAuthorName(author1);
                authors.add(author);
            }
        }
        for (Author author1 : authors) {
            System.out.println("author1.getAuthorName() = " + author1.getAuthorName());
        }
    }
}
