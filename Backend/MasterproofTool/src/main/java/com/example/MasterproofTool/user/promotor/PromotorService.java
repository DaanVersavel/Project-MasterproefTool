package com.example.MasterproofTool.user.promotor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.MasterproofTool.MasterproefToolApplication;
import com.example.MasterproofTool.subject.Subject;
import com.example.MasterproofTool.subject.SubjectRepository;
import com.example.MasterproofTool.user.role.Role;
import com.example.MasterproofTool.user.role.RoleRepository;
import com.example.MasterproofTool.user.student.Student;
import com.example.MasterproofTool.user.student.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PromotorService {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final PromotorRepository promotorRepository;
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;

    public PromotorService(PasswordEncoder passwordEncoder, RoleRepository roleRepository, PromotorRepository promotorRepository, SubjectRepository subjectRepository, StudentRepository studentRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.promotorRepository = promotorRepository;
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
    }

    public Optional<Promotor> saveNewPromotor(Promotor promotor) {
        //check if Promotor already exist
        Optional<Promotor> promotorByOptional =
                promotorRepository.findPromotorByEmail(promotor.getEmail());
        if(promotorByOptional.isPresent()){
            throw  new IllegalStateException("Email already taken");
        }
        //if student doesn't exist
        else {
            encodePassword(promotor);
            promotorRepository.save(promotor);
            addRoleToCompany(promotor.getEmail(), MasterproefToolApplication.ROLE_PROMOTOR);

        }
        return promotorRepository.findPromotorByEmail(promotor.getEmail());
    }
    public void encodePassword(Promotor promotor){
        promotor.setPassword(passwordEncoder.encode(promotor.getPassword()));
    }

    public void addRoleToCompany(String email, String rolename){
        Promotor user = promotorRepository.findExistingPromotorByEmail(email);
        Role role = roleRepository.findByRoleName(rolename);
        user.setRole(role);
        promotorRepository.save(user);
    }

    public List<Promotor> getPromotors() {
        return promotorRepository.findAll();
    }

    public List<Subject> getMySubjects(String accesToken) {
        Promotor p=getUser(accesToken);
        return subjectRepository.findSubjectByPromotor(p);
    }

    public Promotor getUser(String access_token) {
        Algorithm algorithm = Algorithm.HMAC256("secretthatnobodycanacces".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(access_token);
        String email = decodedJWT.getSubject();
        return promotorRepository.findExistingPromotorByEmail(email);
    }

    public void boostStudent(long subjectid, long studentid) {
        Subject subject = subjectRepository.findSubjectById(subjectid);
        Student student = studentRepository.findByKeyId(studentid);
        student.setBoostedSubject(subject);
        subject.setBoostedStudent(student);
        studentRepository.save(student);
        subjectRepository.save(subject);

    }

    public List<Student> getStudentListPerSubject(String accesToken, long subjectId) {
        Subject subject = subjectRepository.findSubjectById(subjectId);
        return studentRepository.findStudentBySubjectFirstchoise(subject);
    }
}
