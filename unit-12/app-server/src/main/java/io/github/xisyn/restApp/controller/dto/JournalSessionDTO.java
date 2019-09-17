package io.github.xisyn.restApp.controller.dto;

import io.github.xisyn.restApp.entity.Session;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.util.Locale;

public class JournalSessionDTO extends JournalItemDTO {
    public Long id;
    public String name;
    public LocalDateTime insertDate;
    public String result;

    public JournalSessionDTO() {
    }

    public JournalSessionDTO(Session session) {
        this.id = session.getId();
        this.name = session.getFullName();
        this.insertDate = session.getInsertDate();
        String formattedPercentage = new DecimalFormat("#0.##", new DecimalFormatSymbols(Locale.ENGLISH)).format(session.getCorrectQuestionsPercent());
        this.result = formattedPercentage;
    }
}
