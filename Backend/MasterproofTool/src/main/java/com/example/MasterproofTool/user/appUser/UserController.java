package com.example.MasterproofTool.user.appUser;


import com.example.MasterproofTool.user.Appuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path ="/User/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService= userService;
    }


    @GetMapping(value = "/users/")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Appuser>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }
}
