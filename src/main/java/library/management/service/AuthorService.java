package library.management.service;

import library.management.domain.book.Author;
import library.management.domain.book.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public void addAuthor(Author author){
        authorRepository.saveAndGetId(author);
    }

    public List<Author> authorList(){
        return authorRepository.findAll();
    }

    public Author findByAuthorId(Integer authorId){
        return authorRepository.findByAuthorId(authorId);
    }

    public List<Author> findAuthorsByAuthorIds(List<Integer> authorIds){
        List<Author> authors = new ArrayList<>();
        for (Integer authorId : authorIds) {
            Author byAuthorId = authorRepository.findByAuthorId(authorId);
            authors.add(byAuthorId);
        }
        return authors;
    }

    public Author authorInfo(Integer authorId){
        return authorRepository.selectAuthorWithBooks(authorId);
    }
}
