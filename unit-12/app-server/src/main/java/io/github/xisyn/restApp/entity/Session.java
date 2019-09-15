package io.github.xisyn.restApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Session extends BaseEntity {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "correct_answers_percent")
    private Double correctQuestionsPercent;

    private Long insertDate;

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

    public Long getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Long insertDate) {
        this.insertDate = insertDate;
    }
}
