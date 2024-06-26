package org.project.spring_mini_project.features.student;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.domain.Role;
import org.project.spring_mini_project.domain.Student;
import org.project.spring_mini_project.features.role.RoleRepository;
import org.project.spring_mini_project.features.student.dto.StudentCreateRequest;
import org.project.spring_mini_project.features.student.dto.StudentRespone;
import org.project.spring_mini_project.features.student.dto.StudentUpdateRequest;
import org.project.spring_mini_project.features.user.UserRepository;
import org.project.spring_mini_project.mapper.StudentMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final StudentMapper studentMapper;


    @Override
    public List<StudentRespone> getAllStudents(int page, int size) {

        if(page<=0){
            throw new IllegalArgumentException("Page number must be greater than 0");
        }

        int pageSize=page-1;

        Pageable pageable = PageRequest.of(pageSize, size);
        Page<Student> studentPage = studentRepository.findAll(pageable);
       return studentPage.stream().
               map(studentMapper::mapStudentToStudentResponse).toList();
    }

    @Override
    public StudentRespone createStudent(StudentCreateRequest studentCreateRequest) {
        var owner = userRepository.findById(studentCreateRequest.user_id())
                .orElseThrow(
                        () -> new NoSuchElementException(
                                "User ID = " + studentCreateRequest.user_id() + " is not a valid user"
                        )
                );

        // Fetch the "STUDENT" role
        Role role = roleRepository.findByName("STUDENT")
                .orElseThrow(() -> new NoSuchElementException("Role not found with name: STUDENT"));
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        // Set the "STUDENT" role to the user
        owner.setRoles(roles);
        userRepository.save(owner);

        var student = studentMapper.mapStudentRequestToStudent(studentCreateRequest);
        student.setUser(owner);

        var saveStudent = studentRepository.save(student);

        return studentMapper.mapStudentToStudentResponse(saveStudent);
    }


    @Override
    public StudentRespone getStudentByUsername(String username) {

        var student=studentRepository.findByUserUsername(username)
                .orElseThrow(
                        ()->new NoSuchElementException(
                                "Student with username = "+username+" not found"
                        )
                );
        return studentMapper.mapStudentToStudentResponse(student);
    }

    @Override
    public StudentRespone updateStudentByUsername(String username, StudentUpdateRequest studentUpdateRequest) {

        var findStudent=studentRepository.findByUserUsername(username)
                .orElseThrow(
                        ()->new NoSuchElementException(
                                "Student with username = "+username+" not found"
                        )
                );
        studentMapper.updateStudentFromRequest(findStudent,studentUpdateRequest);
return studentMapper.mapStudentToStudentResponse(studentRepository.save(findStudent));

//        var updateStudent=studentMapper.mapStudentRequestToStudent(studentUpdateRequest);
//        updateStudent.setId(findStudent.getId());
//
//        return studentMapper.mapStudentToStudentResponse(studentRepository.save(updateStudent));
    }
}
