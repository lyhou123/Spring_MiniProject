package org.project.spring_mini_project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.project.spring_mini_project.domain.Categories;
import org.project.spring_mini_project.domain.Course;
import org.project.spring_mini_project.domain.Instructor;
import org.project.spring_mini_project.features.category.dto.CategoryResponse;
import org.project.spring_mini_project.features.course.dto.CourseCreateRequest;
import org.project.spring_mini_project.features.course.dto.CourseDetailsResponse;
import org.project.spring_mini_project.features.course.dto.CourseResponse;
import org.project.spring_mini_project.features.instructor.dto.InstructorResponse;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseResponse toCourseResponse(Course course);

    Course toCourse(CourseCreateRequest courseCreateRequest);

    @Mapping(source = "instructor", target = "instructor", qualifiedByName = "toInstructorResponse")
    @Mapping(source = "categories", target = "categories", qualifiedByName = "toCategoryResponse")
    CourseDetailsResponse toCourseDetailsResponse(Course course);

    @Named("toInstructorResponse")
    default InstructorResponse toInstructorResponse(Instructor instructor) {
        if (instructor == null) {
            System.out.println("Instructor is null");
            return null;
        }
        System.out.println("Instructor is not null");
        return InstructorResponse.builder()
                .id(instructor.getId())
                .given_name(instructor.getGiven_name())
                .build();
    }

    @Named("toCategoryResponse")
    default CategoryResponse toCategoryResponse(Categories category) {
        if (category == null) {
            return null;
        }
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}