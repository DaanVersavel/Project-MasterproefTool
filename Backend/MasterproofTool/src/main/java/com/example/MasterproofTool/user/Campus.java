package com.example.MasterproofTool.user;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Campus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long campus_id;
    private String naam;
    @ManyToMany(mappedBy = "campussen")
    private Set<Discipline> disciplines=new HashSet<>();
    private double longitude;
    private double latitude;

    public Campus(Long campus_id, String naam, Set<Discipline> disciplines, double longitude, double latitude) {
        this.campus_id = campus_id;
        this.naam = naam;
        this.disciplines = disciplines;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Campus(String naam, Set<Discipline> disciplines, double longitude, double latitude) {
        this.naam = naam;
        this.disciplines = disciplines;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Campus() {
    }

    public void setCampus_id(Long campus_id) {
        this.campus_id = campus_id;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setDisciplines(Set<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Campus{" +
                "campus_id=" + campus_id +
                ", naam='" + naam + '\'' +
                ", disciplines=" + disciplines +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
