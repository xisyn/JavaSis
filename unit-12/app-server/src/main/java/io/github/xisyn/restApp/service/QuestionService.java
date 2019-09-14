package io.github.xisyn.restApp.service;

import io.github.xisyn.restApp.controller.dto.QuestionsItemDTO;

import java.util.List;

public interface QuestionService {
    QuestionsItemDTO createQuestion(QuestionsItemDTO dto);

    QuestionsItemDTO editQuestion(QuestionsItemDTO dto);

    List<QuestionsItemDTO> getQuestions();
}
