package com.example.MasterproofTool.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "Subject/")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //post methode for submitting a subject
    @PostMapping(path = "Post/")
    @CrossOrigin(origins = "http://localhost:3000")
    public void registerNewSubject(@RequestBody Subject subject) {
        subjectService.addNewSubject(subject);
    }

    //get method for list of subject
    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Subject> getSubjects() {
        return subjectService.getSubjects();
    }

    //get method for not yet accepted methods
    @GetMapping(path = "Review/")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Subject> getSubjectForReview(){
        return subjectService.getSubjectsForReview();
    }



}