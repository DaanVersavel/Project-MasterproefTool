package com.example.MasterproofTool.user.company;

import com.example.MasterproofTool.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping(path = "/Company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    //Saves a new company
    @PostMapping(path = "/Save")
    public ResponseEntity<Optional<Company>> saveCompany(@RequestBody Company company){
        URI uri=  URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/Company/Save").toUriString());
        return ResponseEntity.created(uri).body(companyService.saveNewCompany(company));
    }

    //gives a list of company's
    @GetMapping
    public List<Subject> getSubjects(HttpServletRequest request){
        String email=getAccesToken(request);
        return companyService.getSubjects(email);
    }


    public String getAccesToken(HttpServletRequest request){
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        return authorizationHeader.substring("Bearer ".length());
    }

}
