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
    @ManyToOne
    @JoinColumn(name = "first_choice_id")
    private Subject firstChoice;
    @ManyToOne
    @JoinColumn(name = "second_choice_id")
    private Subject secondChoice;
    @ManyToOne
    @JoinColumn(name = "third_choice_id")
    private Subject thirdChoice;
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


    public Student(String firstName, String surname, Long keyId, String gsm, String email, String studentNumber, Subject firstChoice, Subject secondChoice, Subject thirdChoice, String discipline, Subject assignedSubject, Subject boostedSubject) {
        super(firstName, surname, keyId, gsm, email);
        this.studentNumber = studentNumber;
        this.firstChoice = firstChoice;
        this.secondChoice = secondChoice;
        this.thirdChoice = thirdChoice;
        this.discipline = discipline;
        this.assignedSubject = assignedSubject;
        this.boostedSubject=boostedSubject;
    }
    public Student(String firstName, String surname, String gsm, String email, String studentNumber){
        super(firstName, surname, gsm, email);
        this.studentNumber = studentNumber;        
    }
    public Student(String firstName, String surname, String gsm, String email, String studentNumber, String password){
        super(firstName, surname, gsm, email,password);
        this.studentNumber = studentNumber;
    }

    public Student(String firstName, String surname, String gsm, String email, String studentNumber, Subject firstChoice, Subject secondChoice, Subject thirdChoice, String discipline, Subject assignedSubject, Subject boostedSubject) {
        super(firstName, surname, gsm, email);
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
