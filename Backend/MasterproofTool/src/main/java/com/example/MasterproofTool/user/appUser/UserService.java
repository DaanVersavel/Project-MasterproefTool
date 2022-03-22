package com.example.MasterproofTool.user.appUser;
import com.example.MasterproofTool.user.Role;
import com.example.MasterproofTool.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username,String rolename);
    User getUser(String username);
    List<User>getUsers();
}
