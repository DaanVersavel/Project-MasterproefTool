package com.example.MasterproofTool.user.disciplines;

import com.example.MasterproofTool.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline,Long> {

//    @Query("SELECT Subject from Discipline d where ?1 member of d.campussen AND ?2 member of d.campussen")
//    List<Subject> getSubjectsForReview(Campus campus, Discipline discipline) ;

    Discipline findByNaam(String name);

    @Query("SELECT d.subjects from Discipline d where ?1 member of d.campussen AND ?2 member of d.campussen")
    List<Subject> findSubjectByDisciplineApprovedFalseAndDeniedFalse(Discipline discipline);

    @Query("SELECT d.subjects from Discipline d where d=?1")
    List<Subject> findSubjectByDiscipline(Discipline discipline);
}
