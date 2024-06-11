package library.management.service;

import library.management.domain.book.Author;
import library.management.domain.book.Book;
import library.management.domain.book.BookAuthor;
import library.management.domain.book.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> findAll(String title) {
        return bookRepository.findAll(title);
    }

    public void addBook(Book book) {
        bookRepository.addBook(book);
    }

    public void editBook(Book book, List<BookAuthor> originAuthors, List<Integer> currentAuthorIds, List<Integer> newAuthorIds) {
        bookRepository.editBook(book);

        for (BookAuthor bookAuthor : originAuthors) {
            boolean isAuthorInBook = false;
            for (Author author : book.getAuthors()) {
                if (author.getAuthorId().equals(bookAuthor.getAuthorId())) {
                    isAuthorInBook = true;
                    break;
                }
            }
            if (!isAuthorInBook) {
                BookAuthor ba = new BookAuthor();
                ba.setIsbn(book.getIsbn());
                ba.setAuthorId(bookAuthor.getAuthorId());
                bookRepository.deleteBookAuthor(ba);
            }
        }

        if (newAuthorIds != null) {
            for (Integer authorId : newAuthorIds) {
                if (currentAuthorIds != null) {
                    if (!currentAuthorIds.contains(authorId)) {
                        BookAuthor ba = new BookAuthor();
                        ba.setIsbn(book.getIsbn());
                        ba.setAuthorId(authorId);
                        bookRepository.insertBookAuthor(ba);
                    }
                } else {
                    BookAuthor ba = new BookAuthor();
                    ba.setIsbn(book.getIsbn());
                    ba.setAuthorId(authorId);
                    bookRepository.insertBookAuthor(ba);
                }
            }
        }
    }

    public void updateRentedState1(String bookIsbn) {
        bookRepository.updateRentedState1(bookIsbn);
    }

    public void updateRentedState0(String bookIsbn) {
        bookRepository.updateRentedState0(bookIsbn);
    }

    public Book findByBookIsbn(String bookIsbn) {
        return bookRepository.findByBookIsbn(bookIsbn);
    }

    public List<BookAuthor> findAuthorsByBookIsbn(String isbn) {
        return bookRepository.findAuthorsByBookIsbn(isbn);
    }

    public Book selectBookWithAuthors(String bookIsbn) {
        return bookRepository.selectBookWithAuthors(bookIsbn);
    }

    public void deleteBook(String isbn) {
        bookRepository.deleteBook(isbn);
    }

    public boolean isBookRentedByIsbn(String isbn) {
        return bookRepository.isBookRentedByIsbn(isbn);
    }
}
