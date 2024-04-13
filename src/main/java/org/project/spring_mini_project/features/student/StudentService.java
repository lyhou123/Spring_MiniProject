package org.project.spring_mini_project.features.student;

import org.project.spring_mini_project.features.student.dto.StudentRespone;

import java.util.List;

public interface StudentService {
    List<StudentRespone> getAllStudents();
}
