package com.example.MasterproofTool.user.appUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository //responsible for data acces
@Component
public interface UserRepository extends JpaRepository<Appuser,Long> {
    Appuser findByEmail(String email);
}
