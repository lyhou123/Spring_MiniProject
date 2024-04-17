package org.project.spring_mini_project.features.enrollment;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.domain.Course;
import org.project.spring_mini_project.domain.Enrollment;
import org.project.spring_mini_project.domain.Student;
import org.project.spring_mini_project.features.course.CourseRepository;
import org.project.spring_mini_project.features.enrollment.dto.*;
import org.project.spring_mini_project.features.student.StudentRepository;
import org.project.spring_mini_project.mapper.EnrollmentMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;


    @Override
    public EnrollmentResponse enrollStudent(EnrollmentRequest enrollmentRequest) {

        var course = courseRepository.findById(enrollmentRequest.course_id())
                .orElseThrow(() -> new NoSuchElementException( "Course not found"));
       var student= studentRepository.findById(enrollmentRequest.student_id())
                .orElseThrow(() -> new NoSuchElementException("Student not found"));
        var enrollment = enrollmentMapper.toEnrollment(enrollmentRequest);

        // Set the Course object to the Enrollment object
        enrollment.setEnrolledAt(LocalDateTime.now());
        enrollment.setIs_deleted(false);
        enrollment.setIs_certified(false);
        enrollment.setProgress(0);
        enrollment.setCourse(course);
        enrollment.setStudent(student);
        enrollment = enrollmentRepository.save(enrollment);

        return enrollmentMapper.toEnrollmentResponse(enrollment);
    }

    @Override
    public List<EnrollmentResponse> getEnrollments(int page, int size, String code, String courseTitle, String courseCategory, String studentUsername, Boolean is_certified) {

        //starting fix page index size because page index start from index 0
        int fixedPage = page - 1;

//        String isCertifiedString = (is_certified == null) ? "true" : is_certified.toString();

        String isCertifiedString = (is_certified != null) ? is_certified.toString() : null;

        Specification<Enrollment> spec = Specification
                .where(new EnrollmentSpecification("code", code))
                .and(new EnrollmentSpecification("courseTitle", courseTitle))
                .and(new EnrollmentSpecification("courseCategoriesName", courseCategory))
                .and(new EnrollmentSpecification("studentUserUsername", studentUsername))
                .and(new EnrollmentSpecification("isCertified", isCertifiedString)
                );

        Pageable pageable = PageRequest.of(fixedPage, size, Sort.by(Sort.Direction.ASC, "enrolledAt"));
        Page<Enrollment> enrollmentsPage = enrollmentRepository.findAll(spec, pageable);
        List<Enrollment> enrollments = enrollmentsPage.getContent();

        return enrollments.stream()
                .map(enrollmentMapper::toEnrollmentResponse)
                .collect(Collectors.toList());
    }


    @Override
    public EnrollmentResponse getEnrollmentByCode(String code) {

        Enrollment enrollment = enrollmentRepository.findByCode(code);
        if (enrollment == null) {
            throw new NoSuchElementException("Enrollment not found");
        }
        return enrollmentMapper.toEnrollmentResponse(enrollment);
    }

    @Override
    public EnrollmentProgressResponse updateEnrollmentProgress(String code, EnrollmentProgressRequest enrollmentProgressRequest) {
        Enrollment enrollment = enrollmentRepository.findByCode(code);
        if (enrollment == null) {
            throw new NoSuchElementException("Enrollment not found");
        }
        //validation ot value progress
        int progressValue = enrollmentProgressRequest.progress();

        if(progressValue >= 0 && progressValue <= 100) {
            if(progressValue == 100) {
                enrollment.setIs_certified(true);
            } else {
                enrollment.setIs_certified(false);
            }
        } else {
            throw new IllegalArgumentException("Progress value must be between 0 and 100");
        }
        enrollment.setProgress(enrollmentProgressRequest.progress()

        );
        enrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toEnrollmentProgressResponse(enrollment);
    }

    @Override
    public EnrollmentProgressResponse getEnrollmentProgress(String code) {
        Enrollment enrollment = enrollmentRepository.findByCode(code);
        if (enrollment == null) {
            throw new NoSuchElementException("Enrollment not found");
        }
        return enrollmentMapper.toEnrollmentProgressResponse(enrollment);
    }

    @Override
    public EnrollmentResponse certifyEnrollment(String code) {
        Enrollment enrollment = enrollmentRepository.findByCode(code);
        if (enrollment == null) {
            throw new NoSuchElementException("Enrollment not found");
        }
        enrollment.setProgress(100);
        enrollment.setIs_certified(true);
        enrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toEnrollmentResponse(enrollment);
    }

    @Override
    public EnrollmentResponse discardEnrollment(String code) {
        Enrollment enrollment = enrollmentRepository.findByCode(code);
        enrollment.setIs_deleted(true);
        enrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toEnrollmentResponse(enrollment);
    }

    @Override
    public EnrollmentDetailsResponse getEnrollmentDetails(String code) {
        var enrollment = enrollmentRepository.findByCode(code);
        if (enrollment == null) {
            throw new NoSuchElementException("Enrollment not found");
        }
        return enrollmentMapper.toEnrollmentDetailsResponse(enrollment);
    }


}