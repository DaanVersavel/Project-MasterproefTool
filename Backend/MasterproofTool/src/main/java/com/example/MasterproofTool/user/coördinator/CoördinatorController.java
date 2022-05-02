package com.example.MasterproofTool.user.coördinator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = "/Coordinator")
public class CoördinatorController {

    private final CoördinatorService coordinatorService;

    @Autowired
    public CoördinatorController(CoördinatorService coordinatorService) {
        this.coordinatorService = coordinatorService;
    }

    //saves a Coordinator
    @PostMapping(path = "/Save")
    public ResponseEntity<Optional<Coördinator>> saveCoördinator(@RequestBody Coördinator coördinator){
        URI uri=  URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/Coordinator/Save").toUriString());
        return ResponseEntity.created(uri).body(coordinatorService.saveNewCoördinator(coördinator));
    }
}
