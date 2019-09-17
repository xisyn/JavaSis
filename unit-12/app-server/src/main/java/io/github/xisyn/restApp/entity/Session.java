package io.github.xisyn.restApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Session extends BaseEntity {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "correct_answers_percent")
    private Double correctQuestionsPercent;

    @Column(name = "insert_date")
    private LocalDateTime insertDate;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Double getCorrectQuestionsPercent() {
        return correctQuestionsPercent;
    }

    public void setCorrectQuestionsPercent(Double correctQuestionsPercent) {
        this.correctQuestionsPercent = correctQuestionsPercent;
    }

    public LocalDateTime getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDateTime insertDate) {
        this.insertDate = insertDate;
    }
}
