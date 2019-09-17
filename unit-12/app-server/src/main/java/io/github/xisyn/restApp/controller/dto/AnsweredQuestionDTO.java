package io.github.xisyn.restApp.controller.dto;

import java.util.List;

public class AnsweredQuestionDTO {
    public String id;

    public List<SessionQuestionAnswerDTO> answersList;

    public AnsweredQuestionDTO() {
    }

    public AnsweredQuestionDTO(AnsweredQuestionDTO answeredQuestionDTO) {
        this.id = answeredQuestionDTO.id;
        this.answersList = answeredQuestionDTO.answersList;
    }

    public AnsweredQuestionDTO(String id, List<SessionQuestionAnswerDTO> answersList) {
        this.id = id;
        this.answersList = answersList;
    }
}
