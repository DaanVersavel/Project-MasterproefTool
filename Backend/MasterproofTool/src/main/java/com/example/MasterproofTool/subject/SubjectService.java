package com.example.MasterproofTool.subject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {



    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getSubjectsApproved() { return subjectRepository.findSubjectByApprovedTrue(); }



    //saving of a subject
    public void addNewSubject(Subject subject) {
        Optional<Subject> subjectByOptional =
                subjectRepository.findSubjectByTitle(subject.getTitle());
        if(subjectByOptional.isPresent()){
            throw  new IllegalStateException("Subject title already taken");
        }
        subjectRepository.save(subject);
    }

    public List<Subject> getSubjectsForReview() {
        return subjectRepository.findSubjectByApprovedFalse();
    }

    public Subject updateSubjectReviewedTrue(@PathVariable long id){
        Subject subject = subjectRepository.findSubjectById(id);
        subject.setApprovedTrue();
        return subjectRepository.save(subject);
    }

    public List<Subject> getSubjectForUser(long coordinator_id) {
        return subjectRepository.findSubjectByCoordinator_Id( coordinator_id);
    }
}
