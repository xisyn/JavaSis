package io.github.xisyn.restApp.controller.dto;

import io.github.xisyn.restApp.entity.SelectedAnswer;

public class SessionQuestionAnswerDTO {
    public String id;

    public Boolean isSelected;

    public SessionQuestionAnswerDTO() {
    }

    public SessionQuestionAnswerDTO(SelectedAnswer answer) {
        this.id = String.valueOf(answer.getId());
        this.isSelected = answer.getSelected();
    }

    public SessionQuestionAnswerDTO(SessionQuestionAnswerDTO sessionQuestionAnswerDTO) {
        this.id = sessionQuestionAnswerDTO.id;
        this.isSelected = sessionQuestionAnswerDTO.isSelected;
    }
}
