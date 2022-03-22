package com.example.MasterproofTool.user;

import javax.persistence.*;

@Entity
@DiscriminatorValue("2")

public class Promotor extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private Long courseId;
    private boolean boost;
    private String campus;
    private String discipline;

    public Promotor(){}

    public Promotor(String firstName, String surname, Long keyId, int GSM, String email, Long id, Long courseId, boolean boost, String campus, String discipline) {
        super(firstName, surname, keyId, GSM, email);
        Id = id;
        this.courseId = courseId;
        this.boost = boost;
        this.campus = campus;
        this.discipline = discipline;
    }

    public Promotor(String firstName, String surname, int GSM, String email, Long courseId, boolean boost, String campus, String discipline) {
        super(firstName, surname, GSM, email);
        this.courseId = courseId;
        this.boost = boost;
        this.campus = campus;
        this.discipline = discipline;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public boolean isBoost() {
        return boost;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public void setBoost(boolean boost) {
        this.boost = boost;
    }

}
