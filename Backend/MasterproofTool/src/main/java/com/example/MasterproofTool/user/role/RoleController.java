package com.example.MasterproofTool.user.role;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/Role")
public class RoleController {
    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    //returns list of roles
    @GetMapping()
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}

