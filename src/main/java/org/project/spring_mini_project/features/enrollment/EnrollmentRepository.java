package org.project.spring_mini_project.features.enrollment;

import org.project.spring_mini_project.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>
{

}
