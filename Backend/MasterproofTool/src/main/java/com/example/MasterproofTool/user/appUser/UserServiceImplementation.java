package com.example.MasterproofTool.user.appUser;

import com.example.MasterproofTool.user.Role;
import com.example.MasterproofTool.user.RoleRepository;
import com.example.MasterproofTool.user.User;
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
    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role){
        return roleRepository.save(role);
    }
    @Override
    public void addRoleToUser(String email, String rolename){
        User user = userRepository.findByEmail(email);
        Role role = roleRepository.findByName(rolename);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
