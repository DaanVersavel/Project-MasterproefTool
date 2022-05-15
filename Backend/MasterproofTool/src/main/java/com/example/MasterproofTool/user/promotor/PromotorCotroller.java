package com.example.MasterproofTool.user.promotor;

import com.example.MasterproofTool.subject.Subject;
import com.example.MasterproofTool.user.student.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping(path = "/Promotor")
public class PromotorCotroller {

    private final PromotorService promotorService;

    public PromotorCotroller(PromotorService promotorService) {
        this.promotorService = promotorService;
    }

    //saves a Promotor
    @PostMapping(path = "/Save")
    public ResponseEntity<Optional<Promotor>> saveCompany(@RequestBody Promotor promotor) {
        URI uri=  URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/Promotor/Save").toUriString());
        return ResponseEntity.created(uri).body(promotorService.saveNewPromotor(promotor));
    }

    //return list of Promotors
    @GetMapping()
    public List<Promotor> getPromotors(){
        return promotorService.getPromotors();
    }

    //return list of the subjects of a promotor
    @GetMapping(path = "/MySubjects")
    public List<Subject> getMySubjects(HttpServletRequest request){
        return promotorService.getMySubjects(getAccesToken(request));
    }
    //return list of the students with first choise a specific subject
    //id = id of subject
    @GetMapping(path = "/MySubjects/Students/{id}")
    public List<Student> getMySubjectsStudentList(@PathVariable("id") long subjectid,HttpServletRequest request){
        return promotorService.getStudentListPerSubject(getAccesToken(request),subjectid);
    }
    //to boost a student
    @PutMapping(path= "/MySubjects/Boost/{subjectid}/{studentid}")
    public void boostStudent(@PathVariable("subjectid") long subjectid,@PathVariable("studentid") long studentid){
        promotorService.boostStudent(subjectid,studentid);
    }

    public String getAccesToken(HttpServletRequest request){
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        return authorizationHeader.substring("Bearer ".length());
    }
}
