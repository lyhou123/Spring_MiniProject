package org.project.spring_mini_project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.project.spring_mini_project.domain.Course;
import org.project.spring_mini_project.domain.Enrollment;
import org.project.spring_mini_project.domain.User;
import org.project.spring_mini_project.features.enrollment.dto.*;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    @Mapping(target = "courseTitle", source = "course.title")
    @Mapping(target = "courseCategory", source = "course.categories.name") // changed to 'course.categories.name'
    @Mapping(target = "studentName", source = "student.user.username")
    EnrollmentResponse toEnrollmentResponse(Enrollment enrollment);

    EnrollmentProgressResponse toEnrollmentProgressResponse(Enrollment enrollment);

    Enrollment toEnrollment(EnrollmentRequest enrollmentRequest);
    @Mapping(target = "course_name", source = "course.title")
    @Mapping(target = "courseCategory", source = "course.categories.name") // changed to 'course.categories.name'
    @Mapping(target = "student_name", source = "student.user.username")
    @Mapping(target ="university", source = "student.university")
    @Mapping(target = "high_school", source = "student.high_school")
    @Mapping(target = "is_blocked", source = "student.is_blocked")
    EnrollmentDetailsResponse toEnrollmentDetailsResponse(Enrollment enrollment);

    @Named("mapCourseToString")
    default String mapCourseToString(Course course) {
        return course.getTitle();
    }

    @Named("mapUserToString")
    default String mapUserToString(User user) {
        return user.getGiven_name();
    }
}
