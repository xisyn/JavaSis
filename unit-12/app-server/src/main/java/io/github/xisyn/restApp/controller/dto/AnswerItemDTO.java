package io.github.xisyn.restApp.controller.dto;

import io.github.xisyn.restApp.entity.Answer;

public class AnswerItemDTO extends JournalItemDTO {
    public String answerText;
    public Boolean isCorrect;

    public AnswerItemDTO() {
    }

    public AnswerItemDTO(Answer answer) {
        this.id = answer.getId().toString();
        this.answerText = answer.getName();
        this.isCorrect = answer.getCorrect();
    }
}
