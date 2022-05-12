package com.example.MasterproofTool.subject;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.MasterproofTool.user.appUser.Appuser;
import com.example.MasterproofTool.user.appUser.UserRepository;
import com.example.MasterproofTool.user.coördinator.Coördinator;
import com.example.MasterproofTool.user.coördinator.CoördinatorRepository;
import com.example.MasterproofTool.user.promotor.Promotor;
import com.example.MasterproofTool.user.promotor.PromotorRepository;
import com.example.MasterproofTool.user.student.Student;
import com.example.MasterproofTool.user.student.StudentRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final CoördinatorRepository coordinatorRepository;
    private final PromotorRepository promotorRepository;;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, UserRepository userRepository, StudentRepository studentRepository, CoördinatorRepository coordinatorRepository, PromotorRepository promotorRepository) {
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.coordinatorRepository = coordinatorRepository;
        this.promotorRepository = promotorRepository;
    }

    public List<Subject> getSubjectsApproved(String access_token) {
        String email = getEmail(access_token);
        if(studentRepository.findStudentByEmail(email).isPresent()){
            Student student = studentRepository.findExistingStudentByEmail(email);
            return subjectRepository.findSubjectByDisciplineApprovedTrue(student.getDiscipline());
        }
        else if(promotorRepository.findPromotorByEmail(email).isPresent()){
            Promotor promotor = promotorRepository.findExistingPromotorByEmail(email);
            return subjectRepository.findSubjectByDisciplineApprovedTrue(promotor.getDiscipline());
        }
        else if(coordinatorRepository.findOptinalCoordinatorByEmail(email).isPresent()){
            Coördinator coords = coordinatorRepository.findCoordinatorByEmail(email);
            return subjectRepository.findSubjectByDisciplineApprovedTrue(coords.getDiscipline());
        }
        else return Collections.emptyList();
    }

    //saving of a subject
    public void addNewSubject(Subject subject) {
        Optional<Subject> subjectByOptional = subjectRepository.findSubjectByTitle(subject.getTitle());
        if(subjectByOptional.isPresent()){
            throw  new IllegalStateException("Subject title already taken");
        }
        subjectRepository.save(subject);
    }

    public Subject updateSubjectReviewApprovedTrue(long id){
        Subject subject = subjectRepository.findSubjectById(id);
        subject.setApprovedTrue();
        return subjectRepository.save(subject);
    }

    public List<Subject> getSubjectForUser(long coordinator_id) {
        return subjectRepository.findSubjectByCoordinator_Id( coordinator_id);
    }

    public Subject updateSubjectReviewDeniedTrue(long id) {
        Subject subject = subjectRepository.findSubjectById(id);
        subject.setDeniedTrue();
        return subjectRepository.save(subject);
    }

    public Subject getSpecificSubject(long id) {
        return subjectRepository.findSubjectById(id);
    }

    public List<Subject> getSubjectsDenied() {
        return subjectRepository.findSubjectByApprovedFalseAndDeniedTrue();
    }


    public Appuser getAppuserAccessToken(String access_token) {
        Algorithm algorithm = Algorithm.HMAC256("secretthatnobodycanacces".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(access_token);
        String email = decodedJWT.getSubject();
        return userRepository.findByEmail(email);
    }

    public String getEmail(String access_token) {
        Algorithm algorithm = Algorithm.HMAC256("secretthatnobodycanacces".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(access_token);
        return  decodedJWT.getSubject();

    }



}
