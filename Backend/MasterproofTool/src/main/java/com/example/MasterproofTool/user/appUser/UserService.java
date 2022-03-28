package com.example.MasterproofTool.user.appUser;
import com.example.MasterproofTool.user.Role;
import com.example.MasterproofTool.user.Appuser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public interface UserService {

    Appuser saveUser(Appuser user);
    Role saveRole(Role role);
    void addRoleToUser(String username,String rolename);
    Appuser getUser(String email);
    List<Appuser>getUsers();
}
