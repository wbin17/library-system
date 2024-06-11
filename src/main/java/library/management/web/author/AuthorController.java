package library.management.web.author;

import library.management.domain.book.Author;
import library.management.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/authors")
@Slf4j
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public String authorList(Model model){
        List<Author> authors = authorService.authorList();
        model.addAttribute("authors", authors);
        return "/author/authorList";
    }

    @GetMapping("/{authorId}")
    public String authorInfo(@PathVariable("authorId")Integer authorId,
                             Model model){
        Author author = authorService.authorInfo(authorId);
        model.addAttribute("author", author);

        return "/author/authorInfo";
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute("author") Author author){
        return "/author/addAuthorForm";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("author") Author author){
        authorService.addAuthor(author);
        return "redirect:/authors";
    }

}
