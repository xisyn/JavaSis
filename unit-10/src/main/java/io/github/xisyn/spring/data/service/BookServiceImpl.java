package io.github.xisyn.spring.data.service;

import io.github.xisyn.spring.data.data.BookRepository;
import io.github.xisyn.spring.data.entity.Book;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void save(Book book) {
        this.bookRepository.save(book);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Page<Book> findAtPage(int pageIndex, int rowCount,
                                 Sort.Direction direction, String field) {
        PageRequest pageRequest =  PageRequest.of(pageIndex, rowCount,
                direction, field);
        return bookRepository.findAll(pageRequest);

    }

    @Override
    public List<Book> findSame(Book book) {
        return bookRepository.findAll(Example.of(book));
    }

    @Override
    public List<Book> findByYear(int year) {
        return null;
    }

    @Override
    public List<Book> findByAuthor(Long author_id) {
        return null;
    }

    @Override
    public List<Book> complexQuery() {
        return null;
    }

    @Override
    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title).orElseThrow(() -> new RuntimeException("Книги с таким заголовком нет"));
    }

}
