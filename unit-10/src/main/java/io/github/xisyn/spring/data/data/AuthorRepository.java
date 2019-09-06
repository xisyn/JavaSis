package io.github.xisyn.spring.data.data;

import io.github.xisyn.spring.data.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
