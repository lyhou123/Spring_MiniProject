package org.project.spring_mini_project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.project.spring_mini_project.domain.Course;
import org.project.spring_mini_project.domain.Enrollment;
import org.project.spring_mini_project.domain.Student;
import org.project.spring_mini_project.domain.User;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentProgressRequest;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentProgressRespone;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentRespone;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {
//    @Mapping(target = "course_id", ignore = true)

    @Mapping(source = "student", target = "student_id", qualifiedByName = "mapUserToUserId")
    EnrollmentRespone maptoEnrollMentRespone(Enrollment enrollment);

//    @Mapping(target = "course_id", ignore = true)
    Enrollment mapEnrollmentRequestToEnrollment(EnrollmentProgressRequest enrollmentProgressRequest);

    EnrollmentProgressRespone maptoEnrollMentProgressRespone(Enrollment enrollment);

    @Named("mapUserToUserId")
    default Integer mapUserToUserId(Student student) {
        return student != null ? student.getId() : null;
    }


}
