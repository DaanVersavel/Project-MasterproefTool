package com.example.MasterproofTool.subject;
import com.example.MasterproofTool.user.*;

import javax.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;

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
    }

    public Subject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   public Subject(String title, String description, /*Set<Discipline> disciplines,*/ String remark,  int aStudents) {
        this.title = title;
        this.description = description;
        //this.disciplines = disciplines;
        this.remark = remark;
        this.aStudents = aStudents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Coördinator getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Coördinator coordinator) {
        this.coordinator = coordinator;
    }

    public Promotor getPromotor() {
        return promotor;
    }

    public void setPromotor(Promotor promotor) {
        this.promotor = promotor;
    }

    public Student getBoostedStudent() {
        return boostedStudent;
    }

    public void setBoostedStudent(Student boostedStudent) {
        this.boostedStudent = boostedStudent;
    }

    public Set<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(Set<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public int getaStudents() {
        return aStudents;
    }

    public void setaStudents(int aStudents) {
        this.aStudents = aStudents;
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
