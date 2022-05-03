package com.example.MasterproofTool.user.company;

import com.example.MasterproofTool.user.appUser.Appuser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("4")
@Setter
@Getter

public class Company extends Appuser {
    private String name;
    private double longitude;
    private double latitude;
    private String btwnummer;

    public Company(){}

    public Company(String firstName, String surname, Long keyId, String gsm, String email, String name, double longitude, double latitude, String btwnummer) {
        super(firstName, surname, keyId, gsm, email);
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.btwnummer = btwnummer;
    }

    public Company(String firstName, String surname, String gsm, String email, String name, double longitude, double latitude, String btwnummer) {
        super(firstName, surname, gsm, email);
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.btwnummer = btwnummer;
    }
    public Company(String firstName, String surname, String gsm, String email, String name, double longitude, double latitude, String btwnummer,String password) {
        super(firstName, surname, gsm, email,password);
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.btwnummer = btwnummer;
    }
    public Company(String firstName, String surname, String gsm, String email, String name, String btwnummer, String password) {
        super(firstName, surname, gsm, email, password);
        this.name = name;
        this.btwnummer = btwnummer;
    }



    @Override
    public String toString() {
        return "Company{" +
                ", name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", btwnummer=" + btwnummer +
                '}';
    }
}