package com.example.MasterproofTool.user.promotor;

import com.example.MasterproofTool.subject.Subject;
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

    public String getAccesToken(HttpServletRequest request){
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        return authorizationHeader.substring("Bearer ".length());
    }
}
