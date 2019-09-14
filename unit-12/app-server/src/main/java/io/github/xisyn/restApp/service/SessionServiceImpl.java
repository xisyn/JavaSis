package io.github.xisyn.restApp.service;

import io.github.xisyn.restApp.controller.dto.AnsweredQuestionDTO;
import io.github.xisyn.restApp.controller.dto.SessionItemDto;
import io.github.xisyn.restApp.controller.dto.SessionQuestionAnswerDTO;
import io.github.xisyn.restApp.data.AnswerRepository;
import io.github.xisyn.restApp.data.QuestionRepository;
import io.github.xisyn.restApp.data.SelectedAnswerRepository;
import io.github.xisyn.restApp.data.SessionRepository;
import io.github.xisyn.restApp.entity.Question;
import io.github.xisyn.restApp.entity.SelectedAnswer;
import io.github.xisyn.restApp.entity.Session;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final SelectedAnswerRepository selectedAnswerRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public SessionServiceImpl(SessionRepository sessionRepository,
                              SelectedAnswerRepository selectedAnswerRepository,
                              AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.sessionRepository = sessionRepository;
        this.selectedAnswerRepository = selectedAnswerRepository;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public String createSession(SessionItemDto dto) {
        Session session = new Session();
        session.setFullName(dto.name);

        for (AnsweredQuestionDTO answeredQuestionDTO : dto.questionsList) {
            for (SessionQuestionAnswerDTO sessionQuestionAnswerDTO : answeredQuestionDTO.answersList) {
                SelectedAnswer answer = new SelectedAnswer();

                answer.setSelected(sessionQuestionAnswerDTO.isSelected);
                answer.setSession(session);
                answer.setAnswer(answerRepository.findById(Long.valueOf(sessionQuestionAnswerDTO.id))
                        .orElseThrow(() -> new RuntimeException(String.format("Нет ответа с таким id: %s", String.valueOf(sessionQuestionAnswerDTO.id)))));

                selectedAnswerRepository.save(answer);
            }
        }

        double numberOfQuestion = questionRepository.count();
        double numberOfCorrectQuestions = 0;

        List<Question> questions = new ArrayList<>();
        questionRepository.findAll().forEach(questions::add);

        for (Question question : questions) {
            List<SelectedAnswer> selectedAnswers = answerRepository.findByQuestion(question).
                    stream().
                    map(answer -> selectedAnswerRepository.findByAnswerId(answer.getId())).
                    filter(SelectedAnswer::getSelected).
                    collect(Collectors.toList());

            List<SelectedAnswer> inCorrectAnswers = selectedAnswers.stream().
                    filter(selectedAnswer -> answerRepository.findById(selectedAnswer.
                            getAnswer().
                            getId()).
                            orElseThrow(() -> new RuntimeException(String.format("Не найден ответ с таким id: %s", String.valueOf(selectedAnswer.getAnswer().getId())))).
                            getCorrect().
                            equals(false)).
                    collect(Collectors.toList());

            if (inCorrectAnswers.size() == 0) {
                numberOfCorrectQuestions++;
            }
        }

        double correctQuestionsPercent = (numberOfCorrectQuestions / numberOfQuestion) * 100;

        session.setCorrectQuestionsPercent(correctQuestionsPercent);
        sessionRepository.save(session);
        //В ответ получаем строку - количество набранных процентов
        String formattedPercentage = new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.ENGLISH)).format(correctQuestionsPercent);
        return formattedPercentage;
    }

}
