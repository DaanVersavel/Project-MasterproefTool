package com.example.MasterproofTool.subject;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.MasterproofTool.user.appUser.Appuser;
import com.example.MasterproofTool.user.appUser.UserRepository;
import com.example.MasterproofTool.user.campus.Campus;
import com.example.MasterproofTool.user.campus.CampusRepository;
import com.example.MasterproofTool.user.company.Company;
import com.example.MasterproofTool.user.company.CompanyRepository;
import com.example.MasterproofTool.user.coördinator.Coördinator;
import com.example.MasterproofTool.user.coördinator.CoördinatorRepository;
import com.example.MasterproofTool.user.disciplines.Discipline;
import com.example.MasterproofTool.user.disciplines.DisciplineRepository;
import com.example.MasterproofTool.user.promotor.Promotor;
import com.example.MasterproofTool.user.promotor.PromotorRepository;
import com.example.MasterproofTool.user.student.Student;
import com.example.MasterproofTool.user.student.StudentRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final CoördinatorRepository coordinatorRepository;
    private final PromotorRepository promotorRepository;;
    private final CampusRepository campusRepository;
    private final CompanyRepository companyRepository;
    private final DisciplineRepository disciplineRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, UserRepository userRepository, StudentRepository studentRepository, CoördinatorRepository coordinatorRepository, PromotorRepository promotorRepository, CampusRepository campusRepository, CompanyRepository companyRepository, DisciplineRepository disciplineRepository) {
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.coordinatorRepository = coordinatorRepository;
        this.promotorRepository = promotorRepository;
        this.campusRepository = campusRepository;
        this.companyRepository = companyRepository;
        this.disciplineRepository = disciplineRepository;
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
        }else{
            if(subject.getCompany()!=null){
                Company company= companyRepository.findExistingCompanyByEmail(subject.getCompany().getEmail());
                subject.setCompany(company);
            }
            if(subject.getCoordinator()!=null){
                Coördinator coord= coordinatorRepository.findExistingCoordinatorByEmail(subject.getCoordinator().getEmail());
                subject.setCoordinator(coord);
            }
            if(subject.getPromotor()!=null){
                Promotor promotor= promotorRepository.findExistingPromotorByEmail(subject.getPromotor().getEmail());
                subject.setPromotor(promotor);
            }
            //get all Disciplines
            Set<Discipline> disciplines= new HashSet(subject.getDisciplines()) ;
            //remove the list in subjects
            subject.setDisciplines(new HashSet<>());
            //add the discplines to subject
            for(Discipline d : disciplines){
                Discipline discipline=disciplineRepository.findByNaam(d.getNaam());
                subject.getDisciplines().add(discipline);
            }

            //get all Disciplines
            Set<Campus> campussen= new HashSet(subject.getCampussen());
            //remove the list in subjects
            subject.setCampussen(new HashSet<>());
            //add the discplines to subject
            for(Campus c : campussen){
                Campus campus=campusRepository.findByName(c.getName());
                subject.getCampussen().add(campus);
            }
            subjectRepository.save(subject);
        }

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
