package io.github.xisyn.restApp.controller.dto;

import io.github.xisyn.restApp.entity.Session;

import java.util.List;
import java.util.stream.Collectors;

public class SessionItemDto extends JournalItemDTO {
    public String name;

    public List<AnsweredQuestionDTO> answeredQuestions;

    public SessionItemDto() {
    }

    public SessionItemDto(Session session, List<AnsweredQuestionDTO> answeredQuestionDTOS) {
        this.id = String.valueOf(session.getId());
        this.name = session.getFullName();
        this.answeredQuestions = answeredQuestionDTOS.stream()
                .map(AnsweredQuestionDTO::new)
                .collect(Collectors.toList());
    }
}
