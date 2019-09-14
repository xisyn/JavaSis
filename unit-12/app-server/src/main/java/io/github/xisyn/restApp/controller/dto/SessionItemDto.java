package io.github.xisyn.restApp.controller.dto;

import io.github.xisyn.restApp.entity.Session;

import java.util.List;

public class SessionItemDto extends JournalItemDTO {
    public String name;

    public List<AnsweredQuestionDTO> questionsList;

    public SessionItemDto() {
    }

    public SessionItemDto(Session session, List<AnsweredQuestionDTO> questionsList) {
        this.id = String.valueOf(session.getId());
        this.name = session.getFullName();
        this.questionsList = questionsList;
    }
}
