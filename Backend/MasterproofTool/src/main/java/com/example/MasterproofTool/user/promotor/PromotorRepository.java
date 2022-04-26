package com.example.MasterproofTool.user.promotor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PromotorRepository extends JpaRepository<Promotor,Long> {
    @Query(value = "select p from Promotor p where p.email=?1")
    Promotor findExistingPromotorByEmail(String email);

    @Query(value = "select p from Promotor p where p.email=?1")
    Optional<Promotor> findPromotorByEmail(String email);
}
