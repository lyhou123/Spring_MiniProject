package org.project.spring_mini_project.features.enrollment;

import org.project.spring_mini_project.features.enrollment.dto.*;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponse enrollStudent(EnrollmentRequest enrollmentRequest);
    List<EnrollmentResponse> getEnrollments(int page,int size,String code, String courseTitle, String courseCategory, String studentUsername, Boolean is_certified);
    EnrollmentProgressResponse updateEnrollmentProgress(String code, EnrollmentProgressRequest enrollmentProgressRequest);
    EnrollmentProgressResponse getEnrollmentProgress(String code);
    EnrollmentResponse certifyEnrollment(String code);
    EnrollmentResponse discardEnrollment(String code);

    EnrollmentDetailsResponse getEnrollmentDetails(String code);

}
