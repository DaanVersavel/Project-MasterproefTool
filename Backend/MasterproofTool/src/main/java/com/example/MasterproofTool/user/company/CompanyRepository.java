package com.example.MasterproofTool.user.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query(value = "select c from Company c where c.email=?1")
    Company findExistingCompanyByEmail(String email);

    @Query(value = "select c from Company c where c.email=?1")
    Optional<Company> findCompanyByEmail(String email);
}
