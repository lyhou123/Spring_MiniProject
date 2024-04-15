package org.project.spring_mini_project.features.enrollment;

import jakarta.transaction.Transactional;
import org.project.spring_mini_project.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>
{
    @Modifying
    @Transactional
    @Query("UPDATE Enrollment u SET u.is_deleted = :status WHERE u.id = :Id")
    int updateBlockedStatusById(Long Id, boolean status);
}
