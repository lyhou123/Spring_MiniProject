package org.project.spring_mini_project.features.enrollment;

import org.project.spring_mini_project.features.enrollment.dto.EnrollmentProgressRespone;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentRespone;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentProgressRequest;

import java.util.List;

public interface EnrollmentService {
    EnrollmentRespone createEnrollment(EnrollmentProgressRequest enrollmentProgressRequest);

    EnrollmentRespone getEnrollment(Long id);

    List< EnrollmentRespone> getEnrollment(String sortBy, String filterBy, String filterValue, int perPage, int page);

    EnrollmentProgressRespone updateEnrollment(Long id, EnrollmentProgressRequest enrollmentProgressRequest);

    EnrollmentProgressRespone getProgress(Long id);

    EnrollmentRespone disableEnrollment(Long id);

    List<EnrollmentRespone> getAllEnrollments();

}
