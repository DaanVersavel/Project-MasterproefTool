package com.example.MasterproofTool.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping(path = "/Subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //get method for list of subject
    @GetMapping
    public List<Subject> getSubjects(HttpServletRequest request){
        String access_token =getAccesToken(request);
        return subjectService.getSubjectsApproved(access_token);
    }
    //Post method for subjects
    @PostMapping(path="/Post")
    public void registerNewSubject(@RequestBody Subject subject){
        subjectService.addNewSubject(subject);
    }

    //update method to assign true to subject
    @PutMapping(path="/Review/Approved/{id}")
    public ResponseEntity<Subject> updateSubjectReviewApprovedTrue(@PathVariable long id){
        return ResponseEntity.ok(subjectService.updateSubjectReviewApprovedTrue(id));
    }
    //updates subject to denied
    @PutMapping(path="/Review/Denied/{id}")
    public ResponseEntity<Subject> updateSubjectReviewDeniedTrue(@PathVariable long id){
        return ResponseEntity.ok(subjectService.updateSubjectReviewDeniedTrue(id));
    }
    //get list of denied
    @GetMapping(path = "/Denied")
    public List<Subject> getSubjectsDenied(){
        return subjectService.getSubjectsDenied();
    }

    //method to get specific subject
    @GetMapping(path="/{id}")
    public Subject getSpecificSubject(@PathVariable long id){
        return subjectService.getSpecificSubject(id);
    }


    public String getAccesToken(HttpServletRequest request){
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        return authorizationHeader.substring("Bearer ".length());
    }

}

