package com.example.MasterproofTool.user.student;


import com.example.MasterproofTool.subject.Subject;
import com.example.MasterproofTool.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/Student")
public class StudentController {


    private final StudentService studentService;
    private final SubjectService subjectService;

    @Autowired
    public StudentController(StudentService studentService, SubjectService subjectService) {
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @GetMapping(path = "/{keyId}")
    public Set<Subject> getSubjectsStarred(@PathVariable("keyId") long keyId){
        return studentService.getStudentStarred(keyId);
    }



}
