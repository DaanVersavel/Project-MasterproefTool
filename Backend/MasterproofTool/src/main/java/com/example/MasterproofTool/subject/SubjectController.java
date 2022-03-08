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

    @PostMapping(path="Post/")
    @CrossOrigin(origins = "http://localhost:3000")
    public void registerNewSubject(@RequestBody Subject subject){
        subjectService.addNewSubject(subject);
    }


    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Subject> getSubjects(){ return subjectService.getSubjects();
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Subject> getSubjectsCampus(){ return subjectService.getSubjects();
    }
    //Campus Leuven
    //Campus Groep T Leuven
    //Campus Brussel
    //Campus Sint-Lucas Brussel
    //Campus Antwerpen
    //Campus Geel
    //Campus De Nayer Sint-Katelijne-Waver
    //Campus Sint-Lucas Gent
    //Technologiecampus Gent
    //
    //
    //
}
