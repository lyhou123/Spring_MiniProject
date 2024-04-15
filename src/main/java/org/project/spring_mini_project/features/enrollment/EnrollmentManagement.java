package org.project.spring_mini_project.features.enrollment;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentProgressRequest;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentProgressRespone;
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

    @GetMapping
    @Operation(summary = "Get Enrollment Sort by enrolledAt\n" +
            "Filter by code, course’s title, course’s category, student’s username, isCertified\n")

    public BaseResponse<List<EnrollmentRespone>> getEnrollments(

            @RequestParam(required = false, defaultValue = "enrolledAt") String sortBy,

            @RequestParam(required = false) String filterBy,

            @RequestParam(required = false) String filterValue,

            @RequestParam(required = false, defaultValue = "10") int perPage,

            @RequestParam(required = false, defaultValue = "1") int page){


        return BaseResponse.<List<EnrollmentRespone>>ok()

                .setPayload(enrollmentService.getEnrollment(sortBy,filterBy,filterValue, perPage, page));
    }

    @PutMapping("/{code}/progress")
    @Operation(summary = "Update Enrollment Progress")
    public BaseResponse<EnrollmentProgressRespone> updateEnrollment(@PathVariable Long code, @RequestBody EnrollmentProgressRequest enrollmentProgressRequest){

        return BaseResponse.<EnrollmentProgressRespone>ok()

                .setPayload(enrollmentService.updateEnrollment(code, enrollmentProgressRequest));
    }

    @GetMapping("/{code}/progress")
    @Operation(summary = "Get Progress of enroll learning")
    public BaseResponse<EnrollmentProgressRespone> getProgress(@PathVariable Long code){

        return BaseResponse.<EnrollmentProgressRespone>ok()

                .setPayload(enrollmentService.getProgress(code));
    }

    @PutMapping("/{code}")
    @Operation(summary = "Update disble enrollment")
    public BaseResponse<EnrollmentRespone> disableEnrollment(@PathVariable Long code){

        return BaseResponse.<EnrollmentRespone>ok()

                .setPayload(enrollmentService.disableEnrollment(code));
    }
}
