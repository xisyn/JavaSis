package io.github.xisyn.restApp.controller;

import io.github.xisyn.restApp.controller.dto.QuestionsItemDTO;
import io.github.xisyn.restApp.service.QuestionService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/question")
public class QuestionRestController {

    private final QuestionService questionService;

    public QuestionRestController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("create")
    public QuestionsItemDTO create(@RequestBody QuestionsItemDTO dto) {
        return questionService.createQuestion(dto);
    }

    @PutMapping("edit")
    public QuestionsItemDTO edit(@RequestBody QuestionsItemDTO dto) {
        return questionService.editQuestion(dto);
    }
}
