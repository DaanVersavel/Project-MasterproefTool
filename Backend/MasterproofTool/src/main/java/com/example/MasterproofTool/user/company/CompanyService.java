package com.example.MasterproofTool.user.company;

import com.example.MasterproofTool.MasterproefToolApplication;
import com.example.MasterproofTool.user.Company;
import com.example.MasterproofTool.user.Role;
import com.example.MasterproofTool.user.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public CompanyService(CompanyRepository companyRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    public Optional<Company> saveNewCompany(Company company) {
        //check if student already exist
        Optional<Company> companyByOptional =
                companyRepository.findCompanyByEmail(company.getEmail());
        if(companyByOptional.isPresent()){
            throw  new IllegalStateException("Email already taken");
        }
        //if student doesn't exist
        else {
            encodePassword(company);
            companyRepository.save(company);
            addRoleToCompany(company.getEmail(), MasterproefToolApplication.ROLE_COMPANY);

        }
        return companyRepository.findCompanyByEmail(company.getEmail());
    }

    public void encodePassword(Company company){
        company.setPassword(passwordEncoder.encode(company.getPassword()));
    }

    public void addRoleToCompany(String email, String rolename){
        Company user = companyRepository.findExistingCompanyByEmail(email);
        Role role = roleRepository.findByRoleName(rolename);
        user.getRoles().add(role);
    }

}
