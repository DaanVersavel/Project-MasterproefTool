package com.example.MasterproofTool.user.campus;

import com.example.MasterproofTool.user.Campus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/Campus")
public class CampusController {
    //import of services
    private final CampusService campusService;

    @Autowired
    public CampusController(CampusService campusService) {
        this.campusService = campusService;
    }
    //TODO unidirectional maken?????
    //get method for list of campusses
    @GetMapping
    @CrossOrigin(origins = "*")
    public List<Campus> getCampuses(){
        return campusService.getCampuses();
    }
}
