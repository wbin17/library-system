package library.management.domain.book;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BookRepository {
    private final SqlSessionTemplate sql;

    public List<Book> findAll(String title){
        return sql.selectList("Book.findAll",title);
    }

    public void addBook(Book book){
        sql.insert("Book.insertBook", book);        //책정보 insert
        for (Author author : book.getAuthors()) {
            log.info("authorId = {}", author.getAuthorId()); //등록폼에서 입력한 저자리스트를
            BookAuthor bookAuthor = new BookAuthor();        //순회하면서
            bookAuthor.setIsbn(book.getIsbn());              //BookAuthor 테이블에
            bookAuthor.setAuthorId(author.getAuthorId());    //insert
            sql.insert("Book.insertBookAuthor", bookAuthor);
        }
    }

    public void editBook(Book book){
        sql.update("Book.updateBook", book);

    }

    public void updateRentedState1(String bookIsbn) {
        sql.update("Book.updateRentedState1", bookIsbn);
    }

    public void updateRentedState0(String bookIsbn) {
        sql.update("Book.updateRentedState0", bookIsbn);
    }

    public Book findByBookIsbn(String bookIsbn) {
        return sql.selectOne("Book.findByBookIsbn", bookIsbn);
    }

    public List<BookAuthor>  findAuthorsByBookIsbn(String isbn){
        return sql.selectList("Book.findAuthorsByBookIsbn", isbn);
    }

    public Book selectBookWithAuthors(String bookIsbn){
        return sql.selectOne("Book.selectBookWithAuthors", bookIsbn);
    }

    public void deleteBookAuthor(BookAuthor bookAuthor){
        sql.delete("Book.deleteBookAuthor", bookAuthor);
    }

    public void insertBookAuthor(BookAuthor bookAuthor){
        sql.insert("Book.insertBookAuthor", bookAuthor);
    }

    public void deleteBook(String isbn) {
        sql.delete("Book.deleteBook", isbn);
    }

    public boolean isBookRentedByIsbn(String isbn){
        return sql.selectOne("Book.isBookRentedByIsbn", isbn);
    }
}
