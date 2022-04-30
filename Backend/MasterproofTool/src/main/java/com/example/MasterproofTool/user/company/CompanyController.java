package com.example.MasterproofTool.user.company;

import com.example.MasterproofTool.user.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = "/Company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping(path = "/Save")
    public ResponseEntity<Optional<Company>> saveCompany(@RequestBody Company company){
        URI uri=  URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/Company/Save").toUriString());
        return ResponseEntity.created(uri).body(companyService.saveNewCompany(company));
    }

}
