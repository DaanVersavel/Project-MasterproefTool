package com.example.MasterproofTool.user.disciplines;

import com.example.MasterproofTool.user.Appuser;
import com.example.MasterproofTool.user.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline,Long> {

    Discipline findByNaam(String name);


}
