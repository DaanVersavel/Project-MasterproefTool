package com.example.MasterproofTool.subject;
import com.example.MasterproofTool.user.company.Company;
import com.example.MasterproofTool.user.coördinator.Coördinator;
import com.example.MasterproofTool.user.disciplines.Discipline;
import com.example.MasterproofTool.user.promotor.Promotor;
import com.example.MasterproofTool.user.student.Student;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private boolean approved;

    private boolean denied;

    private String remark;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @ManyToOne
    @JoinColumn(name= "coördinator_id")
    private Coördinator coordinator;
    @ManyToOne
    @JoinColumn(name="promotor_id")
    private Promotor promotor;
    @OneToOne
    @JoinColumn(name="boostedStudent_id")
    private Student boostedStudent;
    @ManyToMany(mappedBy = "subjects")
    private Set<Discipline>disciplines=new HashSet<>();

    private int aStudents;

    public Subject(Long id, String title, String description, String remark, Company company, Coördinator coordinator, Promotor promotor, Student boostedStudent, Set<Discipline> disciplines, int aStudents) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.remark = remark;
        this.company = company;
        this.coordinator = coordinator;
        this.promotor = promotor;
        this.boostedStudent = boostedStudent;
        this.disciplines = disciplines;
        this.aStudents = aStudents;
        this.approved=false;
        this.denied=false;
    }

    public Subject(String title, String description, String remark, Company company, Coördinator coordinator, Promotor promotor, Student boostedStudent, Set<Discipline> disciplines, int aStudents) {
        this.title = title;
        this.description = description;
        this.remark = remark;
        this.company = company;
        this.coordinator = coordinator;
        this.promotor = promotor;
        this.boostedStudent = boostedStudent;
        this.disciplines = disciplines;
        this.aStudents = aStudents;
        this.approved=false;
        this.denied=false;
    }

    public Subject() {}


   public Subject(String title, String description,String remark,  int aStudents) {
        this.title = title;
        this.description = description;
        this.remark = remark;
        this.aStudents = aStudents;
        this.approved=false;
        this.denied=false;
    }

    public Subject(String title, String description, boolean approved, String remark,  int aStudents) {
        this.title = title;
        this.description = description;
        this.approved = approved;
        this.remark = remark;
        this.aStudents = aStudents;
    }
    public Subject(String title, String description,Coördinator coordinator, String remark,  int aStudents) {
        this.title = title;
        this.description = description;
        this.coordinator = coordinator;
        this.remark = remark;
        this.aStudents = aStudents;
    }
    public Subject(String title, String description, String remark,  int aStudents, Company company) {
        this.title = title;
        this.description = description;
        this.company = company;
        this.remark = remark;
        this.aStudents = aStudents;
    }

    public void setApprovedTrue() {
        this.approved=true;
    }
    public void setDeniedTrue() {
        this.denied=true;
    }


    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", remark='" + remark + '\'' +
                ", company=" + company +
                ", coordinator=" + coordinator +
                ", promotor=" + promotor +
                ", boostedStudent=" + boostedStudent +
                ", disciplines=" + disciplines +
                ", aStudents=" + aStudents +
                '}';
    }
}
