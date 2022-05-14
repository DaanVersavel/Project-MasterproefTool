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

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
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
    @PutMapping(path="/Starred/FirstChoise/{id}")
    public void setFirstChoiceSubject(@PathVariable("id") long id,HttpServletRequest request){
        String access_token =getAccesToken(request);
        studentService.setFirstChoice(id,access_token);
    }
    //method for setting the second choise
    //id of subject
    @PutMapping(path="/Starred/SecondChoise/{id}")
    public void setSecondChoiceSubject(@PathVariable("id") long id,HttpServletRequest request){
        String access_token =getAccesToken(request);
        studentService.setSecondChoice(id,access_token);
    }
    //method for setting the third choise
    //id of subject
    @PutMapping(path="/Starred/ThirdChoise/{id}")
    public void setThirdChoiceSubject(@PathVariable("id") long id,HttpServletRequest request){
        String access_token =getAccesToken(request);
        studentService.setThirdChoice(id,access_token);
    }

    //get method for first choise  uses authorization header
    @GetMapping(path="/GetFirstChoise")
    public Subject getFirstChoiceSubject(HttpServletRequest request){
        String access_token =getAccesToken(request);
        return studentService.getFirstChoice(access_token);
    }

    //get method for second choise  uses authorization header

    @GetMapping(path="/GetSecondChoice")
    public Subject getSecondChoiceSubject(HttpServletRequest request){
        String access_token =getAccesToken(request);
        return studentService.getSecondChoice(access_token);
    }

    //get method for third choise  uses authorization header
    @GetMapping(path="/GetThirdChoice")
    public Subject getThirdChoiceSubject(HttpServletRequest request){
        String access_token =getAccesToken(request);
        return studentService.getThirdChoice(access_token);
    }
    //return list of starred subject uses authorization header
    @GetMapping(path="/Starred")
    public Set<Subject> getMySubjects(HttpServletRequest request) {
        String access_token =getAccesToken(request);
        return studentService.getStarred(access_token);
    }

    //Save a new subject to starred list of student
    //id= id of subject
    @PutMapping(path = "/StarredSave/{id}")
    public void addToStarred(@PathVariable("id") long id,HttpServletRequest request){
        String access_token =getAccesToken(request);
        studentService.addToStarred(id, access_token);
    }
    //removes starred subject

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
