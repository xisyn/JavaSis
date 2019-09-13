package io.github.xisyn.restApp.data;

import io.github.xisyn.restApp.entity.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Long> {

    List<Question> findByNameContainingIgnoreCase(String search);
}
