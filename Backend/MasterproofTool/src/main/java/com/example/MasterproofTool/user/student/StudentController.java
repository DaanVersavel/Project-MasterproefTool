package com.example.MasterproofTool.user.student;


import com.example.MasterproofTool.subject.Subject;
import com.example.MasterproofTool.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Optional;
import java.util.Set;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

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

    //saves a student
    @PostMapping(path = "/Save")
    public ResponseEntity<Optional<Student>> saveStudent(@RequestBody Student student){
        URI uri=  URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/Student/Save").toUriString());
        return ResponseEntity.created(uri).body(studentService.saveNewStudent(student));
    }

    //method for setting the first choise
    //id of subject
    @PutMapping(path="/Starred/firstChoise/{id}")
    public void setFirstChoiceSubject(@PathVariable("id") long id,HttpServletRequest request){
        String access_token =getAccesToken(request);
        studentService.setFirstChoice(id,access_token);
    }

    //get method for first choise  uses authorization header
    @GetMapping(path="/GetFirstChoise/")
    public Subject getFirstChoiceSubject(HttpServletRequest request){
        String access_token =getAccesToken(request);
        return studentService.getFirstChoice(access_token);
    }

    //return list of starred subject uses authorization header
    @GetMapping(path="/Starred")
    public Set<Subject> getMySubjects(HttpServletRequest request) {
        String access_token =getAccesToken(request);
        return studentService.getStarred(access_token);
    }

    //Save a new subject to starred list of student
    //id= id of subject
    @PostMapping(path = "/StarredSave/{id}")
    public void addToStarred(@PathVariable("id") long id,HttpServletRequest request){
        String access_token =getAccesToken(request);
        studentService.addToStarred(id, access_token);
    }

    @PutMapping(path = "/StarredRemove/{id}")
    public void removeFromStarred(@PathVariable("id") long id,HttpServletRequest request){
        String access_token =getAccesToken(request);
        studentService.removeFromStarred(id, access_token);
    }

    public String getAccesToken(HttpServletRequest request){
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        return authorizationHeader.substring("Bearer ".length());
    }

}
