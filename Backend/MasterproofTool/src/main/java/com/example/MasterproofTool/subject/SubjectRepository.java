package com.example.MasterproofTool.subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //responsible for data acces
public interface SubjectRepository extends JpaRepository<Subject,Long> {
//@Query("SELECT s FROM Subject WHERE s.titlee =?1")
    Optional<Subject> findSubjectByTitle(String title);

    @Query(value = "SELECT s FROM Subject s WHERE s.approved = false ")
    List<Subject>  findSubjectByApprovedFalse();
}
