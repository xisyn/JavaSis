package io.github.xisyn.restApp.data;

import io.github.xisyn.restApp.entity.Journal;
import org.springframework.data.repository.CrudRepository;

public interface JournalRepository extends CrudRepository<Journal, String> {
}
