package com.example.MasterproofTool.subject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    //saving of a subject
    public void addNewSubject(Subject subject) {
        Optional<Subject> subjectByOptional =
                subjectRepository.findSubjectByTitle(subject.getTitle());
        if(subjectByOptional.isPresent()){
            throw  new IllegalStateException("Subject title already taken");
        }
        subjectRepository.save(subject);

    }
}
