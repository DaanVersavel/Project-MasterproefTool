package com.example.MasterproofTool.user.coördinator;

import com.example.MasterproofTool.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping(path = "/Coordinator")
public class CoördinatorController {

    private final CoördinatorService coordinatorService;

    @Autowired
    public CoördinatorController(CoördinatorService coordinatorService) {
        this.coordinatorService = coordinatorService;
    }

    //saves a Coordinator
    @PostMapping(path = "/Save")
    public ResponseEntity<Coördinator> saveCoördinator(@RequestBody Coördinator coördinator){
        URI uri=  URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/Coordinator/Save").toUriString());
        return ResponseEntity.created(uri).body(coordinatorService.saveNewCoordinator(coördinator));
    }
    //list of subjects to review
    @GetMapping(path = "/Review")
    public List<Subject> getSubjectForReview(HttpServletRequest request){
        String access_token =getAccesToken(request);
        return coordinatorService.getSubjectsForReview(access_token);
    }
    // return lisst of assigned subjects for a discipline
    @GetMapping(path = "/AssignedSubjects")
    public List<Subject> getAssignedSubjects(HttpServletRequest request){
        String access_token =getAccesToken(request);
        return coordinatorService.getAssignedSubjects(access_token);
    }

    //list of not assigned subjects
    @GetMapping(path = "/NotAssignedSubjects")
    public List<Subject> getNotAssignedSubjects(HttpServletRequest request){
        String access_token =getAccesToken(request);
        return coordinatorService.getNotAssignedSubjects(access_token);
    }
    //auto assign boosted students
    @GetMapping(path = "/AutoAssignBoosted")
    public void autoAssignBoostedStudents(HttpServletRequest request){
        String access_token =getAccesToken(request);
        coordinatorService.autoAssignBoostedStudents(access_token);
    }

    //auto assign firstchoise algoritm
    @GetMapping(path = "/AutoAssignFirstChoise")
    public void autoAssignFirstChoise(HttpServletRequest request){
        String access_token =getAccesToken(request);
        coordinatorService.autoAssignFirstChoise(access_token);
    }


    public String getAccesToken(HttpServletRequest request){
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        return authorizationHeader.substring("Bearer ".length());
    }
}
