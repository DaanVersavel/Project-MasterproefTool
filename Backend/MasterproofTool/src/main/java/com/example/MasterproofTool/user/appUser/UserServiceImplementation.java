package com.example.MasterproofTool.user.appUser;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.MasterproofTool.user.role.Role;
import com.example.MasterproofTool.user.role.RoleRepository;
import com.example.MasterproofTool.user.student.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@Component
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Appuser appUser = userRepository.findByEmail(email);
        if(appUser == null) {
            log.error("User not found in database");
            throw new UsernameNotFoundException("User not found in database");
        }
        else {
            log.info("User found in the database: {}", email);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            Role role =appUser.getRole();
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            return new org.springframework.security.core.userdetails.User(appUser.getEmail(), appUser.getPassword(), authorities);
        }

    }


    @Override
    public Appuser saveAppuser(Appuser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role){
        return roleRepository.save(role);
    }
    @Override
    public void addRoleToAppuser(String email, String rolename){
        Appuser user = userRepository.findByEmail(email);
        Role role = roleRepository.findByRoleName(rolename);
        user.setRole(role);
    }

    @Override
    public Appuser getAppuser(String email){
        return userRepository.findByEmail(email);
    }

    public List<Appuser> getAppusers(){
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(long id) {
        Optional<Appuser> appuserOptional=userRepository.findById(id);
        //if present delete user
        if(appuserOptional.isPresent()){
            userRepository.delete(appuserOptional.get());
        }else{
            throw  new IllegalStateException("User does not exist");
        }
    }

    @Override
    public String getRoleAppuser(String access_token) {
        Appuser user = getAppuserAccessToken(access_token);
        return user.getRole().getRoleName();
    }

    @Override
    public Appuser getAppuserAccessToken(String access_token) {
        Algorithm algorithm = Algorithm.HMAC256("secretthatnobodycanacces".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(access_token);
        String email = decodedJWT.getSubject();
        return userRepository.findByEmail(email);
    }
}
