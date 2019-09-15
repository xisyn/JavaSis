package io.github.xisyn.restApp.controller.dto;

import io.github.xisyn.restApp.entity.SelectedAnswer;

public class SessionQuestionAnswerDTO {
    public String id;

    public Boolean isSelected;

    public SessionQuestionAnswerDTO() {
    }

    public SessionQuestionAnswerDTO(SessionQuestionAnswerDTO sessionQuestionAnswerDTO) {
        this.id = sessionQuestionAnswerDTO.id;
        this.isSelected = sessionQuestionAnswerDTO.isSelected;
    }

    public SessionQuestionAnswerDTO(SelectedAnswer selectedAnswer) {
        this.id = String.valueOf(selectedAnswer.getId());
        this.isSelected = selectedAnswer.getSelected();
    }
}
