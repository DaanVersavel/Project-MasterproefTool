package com.example.MasterproofTool.user;

import com.example.MasterproofTool.subject.Subject;
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
    Set<Campus> campussen=new HashSet<>();
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "Discipline_subject",
            joinColumns = {@JoinColumn(name="discipline_id")},
            inverseJoinColumns = {@JoinColumn(name="subject_id")}
    )
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
