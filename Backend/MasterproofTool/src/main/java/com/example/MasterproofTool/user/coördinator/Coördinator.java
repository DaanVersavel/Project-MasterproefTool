package com.example.MasterproofTool.user.coördinator;

import com.example.MasterproofTool.user.appUser.Appuser;
import com.example.MasterproofTool.user.campus.Campus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("3")
@Getter
@Setter
public class Coördinator extends Appuser {
    private String discipline;
    @ManyToOne
    @JoinColumn(name="campus_id")
    private Campus campus;

    public Coördinator(String firstName, String surname, Long keyId, String gsm, String email, String discipline, Campus campus) {
        super(firstName, surname, keyId, gsm, email);
        this.discipline = discipline;
        this.campus = campus;
    }

    public Coördinator(String firstName, String surname, String gsm, String email, String discipline, Campus campus) {
        super(firstName, surname, gsm, email);
        this.discipline = discipline;
        this.campus = campus;
    }
    public Coördinator(String firstName, String surname, String gsm, String email, String discipline,String password) {
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
