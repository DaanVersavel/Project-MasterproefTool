package com.example.MasterproofTool.user;
import com.example.MasterproofTool.subject.Subject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
public class Student extends User {
    private char studentNumber;
    private Subject firstChoice;
    private Subject secondChoice;
    private Subject thirdChoice;

    public Student(char studentNumber, Subject firstChoice, Subject secondChoice, Subject thirdChoice) {
        this.studentNumber = studentNumber;
        this.firstChoice = firstChoice;
        this.secondChoice = secondChoice;
        this.thirdChoice = thirdChoice;
    }

    public Student(Subject firstChoice, Subject secondChoice, Subject thirdChoice) {
        this.firstChoice = firstChoice;
        this.secondChoice = secondChoice;
        this.thirdChoice = thirdChoice;
    }
    public Student(){}

    public char getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(char studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Subject getFirstChoice() {
        return firstChoice;
    }

    public void setFirstChoice(Subject firstChoice) {
        this.firstChoice = firstChoice;
    }

    public Subject getSecondChoice() {
        return secondChoice;
    }

    public void setSecondChoice(Subject secondChoice) {
        this.secondChoice = secondChoice;
    }

    public Subject getThirdChoice() {
        return thirdChoice;
    }

    public void setThirdChoice(Subject thirdChoice) {
        this.thirdChoice = thirdChoice;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentNumber=" + studentNumber +
                ", firstChoice=" + firstChoice +
                ", secondChoice=" + secondChoice +
                ", thirdChoice=" + thirdChoice +
                '}';
    }
}
