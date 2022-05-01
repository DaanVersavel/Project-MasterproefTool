package com.example.MasterproofTool.user.appUser;


import com.example.MasterproofTool.user.role.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type",
        discriminatorType = DiscriminatorType.INTEGER)
@Entity
@Getter
@Setter
public class Appuser {

    private String firstName;
    private String surname;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long keyId;
    private String gsm;
    private String email;
    private String password;
    @ManyToMany(fetch =FetchType.EAGER)
    private Collection<Role> roles= new ArrayList<>();

    public Appuser(String firstName, String surname, Long keyId, String gsm, String email) {
        this.firstName = firstName;
        this.surname = surname;
        this.keyId = keyId;
        this.gsm = gsm;
        this.email = email;
    }


    public Appuser(String firstName, String surname, Long keyId, String gsm, String email, String password) {
        this.firstName = firstName;
        this.surname = surname;
        this.keyId = keyId;
        this.gsm = gsm;
        this.email = email;
        this.password= password;
    }
    public Appuser(String firstName, String surname, String gsm, String email, String password) {
        this.firstName = firstName;
        this.surname = surname;
        this.gsm = gsm;
        this.email = email;
        this.password= password;
    }
    public Appuser(String firstName, String surname, String gsm, String email) {
        this.firstName = firstName;
        this.surname = surname;
        this.gsm = gsm;
        this.email = email;

    }
    public Appuser(){}

    public Appuser(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }


    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", keyId=" + keyId +
                ", GSM=" + gsm +
                ", email='" + email + '\'' +
                '}';
    }
}
