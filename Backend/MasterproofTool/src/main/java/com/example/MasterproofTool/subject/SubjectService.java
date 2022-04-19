package com.example.MasterproofTool.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getSubjectsApproved() { return subjectRepository.findSubjectByApprovedTrueAndDeniedFalse(); }

    //saving of a subject
    public void addNewSubject(Subject subject) {
        Optional<Subject> subjectByOptional = subjectRepository.findSubjectByTitle(subject.getTitle());
        if(subjectByOptional.isPresent()){
            throw  new IllegalStateException("Subject title already taken");
        }
        subjectRepository.save(subject);
    }
    public List<Subject> getSubjectsForReview() {
        return subjectRepository.findSubjectByApprovedFalseAndDeniedFalse();
    }

    public Subject updateSubjectReviewApprovedTrue(long id){
        Subject subject = subjectRepository.findSubjectById(id);
        subject.setApprovedTrue();
        return subjectRepository.save(subject);
    }

    public List<Subject> getSubjectForUser(long coordinator_id) {
        return subjectRepository.findSubjectByCoordinator_Id( coordinator_id);
    }

    public Subject updateSubjectReviewDeniedTrue(long id) {
        Subject subject = subjectRepository.findSubjectById(id);
        subject.setDeniedTrue();
        return subjectRepository.save(subject);
    }
}
