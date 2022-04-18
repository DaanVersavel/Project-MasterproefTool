package com.example.MasterproofTool.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("2")
@Getter
@Setter
public class Promotor extends Appuser {

    private String courseId;
    private boolean boost; //TODO wat als meerde onderwerpen?
    private String campus;
    private String discipline;

    public Promotor(){}

    public Promotor(String firstName, String surname, Long keyId, String gsm, String email, Long id, String courseId, boolean boost, String campus, String discipline) {
        super(firstName, surname, keyId, gsm, email);
        this.courseId = courseId;
        this.boost = boost;
        this.campus = campus;
        this.discipline = discipline;
    }

    public Promotor(String firstName, String surname, String gsm, String email, String courseId, boolean boost, String campus, String discipline) {
        super(firstName, surname, gsm, email);
        this.courseId = courseId;
        this.boost = boost;
        this.campus = campus;
        this.discipline = discipline;
    }

    public Promotor(String firstName, String surname, String gsm, String email, String courseId, String campus, String discipline, String password) {
        super(firstName, surname, gsm, email, password);
        this.courseId = courseId;
        this.campus = campus;
        this.discipline = discipline;
    }


}
