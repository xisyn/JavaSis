package io.github.xisyn.restApp.service;

import io.github.xisyn.restApp.controller.dto.AnsweredQuestionDTO;
import io.github.xisyn.restApp.controller.dto.SessionItemDto;
import io.github.xisyn.restApp.controller.dto.SessionQuestionAnswerDTO;
import io.github.xisyn.restApp.data.AnswerRepository;
import io.github.xisyn.restApp.data.QuestionRepository;
import io.github.xisyn.restApp.data.SelectedAnswerRepository;
import io.github.xisyn.restApp.data.SessionRepository;
import io.github.xisyn.restApp.entity.*;
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
            saveSelectedAnswers(session, answeredQuestionDTO);
        }

        List<Question> questions = new ArrayList<>();
        questionRepository.findAll().forEach(questions::add);

        double numberOfCorrectQuestions = getNumberOfCorrectQuestions(questions, session);
        double numberOfQuestion = questionRepository.count();

        double correctQuestionsPercent = (numberOfCorrectQuestions / numberOfQuestion) * 100;
        session.setCorrectQuestionsPercent(correctQuestionsPercent);

        session.setInsertDate(System.currentTimeMillis());

        sessionRepository.save(session);

        String formattedPercentage = new DecimalFormat("#0.##", new DecimalFormatSymbols(Locale.ENGLISH)).format(correctQuestionsPercent);
        return formattedPercentage;
    }

    private double getNumberOfCorrectQuestions(List<Question> questions, Session session) {
        double numberOfCorrectQuestions = 0;

        List<SelectedAnswer> sessionSelectedAnswers = selectedAnswerRepository.findBySession(session);

        for (Question question : questions) {
            List<Answer> questionAnswers = answerRepository.findByQuestion(question);

            List<Long> answerIds = questionAnswers.stream()
                    .map(Answer::getId)
                    .collect(Collectors.toList());

            List<SelectedAnswer> questionSelectedAnswers = sessionSelectedAnswers.stream()
                    .filter(selectedAnswer -> answerIds.contains(selectedAnswer.getAnswer().getId()))
                    .filter(SelectedAnswer::getSelected)
                    .collect(Collectors.toList());

            List<SelectedAnswer> inCorrectAnswers = questionSelectedAnswers.stream().
                    filter(selectedAnswer -> selectedAnswer.getAnswer().getCorrect().equals(false))
                    .collect(Collectors.toList());

            if (inCorrectAnswers.size() == 0) {
                numberOfCorrectQuestions++;
            }
        }
        return numberOfCorrectQuestions;
    }

    private void saveSelectedAnswers(Session session, AnsweredQuestionDTO answeredQuestionDTO) {
        for (SessionQuestionAnswerDTO sessionQuestionAnswerDTO : answeredQuestionDTO.answersList) {
            SelectedAnswer answer = new SelectedAnswer();

            answer.setSelected(sessionQuestionAnswerDTO.isSelected);
            answer.setSession(session);
            answer.setAnswer(answerRepository.findById(Long.valueOf(sessionQuestionAnswerDTO.id))
                    .orElseThrow(() -> new RuntimeException(String.format("There is no answer with id: %s", String.valueOf(sessionQuestionAnswerDTO.id)))));

            selectedAnswerRepository.save(answer);
        }
    }
}
