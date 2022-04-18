package com.example.MasterproofTool.user.promotor;

import com.example.MasterproofTool.user.Promotor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = "/Promotor")
public class PromotorCotroller {

    private final PromotorService promotorService;

    public PromotorCotroller(PromotorService promotorService) {
        this.promotorService = promotorService;
    }

    @PostMapping(path = "/Save")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Optional<Promotor>> saveCompany(@RequestBody Promotor promotor) {
        URI uri=  URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/Promotor/Save").toUriString());
        return ResponseEntity.created(uri).body(promotorService.saveNewPromotor(promotor));
    }
}
