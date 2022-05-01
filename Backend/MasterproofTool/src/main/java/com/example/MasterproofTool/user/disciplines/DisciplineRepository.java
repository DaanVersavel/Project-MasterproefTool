package com.example.MasterproofTool.user.disciplines;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline,Long> {

    Discipline findByNaam(String name);


}
