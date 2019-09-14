package io.github.xisyn.restApp.entity;

import javax.persistence.*;

@Entity
public class SelectedAnswer extends BaseEntity {

    @JoinColumn(name = "answer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Answer answer;

    @JoinColumn(name = "session_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Session session;

    @Column(name = "is_selected")
    private Boolean isSelected;

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
