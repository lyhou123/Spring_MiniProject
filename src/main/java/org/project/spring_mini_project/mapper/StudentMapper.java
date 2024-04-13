package org.project.spring_mini_project.mapper;

import org.mapstruct.Mapper;
import org.project.spring_mini_project.domain.Student;
import org.project.spring_mini_project.features.student.dto.StudentReqeust;
import org.project.spring_mini_project.features.student.dto.StudentRespone;

@Mapper(componentModel = "spring")

public interface StudentMapper {
    StudentRespone studentToStudentResponse(Student student);
    Student studentRequestToStudent(StudentReqeust studentReqeust);
}
