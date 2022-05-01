package com.example.MasterproofTool.user.company;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.MasterproofTool.MasterproefToolApplication;
import com.example.MasterproofTool.subject.Subject;
import com.example.MasterproofTool.subject.SubjectRepository;
import com.example.MasterproofTool.user.role.Role;
import com.example.MasterproofTool.user.role.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final SubjectRepository subjectRepository;

    public CompanyService(CompanyRepository companyRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, SubjectRepository subjectRepository) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.subjectRepository = subjectRepository;
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

    public List<Subject> getSubjects(String access_token) {
        Optional<Company> company= getCompanyHeader(access_token);
        return subjectRepository.findSubjectByCompany(company);

    }

    public Optional<Company> getCompanyHeader(String access_token) {
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(access_token);
        String email = decodedJWT.getSubject();
        return companyRepository.findCompanyByEmail(email);
    }
}
