package com.example.MasterproofTool.user;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type",
        discriminatorType = DiscriminatorType.INTEGER)
@Entity
public class Appuser {

    private String firstName;
    private String surname;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long keyId;
    private int GSM;
    private String email;
    private String password;
    @ManyToMany(fetch =FetchType.EAGER)
    private Collection<Role> roles= new ArrayList<>();


    public Appuser(String firstName, String surname, Long keyId, int GSM, String email) {
        this.firstName = firstName;
        this.surname = surname;
        this.keyId = keyId;
        this.GSM = GSM;
        this.email = email;

    }

    public Appuser(String firstName, String surname, int GSM, String email) {
        this.firstName = firstName;
        this.surname = surname;
        this.GSM = GSM;
        this.email = email;

    }
    public Appuser(){}

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

    public Collection<Role> getRol() {
        return roles;
    }
    public Collection<Role> getRoles() {
        return roles;
    }

//    public void setRol(String rol) {
//        this.rol = rol;
//    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", keyId=" + keyId +
                ", GSM=" + GSM +
                ", email='" + email + '\'' +
                '}';
    }
}
