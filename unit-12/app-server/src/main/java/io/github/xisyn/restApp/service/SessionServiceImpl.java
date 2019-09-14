package io.github.xisyn.restApp.service;

import io.github.xisyn.restApp.controller.dto.SessionItemDto;
import io.github.xisyn.restApp.data.SelectedAnswerRepository;
import io.github.xisyn.restApp.data.SessionRepository;
import io.github.xisyn.restApp.entity.Session;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final SelectedAnswerRepository selectedAnswerRepository;

    public SessionServiceImpl(SessionRepository sessionRepository, SelectedAnswerRepository selectedAnswerRepository) {
        this.sessionRepository = sessionRepository;
        this.selectedAnswerRepository = selectedAnswerRepository;
    }

    @Override
    public SessionItemDto createSession(SessionItemDto dto) {
        Session session = new Session();
        session.setFullName(dto.name);



        double correctAnswersPercent = 0;
        session.setCorrectAnswersPercent(correctAnswersPercent);
        sessionRepository.save(session);
        //В ответ получаем строку - количество набранных процентов
        return null;
    }

}
