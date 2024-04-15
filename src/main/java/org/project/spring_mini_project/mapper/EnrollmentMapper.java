package org.project.spring_mini_project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.project.spring_mini_project.domain.Enrollment;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentProgressRequest;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentProgressRespone;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentRespone;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    EnrollmentRespone maptoEnrollMentRespone(Enrollment enrollment);

    @Mapping(target = "course_id", ignore = true)
    Enrollment mapEnrollmentRequestToEnrollment(EnrollmentProgressRequest enrollmentProgressRequest);

    EnrollmentProgressRespone maptoEnrollMentProgressRespone(Enrollment enrollment);
}
