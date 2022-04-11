package com.example.MasterproofTool.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("2")
@Getter
@Setter
public class Promotor extends Appuser {
    //@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private Long courseId;
    private boolean boost;
    private String campus;
    private String discipline;

    public Promotor(){}

    public Promotor(String firstName, String surname, Long keyId, String GSM, String email, Long id, Long courseId, boolean boost, String campus, String discipline) {
        super(firstName, surname, keyId, GSM, email);
        Id = id;
        this.courseId = courseId;
        this.boost = boost;
        this.campus = campus;
        this.discipline = discipline;
    }

    public Promotor(String firstName, String surname, String GSM, String email, Long courseId, boolean boost, String campus, String discipline) {
        super(firstName, surname, GSM, email);
        this.courseId = courseId;
        this.boost = boost;
        this.campus = campus;
        this.discipline = discipline;
    }


}
