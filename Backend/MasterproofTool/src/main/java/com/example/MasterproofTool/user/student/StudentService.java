package com.example.MasterproofTool.user.student;


import com.example.MasterproofTool.MasterproefToolApplication;
import com.example.MasterproofTool.subject.Subject;
import com.example.MasterproofTool.subject.SubjectRepository;
import com.example.MasterproofTool.user.Role;
import com.example.MasterproofTool.user.RoleRepository;
import com.example.MasterproofTool.user.Student;
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

    @Autowired
    public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public Student saveStudent(Student student){
        encodePassword(student);
        return studentRepository.save(student);
    }

    public void encodePassword(Student student){
        student.setPassword(passwordEncoder.encode(student.getPassword()));
    }

    public Set<Subject> getStudentStarred(long keyId) {
        Student s=studentRepository.findByKeyId(keyId);
        return s.getStarredSubjects();
    }

    public void setFirstChoice(long keyId, long subjectid) {
        //looking for student and set first choice
        Student student=studentRepository.findByKeyId(keyId);
        Subject subject=subjectRepository.findSubjectById(subjectid);
        student.setFirstChoice(subject);
        studentRepository.save(student);
    }

    public Subject getFirstChoice(long keyId) {
        Student s=studentRepository.findByKeyId(keyId);
        return s.getFirstChoice();
    }

    public Optional<Student>  saveNewStudent(Student student) {
        //check if student already exist
        Optional<Student> studentByOptional =
                studentRepository.findStudentByEmail(student.getEmail());
        if(studentByOptional.isPresent()){
            throw  new IllegalStateException("Email already taken");
        }
        //if student doesn't exist
        else {
            encodePassword(student);
            studentRepository.save(student);
            addRoleToStudent(student.getEmail(), MasterproefToolApplication.ROLE_STUDENT);

        }
        return studentRepository.findStudentByEmail(student.getEmail());
    }

    public void addRoleToStudent(String email, String rolename){
        Student user = studentRepository.findExistingStudentByEmail(email);
        Role role = roleRepository.findByRoleName(rolename);
        user.getRoles().add(role);
    }
}
