package com.example.MasterproofTool.user.appUser;

import com.example.MasterproofTool.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //responsible for data acces
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
