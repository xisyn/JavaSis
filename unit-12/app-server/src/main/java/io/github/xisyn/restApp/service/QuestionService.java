package io.github.xisyn.restApp.service;

import io.github.xisyn.restApp.controller.dto.QuestionsItemDTO;

public interface QuestionService {
    QuestionsItemDTO createQuestion(QuestionsItemDTO dto);

    QuestionsItemDTO editQuestion(QuestionsItemDTO dto);
}
