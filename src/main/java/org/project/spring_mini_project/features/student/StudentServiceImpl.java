package org.project.spring_mini_project.features.student;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.features.student.dto.StudentRespone;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    @Override
    public List<StudentRespone> getAllStudents() {
        var getAllStudent=studentRepository.findAll();
        return null;
    }
}
