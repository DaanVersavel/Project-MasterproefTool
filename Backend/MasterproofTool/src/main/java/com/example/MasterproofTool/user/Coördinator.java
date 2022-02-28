package com.example.MasterproofTool.user;
import javax.persistence.*;

@Entity
@DiscriminatorValue("3")

public class Coördinator extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String discipline;

    public Coördinator(String firstName, String surname, Long keyId, int GSM, String email, String rol, Long id, String discipline) {
        super(firstName, surname, keyId, GSM, email, rol);
        Id = id;
        this.discipline = discipline;
    }

    public Coördinator(String firstName, String surname, int GSM, String email, String rol, String discipline) {
        super(firstName, surname, GSM, email, rol);
        this.discipline = discipline;
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
}
