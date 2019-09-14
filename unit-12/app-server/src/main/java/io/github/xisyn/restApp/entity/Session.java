package io.github.xisyn.restApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Session extends BaseEntity {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "correct_answers_percent")
    private Double correctAnswersPercent;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Double getCorrectAnswersPercent() {
        return correctAnswersPercent;
    }

    public void setCorrectAnswersPercent(Double correctAnswersPercent) {
        this.correctAnswersPercent = correctAnswersPercent;
    }
}
