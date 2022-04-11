package com.example.MasterproofTool.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("3")
@Getter
@Setter
public class Coördinator extends Appuser {
    //@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String discipline;
    @ManyToOne
    @JoinColumn(name="campus_id")
    private Campus campus;

    public Coördinator(String firstName, String surname, Long keyId, String GSM, String email, String rol, Long id, String discipline, Campus campus) {
        super(firstName, surname, keyId, GSM, email);
        Id = id;
        this.discipline = discipline;
        this.campus = campus;
    }

    public Coördinator(String firstName, String surname, String GSM, String email, String discipline, Campus campus) {
        super(firstName, surname, GSM, email);
        this.discipline = discipline;
        this.campus = campus;
    }

    public Coördinator() {
    }

    public Coördinator(String firstname, String surname) {
        super(firstname,surname);
    }


    @Override
    public String toString() {
        return "Coördinator{" +
                "Id=" + Id +
                ", discipline='" + discipline + '\'' +
                ", campus=" + campus +
                '}';
    }
}
