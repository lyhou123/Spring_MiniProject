package org.project.spring_mini_project.features.enrollment;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentProgressRequest;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentRespone;
import org.project.spring_mini_project.utils.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentManagement {

    private final EnrollmentService enrollmentService;

    @PostMapping
    @Operation(summary = "Create Enrollment")
    public BaseResponse<EnrollmentRespone> getAllEnrollments(@RequestBody EnrollmentProgressRequest enrollmentProgressRequest){

        return BaseResponse.<EnrollmentRespone>ok()

                .setPayload(enrollmentService.createEnrollment(enrollmentProgressRequest));
    }

    @GetMapping("/{code}")
    @Operation(summary = "Get Enrollment by code")
    public BaseResponse<EnrollmentRespone> getEnrollment(@PathVariable Long code){
        return BaseResponse.<EnrollmentRespone>ok()
                .setPayload(enrollmentService.getEnrollment(code));
    }
}
