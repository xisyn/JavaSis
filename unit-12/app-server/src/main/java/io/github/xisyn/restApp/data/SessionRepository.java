package io.github.xisyn.restApp.data;

import io.github.xisyn.restApp.entity.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SessionRepository extends CrudRepository<Session, Long> {
    List<Session> findByFullNameContainingIgnoreCase(String search);
}
