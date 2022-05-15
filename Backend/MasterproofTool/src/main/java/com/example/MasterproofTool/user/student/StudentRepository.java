package com.example.MasterproofTool.user.student;

import com.example.MasterproofTool.subject.Subject;
import com.example.MasterproofTool.user.disciplines.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query(value = "select s from Student s where s.keyId=?1")
    Student findByKeyId(long keyId);

    @Query(value = "select s from Student s where s.email=?1")
    Optional<Student> findStudentByEmail(String email);

    @Query(value = "select s from Student s where s.email=?1")
    Student findExistingStudentByEmail(String email);

    @Query(value = "select s from Student s where s.discipline=?1")
    List<Student> findStudentByDiscipline(Discipline discipline);

    @Query(value = "select s from Student s where s.firstChoice=?1")
    List<Student> findStudentBySubjectFirstchoise(Subject subject);
}
