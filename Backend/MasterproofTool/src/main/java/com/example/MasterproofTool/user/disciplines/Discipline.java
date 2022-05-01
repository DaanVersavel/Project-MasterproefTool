package com.example.MasterproofTool.user.disciplines;

import com.example.MasterproofTool.subject.Subject;
import com.example.MasterproofTool.user.campus.Campus;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long discipline_id;
    private String naam;

    @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinTable(
            name = "Discipline_Campus",
            joinColumns = {@JoinColumn(name="discipline_id")},
            inverseJoinColumns = {@JoinColumn(name="campus_id")}
    )
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "campus_id")
    @JsonIdentityReference(alwaysAsId = true)
    Set<Campus> campussen=new HashSet<>();
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "Discipline_subject",
            joinColumns = {@JoinColumn(name="discipline_id")},
            inverseJoinColumns = {@JoinColumn(name="subject_id")}
    )
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    Set<Subject> subjects= new HashSet<>();

    public Discipline(long discipline_id, String naam, Set<Campus> campussen, Set<Subject> subjects) {
        this.discipline_id = discipline_id;
        this.naam = naam;
        this.campussen = campussen;
        this.subjects = subjects;
    }
    public Discipline( String naam ) {
        this.naam = naam;
    }

    public Discipline(String naam, Set<Campus> campussen, Set<Subject> subjects) {
        this.naam = naam;
        this.campussen = campussen;
        this.subjects = subjects;
    }
    public void addCampus(Campus c) {
        campussen.add(c);
    }



    public Discipline() {
    }


    @Override
    public String toString() {
        return "Discipline{" +
                "discipline_id=" + discipline_id +
                ", naam='" + naam + '\'' +
                ", campussen=" + campussen +
                ", subjects=" + subjects +
                '}';
    }
}
