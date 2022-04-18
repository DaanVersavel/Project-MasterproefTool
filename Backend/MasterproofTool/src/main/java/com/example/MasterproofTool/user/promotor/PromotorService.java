package com.example.MasterproofTool.user.promotor;

import com.example.MasterproofTool.MasterproefToolApplication;
import com.example.MasterproofTool.user.Promotor;
import com.example.MasterproofTool.user.Role;
import com.example.MasterproofTool.user.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PromotorService {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final PromotorRepository promotorRepository;

    public PromotorService(PasswordEncoder passwordEncoder, RoleRepository roleRepository, PromotorRepository promotorRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.promotorRepository = promotorRepository;
    }

    public Optional<Promotor> saveNewPromotor(Promotor promotor) {
        //check if Promotor already exist
        Optional<Promotor> promotorByOptional =
                promotorRepository.findPromotorByEmail(promotor.getEmail());
        if(promotorByOptional.isPresent()){
            throw  new IllegalStateException("Email already taken");
        }
        //if student doesn't exist
        else {
            encodePassword(promotor);
            promotorRepository.save(promotor);
            addRoleToCompany(promotor.getEmail(), MasterproefToolApplication.ROLE_PROMOTOR);

        }
        return promotorRepository.findPromotorByEmail(promotor.getEmail());
    }
    public void encodePassword(Promotor promotor){
        promotor.setPassword(passwordEncoder.encode(promotor.getPassword()));
    }

    public void addRoleToCompany(String email, String rolename){
        Promotor user = promotorRepository.findExistingPromotorByEmail(email);
        Role role = roleRepository.findByRoleName(rolename);
        user.getRoles().add(role);
    }
}
