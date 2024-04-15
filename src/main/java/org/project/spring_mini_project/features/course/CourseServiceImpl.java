package org.project.spring_mini_project.features.course;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.domain.Course;
import org.project.spring_mini_project.domain.Instructor;
import org.project.spring_mini_project.features.course.dto.*;
import org.project.spring_mini_project.mapper.CourseMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Transactional
    @Override
    public CourseResponse createCourse(CourseCreateRequest courseCreateRequest) {
        Course course = courseMapper.toCourse(courseCreateRequest);
        course = courseRepository.save(course);
        return courseMapper.toCourseResponse(course);
    }
    @Transactional(readOnly = true)
    @Override
    public List<CourseResponse> findAllCourses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> coursePage = courseRepository.findAll(pageable);
        return coursePage.stream()
                .map(courseMapper::toCourseResponse)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    @Override
    public CourseDetailsResponse findCourseDetails(String alias) {
        Course course = courseRepository.findCourseByAlias(alias);
        Instructor instructor = course.getInstructor(); // This will fetch the Instructor
        return courseMapper.toCourseDetailsResponse(course);
    }

    @Transactional
    @Override
    public CourseResponse updateCourse(String alias, CourseUpdateRequest courseUpdateRequest) {
        Course course = courseRepository.findCourseByAlias(alias);
        // Update course fields here
        course = courseRepository.save(course);
        return courseMapper.toCourseResponse(course);
    }

    @Transactional
    @Override
    public CourseResponse updateCourseCategories(String alias, CourseCategoryRequest courseCategoryRequest) {
        Course course = courseRepository.findCourseByAlias(alias);
        // Update course categories here
        course = courseRepository.save(course);
        return courseMapper.toCourseResponse(course);
    }

    @Transactional
    @Override
    public CourseResponse disableCourse(String alias) {
//        Course course = courseRepository.findByAlias(alias);
//        // Disable course here
//        course = courseRepository.save(course);
//        return courseMapper.toCourseResponse(course);
        return null;
    }
}