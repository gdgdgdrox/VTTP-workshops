package day21.books.repository;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import day21.books.model.Book;

@Repository
public class BookRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public List<Book> getBooks(){
        List<Book> books = new ArrayList<>();
        final String query = "select title, authors from book2018 where rating > 4 limit 10";
        SqlRowSet result = jdbcTemplate.queryForRowSet(query);
        while (result.next()){
            System.out.printf("title: %s | authors: %s\n", result.getString("title"), result.getString("authors"));
        }
        return books;
    }
}
