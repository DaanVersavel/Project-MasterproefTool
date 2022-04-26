package com.example.MasterproofTool.user.coördinator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CoördinatorRepository  extends JpaRepository<Coördinator,Long> {
    @Query(value = "select c from Coördinator c where c.email=?1")
    Coördinator findExistingCoordinatorByEmail(String email);

    @Query(value = "select c from Coördinator c where c.email=?1")
    Optional<Coördinator> findCoordinatorByEmail(String email);
}
