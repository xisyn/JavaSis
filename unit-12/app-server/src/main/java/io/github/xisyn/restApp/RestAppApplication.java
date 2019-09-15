package io.github.xisyn.restApp;

import io.github.xisyn.restApp.data.JournalRepository;
import io.github.xisyn.restApp.entity.Journal;
import io.github.xisyn.restApp.service.JournalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RestAppApplication {

	@Autowired
	private JournalRepository journalRepository;

	public static void main(String[] args) {
		SpringApplication.run(RestAppApplication.class, args);
	}

	@PostConstruct
	private void initData() {
		Journal questionJournal = new Journal();
		questionJournal.setId(JournalServiceImpl.QUESTIONS_JOURNAL_ID);
		questionJournal.setName("Вопросы");
		questionJournal.setDefaultPageSize(15L);
		journalRepository.save(questionJournal);

		Journal sessionJournal = new Journal();
		sessionJournal.setId(JournalServiceImpl.SESSIONS_JOURNAL_ID);
		sessionJournal.setName("Сессии");
		sessionJournal.setDefaultPageSize(15L);
		journalRepository.save(sessionJournal);
	}
}
