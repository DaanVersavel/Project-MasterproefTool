package com.example.MasterproofTool.user.appUser;
import com.example.MasterproofTool.user.Role;
import com.example.MasterproofTool.user.Appuser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public interface UserService {

    Appuser saveAppuser(Appuser user);
    Role saveRole(Role role);
    void addRoleToAppuser(String username,String rolename);
    Appuser getAppuser(String email);
    List<Appuser>getAppusers();
}
