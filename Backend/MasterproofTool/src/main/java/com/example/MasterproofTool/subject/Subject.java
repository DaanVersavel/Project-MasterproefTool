package com.example.MasterproofTool.subject;

import com.example.MasterproofTool.user.Company;

import javax.persistence.*;


import java.util.Arrays;
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private String discipline;
    private String remark;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    private int aStudents;
    @Transient
    private String[] campus, education;

    public Subject(String title, String description, String discipline, String remark, Company company, int aStudents, String[] campus, String[] education) {
        this.title = title;
        this.description = description;
        this.discipline = discipline;
        this.remark = remark;
        this.company = company;
        this.aStudents = aStudents;
        this.campus = campus;
        this.education = education;
    }
    public Subject(){}

    public Subject(String description, String discipline, String remark, Company company, int aStudents, String[] campus, String[] education) {
        this.description = description;
        this.discipline = discipline;
        this.remark = remark;
        this.company = company;
        this.aStudents = aStudents;
        this.campus = campus;
        this.education = education;
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

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
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

    public int getaStudents() {
        return aStudents;
    }

    public void setaStudents(int aStudents) {
        this.aStudents = aStudents;
    }

    public String[] getCampus() {
        return campus;
    }

    public void setCampus(String[] campus) {
        this.campus = campus;
    }

    public String[] getEducation() {
        return education;
    }

    public void setEducation(String[] education) {
        this.education = education;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", discipline='" + discipline + '\'' +
                ", remark='" + remark + '\'' +
                ", company=" + company +
                ", aStudents=" + aStudents +
                ", campus=" + Arrays.toString(campus) +
                ", education=" + Arrays.toString(education) +
                '}';
    }
}
