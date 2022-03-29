package com.example.MasterproofTool.subject;

import org.springframework.beans.factory.annotation.Autowired;
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

    //Post method for subjects
    @PostMapping(path="/Post")
    @CrossOrigin(origins = "*")
    public void registerNewSubject(@RequestBody Subject subject){
        subjectService.addNewSubject(subject);
    }

    //get method for list of subject
    @GetMapping
    @CrossOrigin(origins = "*")
    public List<Subject> getSubjects(){ return subjectService.getSubjects();
    }

    //get method for not yet accepted methods
    @GetMapping(path = "/Review")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Subject> getSubjectForReview(){
        return subjectService.getSubjectsForReview();
    }
}

