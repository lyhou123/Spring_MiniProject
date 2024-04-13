package org.project.spring_mini_project.features.student;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.features.student.dto.StudentRespone;
import org.project.spring_mini_project.utils.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/students")
@RequiredArgsConstructor
public class StudentRestController {
    private final StudentService studentService;

    @GetMapping
    public BaseResponse<List<StudentRespone>> getAllStudents(){
        return BaseResponse.<List<StudentRespone>>ok()
                .setPayload(studentService.getAllStudents());
    }
}
