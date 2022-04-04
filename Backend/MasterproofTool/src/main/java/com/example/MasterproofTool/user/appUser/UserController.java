package com.example.MasterproofTool.user.appUser;

import com.example.MasterproofTool.user.Appuser;
import com.example.MasterproofTool.user.Role;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path ="/User")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService= userService;
    }

    //list of users
    @GetMapping(value = "/users")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Appuser>> getUsers(){
        return ResponseEntity.ok().body(userService.getAppusers());
    }


    @PostMapping(value = "/users/save")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Appuser> saveAppuser(@RequestBody Appuser appuser){
        URI uri=  URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/User/users/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveAppuser(appuser));
    }

    @PostMapping(value = "/role/save")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri=  URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/User/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping(value = "/role/addtouser")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Role> addRoleToAppuser(@RequestBody Role role){

        return ResponseEntity.ok().build();
    }

}

@Data
class RoleToAppuserForm{
    private String username;
    private String rolename;
}

