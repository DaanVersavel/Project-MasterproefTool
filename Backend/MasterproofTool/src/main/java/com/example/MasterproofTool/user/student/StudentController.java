package com.example.MasterproofTool.user.student;


import com.example.MasterproofTool.subject.Subject;
import com.example.MasterproofTool.subject.SubjectService;
import com.example.MasterproofTool.user.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
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

    @PostMapping(path = "/Save")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Optional<Student>> saveStudent(@RequestBody Student student){
        URI uri=  URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/Student/Save").toUriString());
        return ResponseEntity.created(uri).body(studentService.saveNewStudent(student));
    }

    //List of starred subjects
    //keyId= id student
    @GetMapping(path = "/Starred/{keyId}")
    @CrossOrigin(origins = "*")
    public Set<Subject> getSubjectsStarred(@PathVariable("keyId") long keyId){
        return studentService.getStudentStarred(keyId);
    }

    //method for setting the first choise
    //keyid = id student, id =id subject
    @PutMapping(path="/Starred/firstChoise/{keyId}/{id}")
    @CrossOrigin(origins = "*")
    public void setFirstChoiceSubject(@PathVariable("keyId") long keyId,@PathVariable("id") long id){
        //id for subject
        studentService.setFirstChoice(keyId,id);
    }

    //get method for first choise
    //keyId= id student
    @GetMapping(path="/GetFirstChoise/{keyId}")
    @CrossOrigin(origins = "*")
    public Subject getFirstChoiceSubject(@PathVariable("keyId") long keyId){
        return studentService.getFirstChoice(keyId);
    }


}
