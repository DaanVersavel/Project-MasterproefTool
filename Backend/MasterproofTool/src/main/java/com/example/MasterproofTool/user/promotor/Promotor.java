package com.example.MasterproofTool.user.promotor;

import com.example.MasterproofTool.user.appUser.Appuser;
import com.example.MasterproofTool.user.campus.Campus;
import com.example.MasterproofTool.user.disciplines.Discipline;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("2")
@Getter
@Setter
public class Promotor extends Appuser {
    @ManyToOne()
    @JoinColumn(name="Campus_id")
    private Campus campus;
    @ManyToOne()
    @JoinColumn(name="discipline_id")
    private Discipline discipline;

    public Promotor(){}

    public Promotor(String firstName, String surname, Long keyId, String gsm, String email,  Campus campus, Discipline discipline) {
        super(firstName, surname, keyId, gsm, email);
        this.campus = campus;
        this.discipline = discipline;
    }

    public Promotor(String firstName, String surname, String gsm, String email, Campus campus, Discipline discipline) {
        super(firstName, surname, gsm, email);
        this.campus = campus;
        this.discipline = discipline;
    }

    public Promotor(String firstName, String surname, String gsm, String email,Campus campus, Discipline discipline, String password) {
        super(firstName, surname, gsm, email, password);
        this.campus = campus;
        this.discipline = discipline;
    }


}
