package io.github.xisyn.restApp.controller.dto;

import io.github.xisyn.restApp.entity.Question;
import io.github.xisyn.restApp.entity.SelectedAnswer;
import io.github.xisyn.restApp.entity.Session;

import java.util.List;
import java.util.stream.Collectors;

public class AnsweredQuestionDTO {
    public String id;

    public List<SessionQuestionAnswerDTO> sessionQuestionAnswers;

    public AnsweredQuestionDTO() {
    }

    public AnsweredQuestionDTO(Session session, List<SelectedAnswer> answers) {
        this.id = String.valueOf(session.getId());
        this.sessionQuestionAnswers = answers.stream().
                map(SessionQuestionAnswerDTO::new).
                collect(Collectors.toList());
    }

    public AnsweredQuestionDTO(Question question) {

    }

    public AnsweredQuestionDTO(AnsweredQuestionDTO answeredQuestionDTO) {
        this.id = answeredQuestionDTO.id;
        this.sessionQuestionAnswers = answeredQuestionDTO.sessionQuestionAnswers;
    }
}
