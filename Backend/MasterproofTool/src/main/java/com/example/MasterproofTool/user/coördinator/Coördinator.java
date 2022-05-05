package com.example.MasterproofTool.user.coördinator;

import com.example.MasterproofTool.user.appUser.Appuser;
import com.example.MasterproofTool.user.campus.Campus;
import com.example.MasterproofTool.user.disciplines.Discipline;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("3")
@Getter
@Setter
public class Coördinator extends Appuser {
    @ManyToOne()
    @JoinColumn(name="discipline_id")
    private Discipline discipline;
    @ManyToOne
    @JoinColumn(name="campus_id")
    private Campus campus;

    public Coördinator(String firstName, String surname, Long keyId, String gsm, String email, Discipline discipline, Campus campus) {
        super(firstName, surname, keyId, gsm, email);
        this.discipline = discipline;
        this.campus = campus;
    }

    public Coördinator(String firstName, String surname, String gsm, String email, Discipline discipline, Campus campus) {
        super(firstName, surname, gsm, email);
        this.discipline = discipline;
        this.campus = campus;
    }
    public Coördinator(String firstName, String surname, String gsm, String email, Discipline discipline,String password) {
        super(firstName, surname, gsm, email,password);
        this.discipline = discipline;

    }
    public Coördinator(String firstName, String surname, String gsm, String email, Discipline discipline, Campus campus,String password) {
        super(firstName, surname, gsm, email,password);
        this.discipline = discipline;

    }

    public Coördinator() {
    }

    public Coördinator(String firstname, String surname) {
        super(firstname,surname);
    }


    @Override
    public String toString() {
        return "Coördinator{" +
                ", discipline='" + discipline + '\'' +
                ", campus=" + campus +
                '}';
    }
}
