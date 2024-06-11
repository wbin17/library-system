package library.management.web.book;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import library.management.domain.book.Author;
import library.management.domain.book.Book;
import library.management.domain.book.BookAuthor;
import library.management.domain.member.Member;
import library.management.domain.review.Review;
import library.management.service.AuthorService;
import library.management.service.BookService;
import library.management.service.ReviewService;
import library.management.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final ReviewService reviewService;


    @GetMapping
    public String bookList(@RequestParam(required = false , name = "title")String title,
                           Model model){
        log.info("title = {}", title);
        model.addAttribute("title", title);
        List<Book> bookList = bookService.findAll(title);
        model.addAttribute("bookList", bookList);
        return "/book/bookList";
    }

    @GetMapping("/{bookIsbn}")
    public String bookInfo(@PathVariable("bookIsbn")String bookIsbn,
                            HttpServletRequest request,
                           Model model){
        Member member = (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        Book byBookIsbn = bookService.selectBookWithAuthors(bookIsbn);

        List<Review> reviews = reviewService.findReviewByIsbn(bookIsbn);
        model.addAttribute("reviews", reviews);

        model.addAttribute("member", member);
        model.addAttribute("book", byBookIsbn);
        return "/book/bookInfo";
    }


    @GetMapping("/add")
    public String addForm(@ModelAttribute Book book, Model model){
        List<Author> authors = authorService.authorList();
        model.addAttribute("authors", authors);
        return "/book/addBookForm";
    }
    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book,
                          @RequestParam("authorIds")List<Integer> authorIds) {

        List<Author> authors = new ArrayList<>();


        for (Integer authorId : authorIds) {
            if (authorId != null) {
                Author byAuthorId = authorService.findByAuthorId(authorId);
                authors.add(byAuthorId);
            }
        }
        log.info("authors = {}", authors);
        book.setAuthors(authors);
        bookService.addBook(book);

        return "redirect:/";
    }



}
