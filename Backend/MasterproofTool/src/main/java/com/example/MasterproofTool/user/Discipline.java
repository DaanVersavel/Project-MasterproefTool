package com.example.MasterproofTool.user;

import com.example.MasterproofTool.subject.Subject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long discipline_id;
    private String naam;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Discipline_Campus",
            joinColumns = {@JoinColumn(name="discipline_id")},
            inverseJoinColumns = {@JoinColumn(name="campus_id")}
    )
    Set<Campus> campussen=new HashSet<>();
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Discioline_subject",
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

    public Discipline(String naam, Set<Campus> campussen, Set<Subject> subjects) {
        this.naam = naam;
        this.campussen = campussen;
        this.subjects = subjects;
    }

    public Discipline() {
    }

    public long getDiscipline_id() {
        return discipline_id;
    }

    public void setDiscipline_id(long discipline_id) {
        this.discipline_id = discipline_id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Set<Campus> getCampussen() {
        return campussen;
    }

    public void setCampussen(Set<Campus> campussen) {
        this.campussen = campussen;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
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
