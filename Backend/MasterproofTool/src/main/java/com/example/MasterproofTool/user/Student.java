package com.example.MasterproofTool.user;


import com.example.MasterproofTool.subject.Subject;

import javax.persistence.*;


@Entity
@DiscriminatorValue("1")
public class Student extends Appuser {
    //@Id
    private char studentNumber;
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


    public Student(String firstName, String surname, Long keyId, String GSM, String email, char studentNumber, Long firstChoice, Long secondChoice, Long thirdChoice, String discipline, Subject assignedSubject, Subject boostedSubject) {
        super(firstName, surname, keyId, GSM, email);
        this.studentNumber = studentNumber;
        this.firstChoice = firstChoice;
        this.secondChoice = secondChoice;
        this.thirdChoice = thirdChoice;
        this.discipline = discipline;
        this.assignedSubject = assignedSubject;
        this.boostedSubject=boostedSubject;
    }

    public Student(String firstName, String surname, String GSM, String email, char studentNumber, Long firstChoice, Long secondChoice, Long thirdChoice, String discipline, Subject assignedSubject, Subject boostedSubject) {
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

    public char getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(char studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Long getFirstChoice() {
        return firstChoice;
    }

    public void setFirstChoice(Long firstChoice) {
        this.firstChoice = firstChoice;
    }

    public Long getSecondChoice() {
        return secondChoice;
    }

    public void setSecondChoice(Long secondChoice) {
        this.secondChoice = secondChoice;
    }

    public Long getThirdChoice() {
        return thirdChoice;
    }

    public void setThirdChoice(Long thirdChoice) {
        this.thirdChoice = thirdChoice;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public Subject getAssignedSubject() {
        return assignedSubject;
    }

    public void setAssignedSubject(Subject assignedSubject) {
        this.assignedSubject = assignedSubject;
    }

    public Subject getBoostedSubject() {
        return boostedSubject;
    }

    public void setBoostedSubject(Subject boostedSubject) {
        this.boostedSubject = boostedSubject;
    }

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
