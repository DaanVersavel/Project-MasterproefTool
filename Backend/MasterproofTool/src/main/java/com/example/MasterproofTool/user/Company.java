package com.example.MasterproofTool.user;

import javax.persistence.*;

@Entity
@DiscriminatorValue("4")

public class Company extends Appuser {
    //@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double longitude;
    private double latitude;
    private int btwnummer;

    public Company(){}

    public Company(String firstName, String surname, Long keyId, String GSM, String email,  Long id, String name, double longitude, double latitude, int btwnummer) {
        super(firstName, surname, keyId, GSM, email);
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.btwnummer = btwnummer;
    }

    public Company(String firstName, String surname, String GSM, String email, String name, double longitude, double latitude, int btwnummer) {
        super(firstName, surname, GSM, email);
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.btwnummer = btwnummer;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getBtwnummer() {
        return btwnummer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setBtwnummer(int btwnummer) {
        this.btwnummer = btwnummer;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", btwnummer=" + btwnummer +
                '}';
    }
}
