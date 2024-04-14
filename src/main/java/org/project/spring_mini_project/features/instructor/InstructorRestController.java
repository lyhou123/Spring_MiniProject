package org.project.spring_mini_project.features.instructor;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.features.instructor.dto.InstructorCreateRequest;
import org.project.spring_mini_project.features.instructor.dto.InstructorResponse;
import org.project.spring_mini_project.utils.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/instructors")
@RequiredArgsConstructor
public class InstructorRestController {
    private final InstructorService instructorService;

    @PostMapping
    public BaseResponse<InstructorResponse> createInstructor(@RequestBody InstructorCreateRequest request){
        InstructorResponse instructorResponse = instructorService.createInstructor(request);
        return BaseResponse.<InstructorResponse>createSuccess()
                .setPayload(instructorResponse);
    }

    @GetMapping
    public BaseResponse<List<InstructorResponse>> findAllInstructors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return BaseResponse.<List<InstructorResponse>>createSuccess()
                .setPayload(instructorService.findAllInstructors(page, size));
    }

    @GetMapping("/{username}")
    public BaseResponse<InstructorResponse> findInstructorByUsername(@PathVariable String username){
        InstructorResponse instructorResponse = instructorService.findInstructorByUsername(username);
        return BaseResponse.<InstructorResponse>createSuccess()
                .setPayload(instructorResponse);
    }

    @PutMapping("/{username}")
    public BaseResponse<InstructorResponse> updateInstructor(@PathVariable String username, @RequestBody InstructorCreateRequest request){
        InstructorResponse instructorResponse = instructorService.updateInstructor(username, request);
        return BaseResponse.<InstructorResponse>createSuccess()
                .setPayload(instructorResponse);
    }
}
