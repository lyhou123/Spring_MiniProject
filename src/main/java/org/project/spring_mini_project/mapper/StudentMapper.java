package org.project.spring_mini_project.mapper;

import org.mapstruct.*;
import org.project.spring_mini_project.domain.Student;
import org.project.spring_mini_project.domain.User;
import org.project.spring_mini_project.features.student.dto.StudentCreateRequest;
import org.project.spring_mini_project.features.student.dto.StudentRespone;
import org.project.spring_mini_project.features.student.dto.StudentUpdateRequest;
import org.project.spring_mini_project.features.user.UserRepository;

@Mapper(componentModel = "spring")
public interface StudentMapper {

   @Mapping(source = "user", target = "user_id", qualifiedByName = "mapUserToUserId")// changed to 'course.categories.name'
   @Mapping(target = "username", source = "user", qualifiedByName = "mapUserToUsername")
    StudentRespone mapStudentToStudentResponse(Student student);

    Student mapStudentRequestToStudent(StudentCreateRequest studentCreateRequest);


    Student mapStudentRequestToStudent(StudentUpdateRequest studentUpdateRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudentFromRequest(@MappingTarget Student student, StudentUpdateRequest studentUpdateRequest);

  @Named("mapUserToUserId")
    default Long mapUserToUserId(User user) {
        return user != null ? user.getId() : null;
    }
    @Named("mapUserToUsername")
    default String  mapUserToUsername(User user) {
        return user != null ? user.getUsername() : null;
    }

}
