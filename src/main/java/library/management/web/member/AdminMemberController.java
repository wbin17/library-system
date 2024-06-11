package library.management.web.member;

import library.management.domain.book.Author;
import library.management.domain.book.Book;
import library.management.domain.book.BookAuthor;
import library.management.domain.member.Member;
import library.management.service.AuthorService;
import library.management.service.BookService;
import library.management.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminMemberController {
    private final MemberService memberService;
    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping("/add-asf4a6fv1a5f4a6")
    public String addAdminForm(@ModelAttribute("member") Member member){
        return "members/addAdminMemberForm";
    }

    @PostMapping("/add-asf4a6fv1a5f4a6")
    public String save(@ModelAttribute("member")Member member){
        memberService.adminSave(member);
        return "redirect:/";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "/members/accessDenied";
    }

    @GetMapping("/books")
    public String BookList(@RequestParam(required = false, name="title") String title,
                           Model model){
        List<Book> books = bookService.findAll(title);
        model.addAttribute("bookList", books);
        model.addAttribute("title", title);
        return "book/adminBookList";
    }

    @GetMapping("/books/add")
    public String addForm(@ModelAttribute Book book, Model model){
        List<Author> authors = authorService.authorList();
        model.addAttribute("authors", authors);
        return "/book/addBookForm";
    }
    @PostMapping("/books/add")
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

    @GetMapping("/books/{isbn}/edit")
    public String editForm(@PathVariable("isbn")String isbn, Model model){
        Book book = bookService.selectBookWithAuthors(isbn);
        List<Author> allAuthors = authorService.authorList();
        List<Author> availableAuthors = allAuthors.stream()
                .filter(author -> book.getAuthors().stream().noneMatch(a -> a.getAuthorId().equals(author.getAuthorId())))
                .collect(Collectors.toList());

        model.addAttribute("availableAuthors", availableAuthors);
        model.addAttribute("book", book);
        return "/book/editBookForm";
    }

    @PostMapping("/books/{isbn}/edit")
    public String editBook(@ModelAttribute("book") Book book,
                           @RequestParam(required = false, name = "currentAuthorIds")List<Integer> currentAuthorIds,
                           @RequestParam(required = false, name="newAuthorIds")List<Integer> newAuthorIds){
        List<BookAuthor> originAuthors =  bookService.findAuthorsByBookIsbn(book.getIsbn());

        List<Author> currentAuthors = new ArrayList<>();
        //수정전의 책 저자 정보
        if(currentAuthorIds != null) {
            currentAuthors = authorService.findAuthorsByAuthorIds(currentAuthorIds);
        }
        book.setAuthors(currentAuthors);

        //새로 추가될 저자
        if(newAuthorIds != null){
            List<Author> newAuthors = authorService.findAuthorsByAuthorIds(newAuthorIds);
            book.getAuthors().addAll(newAuthors);
        }

        bookService.editBook(book,originAuthors, currentAuthorIds, newAuthorIds);
        return "redirect:/";
    }

    @PostMapping("/books/{isbn}/delete")
    public String deleteBook(@PathVariable("isbn") String isbn,
                             @RequestParam(required = false, name="title") String title,
                             Model model){
        if (bookService.isBookRentedByIsbn(isbn)) {
            List<Book> books = bookService.findAll(title);
            model.addAttribute("bookList", books);
            model.addAttribute("isbn", isbn);
            model.addAttribute("message", "현재 대여중인 책이라 삭제 할 수 없습니다");
            return "book/adminBookList";
        }
        bookService.deleteBook(isbn);
        return "redirect:/admin/books";
    }

    @GetMapping("/authors/add")
    public String addForm(@ModelAttribute("author") Author author){
        return "/author/addAuthorForm";
    }

    @PostMapping("/authors/add")
    public String save(@ModelAttribute("author") Author author){
        authorService.addAuthor(author);
        return "redirect:/authors";
    }

}
