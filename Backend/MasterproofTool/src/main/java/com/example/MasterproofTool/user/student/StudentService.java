package com.example.MasterproofTool.user.student;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.MasterproofTool.MasterproefToolApplication;
import com.example.MasterproofTool.subject.Subject;
import com.example.MasterproofTool.subject.SubjectRepository;
import com.example.MasterproofTool.user.campus.Campus;
import com.example.MasterproofTool.user.campus.CampusRepository;
import com.example.MasterproofTool.user.disciplines.Discipline;
import com.example.MasterproofTool.user.disciplines.DisciplineRepository;
import com.example.MasterproofTool.user.role.Role;
import com.example.MasterproofTool.user.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CampusRepository campusRepository;
    private final DisciplineRepository disciplineRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, CampusRepository campusRepository, DisciplineRepository disciplineRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.campusRepository = campusRepository;
        this.disciplineRepository = disciplineRepository;
    }

    public Student saveStudent(Student student) {
        encodePassword(student);
        return studentRepository.save(student);
    }

    public void encodePassword(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
    }

    public Set<Subject> getStudentStarred(long keyId) {
        Student s = studentRepository.findByKeyId(keyId);
        return s.getStarredSubjects();
    }

    public void setFirstChoice(long subjectid, String access_token) {
        //looking for student and set first choice
        Student student = getStudent(access_token);
        Subject subject = subjectRepository.findSubjectById(subjectid);
        student.setFirstChoice(subject);
        studentRepository.save(student);
    }

    public Subject getFirstChoice(String access_token) {
        Student s = getStudent(access_token);
        return s.getFirstChoice();
    }

    public Optional<Student> saveNewStudent(Student student) {
        //check if student already exist
        Optional<Student> studentByOptional =
                studentRepository.findStudentByEmail(student.getEmail());
        if (studentByOptional.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        //if student doesn't exist
        else {
            Campus campus = campusRepository.findByName(student.getCampus().getName());
            student.setCampus(campus);
            Discipline discipline = disciplineRepository.findByNaam(student.getDiscipline().getNaam());
            student.setDiscipline(discipline);
            encodePassword(student);
            studentRepository.save(student);
            addRoleToStudent(student.getEmail(), MasterproefToolApplication.ROLE_STUDENT);
        }
        return studentRepository.findStudentByEmail(student.getEmail());
    }

    public void addRoleToStudent(String email, String rolename) {
        Student user = studentRepository.findExistingStudentByEmail(email);
        Role role = roleRepository.findByRoleName(rolename);
        user.setRole(role);
        studentRepository.save(user);
    }

    public Set<Subject> getStarred(String access_token) {
        Student student = getStudent(access_token);
        return student.getStarredSubjects();
    }

    public void addToStarred(long id, String access_token) {
        Student student = getStudent(access_token);
        Subject subject = subjectRepository.findSubjectById(id);
        student.getStarredSubjects().add(subject);
        studentRepository.save(student);
    }

    public Student getStudent(String access_token) {
        Algorithm algorithm = Algorithm.HMAC256("secretthatnobodycanacces".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(access_token);
        String email = decodedJWT.getSubject();
        return studentRepository.findExistingStudentByEmail(email);
    }

    public void removeFromStarred(long subjectid, String access_token) {
        Student student = getStudent(access_token);
        Subject subject = subjectRepository.findSubjectById(subjectid);
        student.removeSubjectFromStarred(subject);
        studentRepository.save(student);
    }

    public Subject getSecondChoice(String access_token) {
        Student s = getStudent(access_token);
        return s.getSecondChoice();
    }

    public Subject getThirdChoice(String access_token) {
        Student s = getStudent(access_token);
        if(s.getThirdChoice()==null){
            throw new IllegalStateException("No third choise added");
        } else {
            return s.getThirdChoice();
        }
    }

    public void setThirdChoice(long subjectid, String access_token) {
        //looking for student and set first choice
        Student student = getStudent(access_token);
        Subject subject = subjectRepository.findSubjectById(subjectid);
        //if(student.getSecondChoice()!=subject&&student.getFirstChoice()!=subject){
           // if subject in not yet assigned
            student.setThirdChoice(subject);
            studentRepository.save(student);
        //}
        //else{
          //  throw new IllegalArgumentException("Subject already assigned");
        //}
    }

    public void setSecondChoice(long subjectid, String access_token) {
        //looking for student and set first choice
        Student student = getStudent(access_token);
        Subject subject = subjectRepository.findSubjectById(subjectid);
        if(student.getThirdChoice()!=subject&&student.getFirstChoice()!=subject){
            // if subject in not yet assigned
            student.setSecondChoice(subject);
            studentRepository.save(student);
        }
        else{
            throw new IllegalArgumentException("Subject already assigned");
        }

    }
}
