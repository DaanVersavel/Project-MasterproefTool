package com.example.MasterproofTool.user.coördinator;

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
import com.example.MasterproofTool.user.student.Student;
import com.example.MasterproofTool.user.student.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoördinatorService {

    private final CoördinatorRepository coordinatorRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final SubjectRepository subjectRepository;
    private final DisciplineRepository disciplineRepository;
    private final StudentRepository studentRepository;
    private final CampusRepository campusRepository;

    public CoördinatorService(CoördinatorRepository coordinatorRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, SubjectRepository subjectRepository, DisciplineRepository disciplineRepository, StudentRepository studentRepository, CampusRepository campusRepository) {
        this.coordinatorRepository = coordinatorRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.subjectRepository = subjectRepository;
        this.disciplineRepository = disciplineRepository;
        this.studentRepository = studentRepository;
        this.campusRepository = campusRepository;
    }

    public void addRoleToCoordinator(String email, String rolename){
        Coördinator user = coordinatorRepository.findExistingCoordinatorByEmail(email);
        Role role = roleRepository.findByRoleName(rolename);
        user.setRole(role);
        coordinatorRepository.save(user);
    }
    public void encodePassword(Coördinator coördinator){
        coördinator.setPassword(passwordEncoder.encode(coördinator.getPassword()));
    }


    public Coördinator  saveNewCoordinator(Coördinator coördinator) {
        //check if coordinator already exist
        Optional<Coördinator> coordinatorByOptional =
                coordinatorRepository.findOptinalCoordinatorByEmail(coördinator.getEmail());
        if(coordinatorByOptional.isPresent()){
            throw  new IllegalStateException("Email already taken");
        }
        //if student doesn't exist
        else {
            Campus campus = campusRepository.findByName(coördinator.getCampus().getName());
            coördinator.setCampus(campus);
            Discipline discipline = disciplineRepository.findByNaam(coördinator.getDiscipline().getNaam());
            coördinator.setDiscipline(discipline);
            encodePassword(coördinator);
            coordinatorRepository.save(coördinator);
            addRoleToCoordinator(coördinator.getEmail(), MasterproefToolApplication.ROLE_COÖRDINATOR);
        }
        return coordinatorRepository.findCoordinatorByEmail(coördinator.getEmail());
    }
    public List<Subject> getsubjectscoordinator(String access_token){
        Coördinator coordinator = getCoordinatorAccessToken(access_token);
        Discipline desiredDiscipline= coordinator.getDiscipline();
        Campus desiredCampus= coordinator.getCampus();

        //this is a list of subject for a specific disipline
        List<Subject> listSubjectsOfDiscipline = disciplineRepository.findSubjectByDiscipline(desiredDiscipline);
        List<Subject> listofSubjectofCampusDiscipline = new ArrayList<>();
        //first sort on campus
        for(Subject subject: listSubjectsOfDiscipline){
            //if the subject contains the campus add it to the new list listofSubjectofCampus
            if(subject.getCampussen().contains(desiredCampus)){
                listofSubjectofCampusDiscipline.add(subject);
            }
        }
        return listofSubjectofCampusDiscipline;
    }

    public List<Subject> getSubjectsForReview(String access_token) {
        //this is a list of subject for a specific disipline
        List<Subject> listSubjectsDiscipline = getsubjectscoordinator(access_token);
        List<Subject> subjectsForRiew = new ArrayList<>();

        //not yet sure if the subject are approved false and denied false
        for(Subject subject1: listSubjectsDiscipline) {
            if (subject1.getApproved() == false && subject1.getDenied() == false) {
                subjectsForRiew.add(subject1);
            }
        }
        return subjectsForRiew;
    }


    public Coördinator getCoordinatorAccessToken(String access_token) {
        Algorithm algorithm = Algorithm.HMAC256("secretthatnobodycanacces".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(access_token);
        String email = decodedJWT.getSubject();
        return coordinatorRepository.findExistingCoordinatorByEmail(email);
    }



    public void autoAssignBoostedStudents(String access_token){
        //nows coordinator
        Coördinator coordinator = getCoordinatorAccessToken(access_token);
        //subject he can acces
        List<Subject> subjects = subjectRepository.findSubjectByDisciplineApprovedTrue(coordinator.getDiscipline());

        //check for evvery subject if he has a boosted student
        for(Subject subject : subjects){
            //if it has boosted student you can assign it
            if(subject.getBoostedStudent()!=null){
                //assign subject to student
                Student bsFromSubject = subject.getBoostedStudent();
                Student bs = studentRepository.findExistingStudentByEmail(bsFromSubject.getEmail());
                bs.setAssignedSubject(subject);
                studentRepository.save(bs);

                //assign student to subject
                Subject subjectToBeAssigned= subjectRepository.findSubjectById(subject.getId());
                subjectToBeAssigned.addAssignedStudents(bs);
                subjectRepository.save(subjectToBeAssigned);
            }
        }
    }

    public List<Subject> getAssignedSubjects(String access_token) {
        Coördinator coord=getCoordinatorAccessToken(access_token);
        List<Subject> assignedlists= new ArrayList<>();
        // list of all subject of a discipline
        List<Subject> allForDiscipline = subjectRepository.findSubjectByDisciplineApprovedTrue(coord.getDiscipline());
        //remove subbject that are not fully assigned
        for(Subject s: allForDiscipline ){
            //if assigned student equals astudents the subject is fully assigned
            //so ifnot we can remove it from the assigned list
            if(s.getAStudents()==s.getAssignedStudents().size()){
                assignedlists.add(s);
            }
        }
        //return updated list
        return assignedlists;
    }

    public List<Subject> getNotAssignedSubjects(String access_token) {
        Coördinator coord=getCoordinatorAccessToken(access_token);
        List<Subject> notAssignedSubject= new ArrayList<>();
        // list of all subject of a discipline
        List<Subject> allForDiscipline = subjectRepository.findSubjectByDisciplineApprovedTrue(coord.getDiscipline());
        //remove subbject that are not fully assigned
        for(Subject s: allForDiscipline ){
            //if assigned student equals astudents the subject is fully assigned
            //so if not we can add it to the not assigned list
            if(s.getAStudents()!=s.getAssignedStudents().size()){
                notAssignedSubject.add(s) ;
            }
        }
        //return updated list
        return notAssignedSubject;
    }



    public void autoAssignFirstChoise(String access_token){
        Coördinator coordinator= getCoordinatorAccessToken(access_token);
        List<Subject> listSubjectsDiscipline = getsubjectscoordinator(access_token);
        //we wil assign the first choise if ther is only 1 firstchoise
        List<Student> studentListDiscipline= studentRepository.findStudentByDiscipline(coordinator.getDiscipline());
        for(Subject subject: listSubjectsDiscipline){
            int count=0;
            Student firstchoiseStudent = null;
            for(Student student : studentListDiscipline){
                //if subject is the same as first choise increase counter and capture student
                 if(student.getFirstChoice().equals(subject)){
                     count++;
                     firstchoiseStudent= student;
                 }
            }
            //if count is the same as aStudent for subjects
            if(count==subject.getAStudents()&&subject.getAStudents()!=2){
                Student studentToAssign= studentRepository.findExistingStudentByEmail(firstchoiseStudent.getEmail());
                Subject subjectToBeAssigned = subjectRepository.findSubjectById(subject.getId());

                studentToAssign.setAssignedSubject(subjectToBeAssigned);
                subjectToBeAssigned.getAssignedStudents().add(studentToAssign);

                studentRepository.save(studentToAssign);
                subjectRepository.save(subjectToBeAssigned);
            }
        }
    }
}
