package com.example.MasterproofTool.user;


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
    private String GSM;
    private String email;
    private String password;
    @ManyToMany(fetch =FetchType.EAGER)
    private Collection<Role> roles= new ArrayList<>();


    public Appuser(String firstName, String surname, Long keyId, String GSM, String email) {
        this.firstName = firstName;
        this.surname = surname;
        this.keyId = keyId;
        this.GSM = GSM;
        this.email = email;
    }


    public Appuser(String firstName, String surname, Long keyId, String GSM, String email,String password) {
        this.firstName = firstName;
        this.surname = surname;
        this.keyId = keyId;
        this.GSM = GSM;
        this.email = email;
        this.password= password;
    }
    public Appuser(String firstName, String surname, String GSM, String email,String password) {
        this.firstName = firstName;
        this.surname = surname;
        this.GSM = GSM;
        this.email = email;
        this.password= password;
    }
    public Appuser(String firstName, String surname, String GSM, String email) {
        this.firstName = firstName;
        this.surname = surname;
        this.GSM = GSM;
        this.email = email;

    }
    public Appuser(){}


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
