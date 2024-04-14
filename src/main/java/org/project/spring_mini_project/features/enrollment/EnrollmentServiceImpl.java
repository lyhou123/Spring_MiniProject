package org.project.spring_mini_project.features.enrollment;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.features.course.CourseRepository;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentRespone;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentProgressRequest;
import org.project.spring_mini_project.features.student.StudentRepository;
import org.project.spring_mini_project.mapper.EnrollmentMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    private final EnrollmentMapper enrollmentMapper;

    @Override
    public EnrollmentRespone createEnrollment(EnrollmentProgressRequest enrollmentProgressRequest) {

        var student=studentRepository.findById(enrollmentProgressRequest.student_id())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Student ID = " + enrollmentProgressRequest.student_id() + " is not a valid student")
                );

        var course=courseRepository.findById(enrollmentProgressRequest.course_id())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Course ID = " + enrollmentProgressRequest.course_id() + " is not a valid course")
                );

        var enrollment=enrollmentMapper.mapEnrollmentRequestToEnrollment(enrollmentProgressRequest);

        enrollment.setStudent(student);

        enrollment.getCourse_id().add(course);

        var saveEnrollment=enrollmentRepository.save(enrollment);

        return enrollmentMapper.maptoEnrollMentRespone(saveEnrollment);
    }

    @Override
    public EnrollmentRespone getEnrollment(Long id) {
        var enrollment=enrollmentRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Enrollment ID = " + id + " is not a valid enrollment")
                );

        return enrollmentMapper.maptoEnrollMentRespone(enrollment);
    }
}
