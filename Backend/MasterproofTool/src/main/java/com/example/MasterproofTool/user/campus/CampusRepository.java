package com.example.MasterproofTool.user.campus;


import com.example.MasterproofTool.user.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampusRepository extends JpaRepository<Campus,Long> {

    Campus findByName(String name);


}
