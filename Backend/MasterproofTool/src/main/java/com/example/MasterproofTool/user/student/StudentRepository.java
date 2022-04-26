package com.example.MasterproofTool.user.student;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query(value = "select s from Student s where s.keyId=?1")
    Student findByKeyId(long keyId);

    @Query(value = "select s from Student s where s.email=?1")
    Optional<Student> findStudentByEmail(String email);

    @Query(value = "select s from Student s where s.email=?1")
    Student findExistingStudentByEmail(String email);
}
