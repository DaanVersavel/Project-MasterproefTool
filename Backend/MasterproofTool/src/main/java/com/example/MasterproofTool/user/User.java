package com.example.MasterproofTool.user;

import javax.persistence.*;


@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type",
        discriminatorType = DiscriminatorType.INTEGER)
public class User {

    private String firstName;
    private String surname;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long keyId;
    private int GSM;
    private String email;
    private String rol;

    public User(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    public User(String firstName, String surname, Long keyId, int GSM, String email, String rol) {
        this.firstName = firstName;
        this.surname = surname;
        this.keyId = keyId;
        this.GSM = GSM;
        this.email = email;
        this.rol = rol;
    }

    public User(String firstName, String surname, int GSM, String email, String rol) {
        this.firstName = firstName;
        this.surname = surname;
        this.GSM = GSM;
        this.email = email;
        this.rol = rol;
    }
    public User(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getKeyId() {
        return keyId;
    }

    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }

    public int getGSM() {
        return GSM;
    }

    public void setGSM(int GSM) {
        this.GSM = GSM;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", keyId=" + keyId +
                ", GSM=" + GSM +
                ", email='" + email + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
