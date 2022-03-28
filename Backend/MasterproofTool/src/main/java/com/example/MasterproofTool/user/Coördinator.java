package com.example.MasterproofTool.user;

import javax.persistence.*;

@Entity
@DiscriminatorValue("3")

public class Coördinator extends Appuser {
    //@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String discipline;
    @ManyToOne
    @JoinColumn(name="campus_id")
    private Campus campus;

    public Coördinator(String firstName, String surname, Long keyId, int GSM, String email, String rol, Long id, String discipline, Campus campus) {
        super(firstName, surname, keyId, GSM, email);
        Id = id;
        this.discipline = discipline;
        this.campus = campus;
    }

    public Coördinator(String firstName, String surname, int GSM, String email, String discipline, Campus campus) {
        super(firstName, surname, GSM, email);
        this.discipline = discipline;
        this.campus = campus;
    }

    public Coördinator() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
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
