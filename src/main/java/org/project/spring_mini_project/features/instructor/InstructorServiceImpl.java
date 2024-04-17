package org.project.spring_mini_project.features.instructor;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.domain.Instructor;
import org.project.spring_mini_project.features.instructor.dto.InstructorCreateRequest;
import org.project.spring_mini_project.features.instructor.dto.InstructorResponse;
import org.project.spring_mini_project.features.instructor.dto.InstructorUpdateRequest;
import org.project.spring_mini_project.features.user.UserRepository;
import org.project.spring_mini_project.mapper.InstructorMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;
    private final UserRepository userRepository;

    @Override
    public List<InstructorResponse> findAllInstructors(int page, int size) {

        if(page<=0){
            throw new IllegalArgumentException("Page number must be greater than 0");
        }

        int pageSize = page - 1;

        Pageable pageable = PageRequest.of(pageSize, size);
        Page<Instructor> instructorPage = instructorRepository.findAll(pageable);
        return instructorPage.stream()
                .map(instructorMapper::instructorToInstructorResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InstructorResponse createInstructor(InstructorCreateRequest instructorCreateRequest) {
        Instructor instructor = instructorMapper.instructorCreateRequestToInstructor(instructorCreateRequest, userRepository);
        instructor.setIs_deleted(false);
        instructor.setIs_deleted(false);
        Instructor savedInstructor = instructorRepository.save(instructor);
        return instructorMapper.instructorToInstructorResponse(savedInstructor);
    }

    @Override
    public InstructorResponse updateInstructor(String username, InstructorUpdateRequest instructorCreateRequest) {

        Instructor instructor = instructorRepository.findInstructorByUser_Username(username)
                .orElseThrow(() -> new RuntimeException("Instructor not found with username: " + username));

        instructorMapper.updateInstructorFromRequest(instructor, instructorCreateRequest);

        Instructor updatedInstructor = instructorRepository.save(instructor);

        return instructorMapper.instructorToInstructorResponse(updatedInstructor);
    }

    @Override
    public InstructorResponse findInstructorByUsername(String username) {
        Instructor instructor = instructorRepository.findInstructorByUser_Username(username)
                .orElseThrow(() -> new RuntimeException("Instructor not found with username: " + username));
        return instructorMapper.instructorToInstructorResponse(instructor);
    }
}