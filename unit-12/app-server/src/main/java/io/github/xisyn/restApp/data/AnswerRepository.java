package io.github.xisyn.restApp.data;

import io.github.xisyn.restApp.entity.Answer;
import io.github.xisyn.restApp.entity.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
    List<Answer> findByQuestion(Question question);
}
