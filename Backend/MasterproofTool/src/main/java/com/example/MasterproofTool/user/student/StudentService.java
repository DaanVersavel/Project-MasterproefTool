package com.example.MasterproofTool.user.student;



import com.example.MasterproofTool.subject.Subject;
import com.example.MasterproofTool.user.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Set<Subject> getStudentStarred(long keyId) {
        Student s=studentRepository.findByKeyId(keyId);

        return s.getStarredSubjects();
    }
}
