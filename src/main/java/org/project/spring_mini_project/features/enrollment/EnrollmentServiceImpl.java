package org.project.spring_mini_project.features.enrollment;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.domain.Enrollment;
import org.project.spring_mini_project.features.course.CourseRepository;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentProgressRespone;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentRespone;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentProgressRequest;
import org.project.spring_mini_project.features.student.StudentRepository;
import org.project.spring_mini_project.mapper.EnrollmentMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

//        enrollment.setCourse_id(Set.of(course));

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

    @Override
    public List<EnrollmentRespone> getEnrollment(String sortBy, String filterBy, String filterValue, int perPage, int page) {
        return null;
    }

    @Override
    public EnrollmentProgressRespone updateEnrollment(Long id, EnrollmentProgressRequest enrollmentProgressRequest) {
        var enrollment=enrollmentRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Enrollment ID = " + id + " is not a valid enrollment")
                );
        var newEnrollment=enrollmentMapper.mapEnrollmentRequestToEnrollment(enrollmentProgressRequest);

//        newEnrollment.getCourse_id().addAll(enrollment.getCourse_id());

        var enrollMent= enrollmentRepository.save(newEnrollment);

        return enrollmentMapper.maptoEnrollMentProgressRespone(enrollMent);
    }

    @Override
    public EnrollmentProgressRespone getProgress(Long id) {
        var enrollment=enrollmentRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Enrollment ID = " + id + " is not a valid enrollment")
                );
        return enrollmentMapper.maptoEnrollMentProgressRespone(enrollment);
    }

    @Override
    public EnrollmentRespone disableEnrollment(Long id) {
        int affectedRow = enrollmentRepository.updateBlockedStatusById(id, true);
        if (affectedRow > 0) {
            return enrollmentMapper.maptoEnrollMentRespone(
                    enrollmentRepository.findById(id)
                            .orElse(null));
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User with id = " + id + " doesn't exist ! "
            );
        }
    }

    @Override
    public List<EnrollmentRespone> getAllEnrollments() {
        var enroll= enrollmentRepository.findAll();
        return enroll.stream().map(enrollmentMapper::maptoEnrollMentRespone).collect(Collectors.toList());
    }


}
