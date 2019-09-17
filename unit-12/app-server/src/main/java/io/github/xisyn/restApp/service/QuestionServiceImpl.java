package io.github.xisyn.restApp.service;

import io.github.xisyn.restApp.controller.dto.AnswerItemDTO;
import io.github.xisyn.restApp.controller.dto.QuestionsItemDTO;
import io.github.xisyn.restApp.data.AnswerRepository;
import io.github.xisyn.restApp.data.QuestionRepository;
import io.github.xisyn.restApp.entity.Answer;
import io.github.xisyn.restApp.entity.Question;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public QuestionsItemDTO createQuestion(QuestionsItemDTO dto) {
        Question question = new Question();
        question.setName(dto.name);
        questionRepository.save(question);

        saveAnswers(dto, question);

        return new QuestionsItemDTO(question, answerRepository.findByQuestion(question));
    }

    @Override
    public QuestionsItemDTO editQuestion(QuestionsItemDTO dto) {
        Question question = new Question();
        question.setId(Long.valueOf(dto.id));
        question.setName(dto.name);
        questionRepository.save(question);

        deleteAnswers(question);
        saveAnswers(dto, question);

        return new QuestionsItemDTO(question, answerRepository.findByQuestion(question));
    }

    @Override
    public List<QuestionsItemDTO> getQuestions() {
        List<QuestionsItemDTO> questionsItemDTOS = new ArrayList<>();
        for (Question question : questionRepository.findAll()) {
            List<Answer> answers = answerRepository.findByQuestion(question);
            QuestionsItemDTO dto = new QuestionsItemDTO(question, answers);
            questionsItemDTOS.add(dto);
        }
        return questionsItemDTOS;
    }

    private void saveAnswers(QuestionsItemDTO dto, Question question) {
        for (AnswerItemDTO answerDTO : dto.answers) {
            Answer answer = new Answer();
            answer.setName(answerDTO.answerText);
            answer.setCorrect(answerDTO.isCorrect);
            answer.setQuestion(question);
            answerRepository.save(answer);
        }
    }

    private void deleteAnswers(Question question) {
        for (Answer existedAnswer : answerRepository.findByQuestion(question)) {
            answerRepository.delete(existedAnswer);
        }
    }
}
