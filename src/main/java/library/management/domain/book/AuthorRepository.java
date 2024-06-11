package library.management.domain.book;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorRepository {
    private final SqlSessionTemplate sql;

    public int saveAndGetId(Author author){
         return sql.insert("Author.insertAuthor", author);
    }

    public List<Author> findAll() {
        return sql.selectList("Author.findAll");
    }

    public Author findByAuthorId(Integer authorId) {
        return sql.selectOne("Author.findByAuthorId", authorId);
    }

    public Author selectAuthorWithBooks(Integer authorId) {
        return sql.selectOne("Author.selectAuthorWithBooks", authorId);
    }
}
