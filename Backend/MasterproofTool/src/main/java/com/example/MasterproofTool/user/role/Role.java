package com.example.MasterproofTool.user.role;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String roleName;

    public Role(Long id, String roleName) {
        this.id= id;
        this.roleName = roleName;
    }
    public Role() {}


}
