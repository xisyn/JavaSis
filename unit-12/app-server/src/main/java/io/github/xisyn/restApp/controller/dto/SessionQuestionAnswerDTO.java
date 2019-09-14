package io.github.xisyn.restApp.controller.dto;

public class SessionQuestionAnswerDTO {
    public String id;

    public Boolean isSelected;

    public SessionQuestionAnswerDTO() {
    }

    public SessionQuestionAnswerDTO(SessionQuestionAnswerDTO sessionQuestionAnswerDTO) {
        this.id = sessionQuestionAnswerDTO.id;
        this.isSelected = sessionQuestionAnswerDTO.isSelected;
    }
}
