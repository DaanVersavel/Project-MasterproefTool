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
    private String companyName;
    private double longitude;
    private double latitude;
    private String btwnummer;

    public Company(){}

    public Company(String firstName, String surname, Long keyId, String gsm, String email, String companyName, double longitude, double latitude, String btwnummer) {
        super(firstName, surname, keyId, gsm, email);
        this.companyName = companyName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.btwnummer = btwnummer;
    }

    public Company(String firstName, String surname, String gsm, String email, String companyName, double longitude, double latitude, String btwnummer) {
        super(firstName, surname, gsm, email);
        this.companyName = companyName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.btwnummer = btwnummer;
    }
    public Company(String firstName, String surname, String gsm, String email, String companyName, double longitude, double latitude, String btwnummer,String password) {
        super(firstName, surname, gsm, email,password);
        this.companyName = companyName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.btwnummer = btwnummer;
    }
    public Company(String firstName, String surname, String gsm, String email, String companyName, String btwnummer, String password) {
        super(firstName, surname, gsm, email, password);
        this.companyName = companyName;
        this.btwnummer = btwnummer;
    }



    @Override
    public String toString() {
        return "Company{" +
                ", name='" + companyName + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", btwnummer=" + btwnummer +
                '}';
    }
}
