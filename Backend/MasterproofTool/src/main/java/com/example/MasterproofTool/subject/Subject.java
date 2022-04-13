package com.example.MasterproofTool.subject;
import com.example.MasterproofTool.user.*;
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

    public Subject() {}

    public Subject(Long id, String title, String description, String remark, Company company, Coördinator coordinator, Promotor promotor, Set<Discipline> disciplines, Student boostedStudent, int aStudents) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.remark = remark;
        this.company = company;
        this.coordinator = coordinator;
        this.promotor = promotor;
        this.disciplines = disciplines;
        this.aStudents = aStudents;
        this.boostedStudent = boostedStudent;
        this.approved=true;
    }

    public Subject(String title, String description, String remark, Company company, Coördinator coordinator, Promotor promotor, Set<Discipline> disciplines, Student boostedStudent, int aStudents) {
        this.title = title;
        this.description = description;
        this.remark = remark;
        this.company = company;
        this.coordinator = coordinator;
        this.promotor = promotor;
        this.disciplines = disciplines;
        this.aStudents = aStudents;
        this.boostedStudent = boostedStudent;
        this.approved=true;
    }

    public Subject(String title, String description, String remark, Set<Discipline> disciplines, int aStudents) {
        this.title = title;
        this.description = description;
        this.remark = remark;
        this.disciplines = disciplines;
        this.aStudents = aStudents;
        this.approved=true;
    }

    public Subject(String title, String description, String remark, int aStudents) {
        this.title = title;
        this.description = description;
        this.remark = remark;
        this.aStudents = aStudents;
        this.approved=true;
    }

    public void setApprovedTrue() {this.approved=true;}
    public void setApprovedFalse() {this.approved=false;}

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
