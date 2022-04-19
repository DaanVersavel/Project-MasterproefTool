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

    //post methode for submitting a subject
    @PostMapping(path="/Post")
    @CrossOrigin(origins = "*")
    public void registerNewSubject(@RequestBody Subject subject){
        subjectService.addNewSubject(subject);
    }

    //get method for list of subject that are approved
    @GetMapping
    @CrossOrigin(origins = "*")
    public List<Subject> getSubjectsApproved(){ return subjectService.getSubjectsApproved();
    }

    //get method for not yet accepted methods
    @GetMapping(path = "/Review")
    @CrossOrigin(origins = "https://localhost:3000")
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

    //TODO doesn't work yet
    //get method for getting your specific subjects
    @GetMapping(path = "/MySubjects")
    @CrossOrigin(origins = "https://localhost:3000")
    public List<Subject> getSubjectForUser(@RequestBody long coordinator_id){
        return subjectService.getSubjectForUser(coordinator_id);
    }
}

