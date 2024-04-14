package org.project.spring_mini_project.features.enrollment;

import org.project.spring_mini_project.features.enrollment.dto.EnrollmentRespone;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentProgressRequest;

public interface EnrollmentService {
    EnrollmentRespone createEnrollment(EnrollmentProgressRequest enrollmentProgressRequest);

    EnrollmentRespone getEnrollment(Long id);

}
