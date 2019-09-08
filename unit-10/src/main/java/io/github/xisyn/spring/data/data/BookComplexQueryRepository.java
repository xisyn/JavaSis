package io.github.xisyn.spring.data.data;

import io.github.xisyn.spring.data.entity.Book;

import java.util.List;

public interface BookComplexQueryRepository {
    List<Book> complexQueryMethod();
}