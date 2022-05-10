package com.example.MasterproofTool.user.coördinator;

import com.example.MasterproofTool.MasterproefToolApplication;
import com.example.MasterproofTool.user.role.Role;
import com.example.MasterproofTool.user.role.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoördinatorService {

    private final CoördinatorRepository coordinatorRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public CoördinatorService(CoördinatorRepository coordinatorRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.coordinatorRepository = coordinatorRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addRoleToCoordinator(String email, String rolename){
        Coördinator user = coordinatorRepository.findExistingCoordinatorByEmail(email);
        Role role = roleRepository.findByRoleName(rolename);
        user.setRole(role);
        coordinatorRepository.save(user);
    }
    public void encodePassword(Coördinator coördinator){
        coördinator.setPassword(passwordEncoder.encode(coördinator.getPassword()));
    }


    public Optional<Coördinator>  saveNewCoordinator(Coördinator coördinator) {
        //check if coordinator already exist
        Optional<Coördinator> coordinatorByOptional =
                coordinatorRepository.findCoordinatorByEmail(coördinator.getEmail());
        if(coordinatorByOptional.isPresent()){
            throw  new IllegalStateException("Email already taken");
        }
        //if student doesn't exist
        else {
            encodePassword(coördinator);
            coordinatorRepository.save(coördinator);
            addRoleToCoordinator(coördinator.getEmail(), MasterproefToolApplication.ROLE_COÖRDINATOR);
        }
        return coordinatorRepository.findCoordinatorByEmail(coördinator.getEmail());
    }
}
