package com.example.MasterproofTool.user;


import com.example.MasterproofTool.subject.Subject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@DiscriminatorValue("1")
@Getter
@Setter
public class Student extends Appuser {
    //@Id
    private String studentNumber;
    private Long firstChoice;
    private Long secondChoice;
    private Long thirdChoice;
    private String discipline;
    @ManyToOne
    @JoinColumn(name="campus_id")
    private Campus campus;
    @ManyToOne
    @JoinColumn(name="assignedSubject_id")
    private Subject assignedSubject;
    @OneToOne(mappedBy = "boostedStudent")
    private Subject boostedSubject;

    @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    Set<Subject> starredSubjects = new HashSet<>();


    public Student(String firstName, String surname, Long keyId, String GSM, String email, String studentNumber, Long firstChoice, Long secondChoice, Long thirdChoice, String discipline, Subject assignedSubject, Subject boostedSubject) {
        super(firstName, surname, keyId, GSM, email);
        this.studentNumber = studentNumber;
        this.firstChoice = firstChoice;
        this.secondChoice = secondChoice;
        this.thirdChoice = thirdChoice;
        this.discipline = discipline;
        this.assignedSubject = assignedSubject;
        this.boostedSubject=boostedSubject;
    }
    public Student(String firstName, String surname, String GSM, String email, String studentNumber){
        super(firstName, surname, GSM, email);
        this.studentNumber = studentNumber;        
    }
    public Student(String firstName, String surname, String GSM, String email, String studentNumber, String password){
        super(firstName, surname, GSM, email,password);
        this.studentNumber = studentNumber;
    }

    public Student(String firstName, String surname, String GSM, String email, String studentNumber, Long firstChoice, Long secondChoice, Long thirdChoice, String discipline, Subject assignedSubject, Subject boostedSubject) {
        super(firstName, surname, GSM, email);
        this.studentNumber = studentNumber;
        this.firstChoice = firstChoice;
        this.secondChoice = secondChoice;
        this.thirdChoice = thirdChoice;
        this.discipline = discipline;
        this.assignedSubject = assignedSubject;
        this.boostedSubject=boostedSubject;
    }

    public Student(){}



    @Override
    public String toString() {
        return "Student{" +
                "studentNumber=" + studentNumber +
                ", firstChoice=" + firstChoice +
                ", secondChoice=" + secondChoice +
                ", thirdChoice=" + thirdChoice +
                ", discipline='" + discipline + '\'' +
                ", assignedSubject=" + assignedSubject +
                ", boostedSubject=" + boostedSubject +
                '}';
    }
}
