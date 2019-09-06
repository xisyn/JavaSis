package io.github.xisyn.spring.data.service;

import io.github.xisyn.spring.data.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Optional;

public interface BookService {

    void save(Book book);

    Optional<Book> findById(Long id);

    Page<Book> findAtPage(int pageIndex, int rowCount, Sort.Direction direction, String fiel);

    List<Book> findSame(Book book);

    List<Book> findByYear(int year);

    List<Book> findByAuthor(Long author_id);

    List<Book> complexQuery();

    Book findByTitle(String title);
}
