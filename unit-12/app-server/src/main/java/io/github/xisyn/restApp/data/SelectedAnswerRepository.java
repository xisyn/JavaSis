package io.github.xisyn.restApp.data;

import io.github.xisyn.restApp.entity.SelectedAnswer;
import io.github.xisyn.restApp.entity.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SelectedAnswerRepository extends CrudRepository<SelectedAnswer, Long> {
    List<SelectedAnswer> findBySession(Session session);
}
