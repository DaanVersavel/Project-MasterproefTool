package com.example.MasterproofTool.user.appUser;

import com.example.MasterproofTool.user.role.Role;
import com.example.MasterproofTool.user.role.RoleRepository;
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
            appUser.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
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
        user.getRoles().add(role);
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
}
