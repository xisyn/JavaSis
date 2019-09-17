package io.github.xisyn.restApp.service;

import io.github.xisyn.restApp.controller.dto.JournalItemDTO;
import io.github.xisyn.restApp.controller.dto.JournalRequestDTO;
import io.github.xisyn.restApp.entity.Journal;

import java.util.List;

public interface JournalService {
    Journal getJournal(String id);

    List<? extends JournalItemDTO> getJournalRows(String id, JournalRequestDTO req);
}
