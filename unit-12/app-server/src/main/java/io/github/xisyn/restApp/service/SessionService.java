package io.github.xisyn.restApp.service;

import io.github.xisyn.restApp.controller.dto.SessionItemDto;

public interface SessionService {
    String createSession(SessionItemDto dto);

}
