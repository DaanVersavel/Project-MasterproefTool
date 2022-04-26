package com.example.MasterproofTool.user.campus;

import com.example.MasterproofTool.user.disciplines.Discipline;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Campus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long campus_id;
    private String name;
    @ManyToMany(mappedBy = "campussen")
    private Set<Discipline> disciplines=new HashSet<>();
    private double longitude;
    private double latitude;

    public Campus(Long campus_id, String name, Set<Discipline> disciplines, double longitude, double latitude) {
        this.campus_id = campus_id;
        this.name = name;
        this.disciplines = disciplines;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Campus(String name, Set<Discipline> disciplines, double longitude, double latitude) {
        this.name = name;
        this.disciplines = disciplines;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Campus(String name, double latitude, double longitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Campus() {
    }
    public void addDisciplines(Discipline dis){
        disciplines.add(dis);
    }


    @Override
    public String toString() {
        return "Campus{" +
                "campus_id=" + campus_id +
                ", naam='" + name + '\'' +
                ", disciplines=" + disciplines +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
