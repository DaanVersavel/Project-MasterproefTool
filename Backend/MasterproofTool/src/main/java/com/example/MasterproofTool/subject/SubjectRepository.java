package com.example.MasterproofTool.subject;

import com.example.MasterproofTool.user.company.Company;
import com.example.MasterproofTool.user.promotor.Promotor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //responsible for data acces
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    @Query(value = "SELECT t FROM Subject t WHERE t.title =?1")
    Optional<Subject> findSubjectByTitle(String title);

    @Query(value = "SELECT t FROM Subject t WHERE t.id =?1")
    Subject findSubjectById(long id);

    @Query(value = "SELECT s FROM Subject s WHERE s.approved = true ")
    List<Subject> findSubjectByApprovedTrue();

    @Query(value = "SELECT s FROM Subject s WHERE s.approved = false ")
    List<Subject>  findSubjectByApprovedFalse();

    @Query(value = "SELECT s FROM Subject s WHERE s.coordinator =?1")
    List<Subject> findSubjectByCoordinator_Id(long coördinator_id);

    @Query(value = "SELECT s FROM Subject s WHERE s.approved = false AND s.denied=false")
    List<Subject> findSubjectByApprovedFalseAndDeniedFalse();

    @Query(value = "SELECT s FROM Subject s WHERE s.approved = true AND s.denied=false")
    List<Subject> findSubjectByApprovedTrueAndDeniedFalse();

    @Query(value = "SELECT s FROM Subject s WHERE s.company = ?1")
    List<Subject> findSubjectByCompany(Optional<Company> company);

    @Query(value = "SELECT s FROM Subject s WHERE s.promotor = ?1")
    List<Subject> findSubjectByPromotor(Promotor p);
}
