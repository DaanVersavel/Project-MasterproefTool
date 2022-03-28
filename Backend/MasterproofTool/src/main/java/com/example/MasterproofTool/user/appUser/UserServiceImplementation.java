package com.example.MasterproofTool.user.appUser;

import com.example.MasterproofTool.user.Role;
import com.example.MasterproofTool.user.RoleRepository;
import com.example.MasterproofTool.user.Appuser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Override
    public Appuser saveAppuser(Appuser user){
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role){
        return roleRepository.save(role);
    }
    @Override
    public void addRoleToAppuser(String email, String rolename){
        Appuser user = userRepository.findByEmail(email);
        Role role = roleRepository.findByName(rolename);
        user.getRoles().add(role);
    }

    @Override
    public Appuser getAppuser(String email){
        return userRepository.findByEmail(email);
    }

    public List<Appuser> getAppusers(){
        return userRepository.findAll();
    }
}
