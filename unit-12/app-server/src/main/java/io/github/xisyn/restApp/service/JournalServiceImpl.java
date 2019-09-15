package io.github.xisyn.restApp.service;

import io.github.xisyn.restApp.controller.dto.JournalItemDTO;
import io.github.xisyn.restApp.controller.dto.JournalRequestDTO;
import io.github.xisyn.restApp.controller.dto.JournalSessionDTO;
import io.github.xisyn.restApp.controller.dto.QuestionsItemDTO;
import io.github.xisyn.restApp.data.*;
import io.github.xisyn.restApp.entity.BaseEntity;
import io.github.xisyn.restApp.entity.Journal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class JournalServiceImpl implements JournalService {

    public static final String QUESTIONS_JOURNAL_ID = "questions";
    public static final String SESSIONS_JOURNAL_ID = "sessions";

    private final JournalRepository journalRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final SessionRepository sessionRepository;

    public JournalServiceImpl(JournalRepository journalRepository,
                              QuestionRepository questionRepository,
                              AnswerRepository answerRepository,
                              SessionRepository sessionRepository,
                              SelectedAnswerRepository selectedAnswerRepository) {
        this.journalRepository = journalRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Journal getJournal(String id) {
        return journalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("There is no journal with id: %s", id)));
    }

    @Override
    public List<? extends JournalItemDTO> getJournalRows(String id, JournalRequestDTO req) {
        List<? extends JournalItemDTO> collection;
        switch (id) {
            case QUESTIONS_JOURNAL_ID:
                collection = getCollection(
                        req.search,
                        questionRepository::findByNameContainingIgnoreCase,
                        q -> new QuestionsItemDTO(
                                q,
                                answerRepository.findByQuestion(q)));
                break;

            case SESSIONS_JOURNAL_ID:
                collection = getCollection(
                        req.search,
                        sessionRepository::findByFullNameContainingIgnoreCase,
                        JournalSessionDTO::new);
                break;

            default:
                throw new RuntimeException();
        }

        return collection;
    }

    private <T extends BaseEntity, U extends JournalItemDTO> List<U> getCollection(
            String search,
            Function<String, List<T>> finder,
            Function<T, U> mapper
    ) {
        return finder.apply(search)
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    /*private List<AnsweredQuestionDTO> getAnsweredQuestionDTO(Session session) {
        List<AnsweredQuestionDTO> answeredQuestionDTOS = new ArrayList<>();
        List<SelectedAnswer> selectedAnswers = selectedAnswerRepository.findBySession(session);

        List<Question> questions = selectedAnswers.stream().
                map(selectedAnswer -> selectedAnswer.getAnswer().getQuestion().getId()).
                distinct().
                map(id -> questionRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException(String.format("There is no question with id: %s", id)))).
                collect(Collectors.toList());

        for (Question question : questions) {
            List<SessionQuestionAnswerDTO> sessionQuestionAnswerDTOS = answerRepository.findByQuestion(question).
                    stream().
                    map(answer -> selectedAnswerRepository.findByAnswerId(answer.getId())).
                    map(SessionQuestionAnswerDTO::new).
                    collect(Collectors.toList());

            AnsweredQuestionDTO dto = new AnsweredQuestionDTO(String.valueOf(question.getId()), sessionQuestionAnswerDTOS);
            answeredQuestionDTOS.add(dto);
        }

        return answeredQuestionDTOS;
    }*/
}
