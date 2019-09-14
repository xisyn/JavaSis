package io.github.xisyn.restApp.controller;

import io.github.xisyn.restApp.controller.dto.QuestionsItemDTO;
import io.github.xisyn.restApp.controller.dto.SessionItemDto;
import io.github.xisyn.restApp.service.QuestionService;
import io.github.xisyn.restApp.service.SessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/session")
public class SessionRestController {

    private final SessionService sessionService;
    private final QuestionService questionService;

    public SessionRestController(SessionService sessionService, QuestionService questionService) {
        this.sessionService = sessionService;
        this.questionService = questionService;
    }

    //В ответ получаем строку - количество набранных процентов
    @PostMapping
    public String create(@RequestBody SessionItemDto dto) {
        return sessionService.createSession(dto);
    }

    @GetMapping("questions-new")
    public List<QuestionsItemDTO> getQuestions() {
        return questionService.getQuestions();
    }

}
