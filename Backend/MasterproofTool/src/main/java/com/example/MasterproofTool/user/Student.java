package com.example.MasterproofTool.user;


import javax.persistence.*;

@Entity
@DiscriminatorValue("1")
public class Student extends User {
    @Id
    private char studentNumber;
    private Long firstChoice;
    private Long secondChoice;
    private Long thirdChoice;
    private String discipline;

    public Student(String firstName, String surname, Long keyId, int GSM, String email, String rol, char studentNumber, Long firstChoice, Long secondChoice, Long thirdChoice, String discipline) {
        super(firstName, surname, keyId, GSM, email, rol);
        this.studentNumber = studentNumber;
        this.firstChoice = firstChoice;
        this.secondChoice = secondChoice;
        this.thirdChoice = thirdChoice;
        this.discipline = discipline;
    }

    public Student(String firstName, String surname, int GSM, String email, String rol, Long firstChoice, Long secondChoice, Long thirdChoice, String discipline) {
        super(firstName, surname, GSM, email, rol);
        this.firstChoice = firstChoice;
        this.secondChoice = secondChoice;
        this.thirdChoice = thirdChoice;
        this.discipline = discipline;
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

    @Override
    public String toString() {
        return "Student{" +
                "studentNumber=" + studentNumber +
                ", firstChoice=" + firstChoice +
                ", secondChoice=" + secondChoice +
                ", thirdChoice=" + thirdChoice +
                ", discipline= "+ discipline +
                '}';
    }
}
