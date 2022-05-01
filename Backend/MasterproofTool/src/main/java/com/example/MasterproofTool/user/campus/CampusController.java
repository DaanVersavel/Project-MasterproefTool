package com.example.MasterproofTool.user.campus;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    //get method for list of campusses
    @GetMapping
    public List<Campus> getCampuses(){
        return campusService.getCampuses();
    }
}
