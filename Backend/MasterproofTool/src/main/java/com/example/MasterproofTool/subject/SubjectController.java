package com.example.MasterproofTool.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Subject> getSubjects(){
        return subjectService.getSubjectsApproved();
    }
    //Post method for subjects
    @PostMapping(path="/Post")
    public void registerNewSubject(@RequestBody Subject subject){
        subjectService.addNewSubject(subject);
    }

    //get method for not yet accepted methods
    @GetMapping(path = "/Review")
    public List<Subject> getSubjectForReview(){
        return subjectService.getSubjectsForReview();
    }

    //update method to assign true to subject
    @PutMapping(path="/Review/Approved/{id}")
    public ResponseEntity<Subject> updateSubjectReviewApprovedTrue(@PathVariable long id){
        return ResponseEntity.ok(subjectService.updateSubjectReviewApprovedTrue(id));
    }
    @PutMapping(path="/Review/Denied/{id}")
    public ResponseEntity<Subject> updateSubjectReviewDeniedTrue(@PathVariable long id){
        return ResponseEntity.ok(subjectService.updateSubjectReviewDeniedTrue(id));
    }
    @GetMapping(path = "/Denied")
    public List<Subject> getSubjectsDenied(){
        return subjectService.getSubjectsDenied();
    }

    //method to get specific subject
    @GetMapping(path="/{id}")
    public Subject getSpecificSubject(@PathVariable long id){
        return subjectService.getSpecificSubject(id);
    }

    //TODO doesn't work yet
    //get method for getting your specific subjects
    @GetMapping(path = "/MySubjects")
    public List<Subject> getSubjectForUser(@RequestBody long coordinator_id){
        return subjectService.getSubjectForUser(coordinator_id);
    }
}

