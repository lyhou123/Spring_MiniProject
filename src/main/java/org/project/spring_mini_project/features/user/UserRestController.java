package org.project.spring_mini_project.features.user;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.features.user.dto.UserDetailsResponse;
import org.project.spring_mini_project.features.user.dto.UserRequest;
import org.project.spring_mini_project.utils.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserRestController {
    private final UserService userService;

    @GetMapping
    public BaseResponse<List<UserDetailsResponse>>  getAllUsers(){
        return BaseResponse.<List<UserDetailsResponse>>ok()
                .setPayload(userService.findAllUsers());
    }

    @PostMapping
    public BaseResponse<UserDetailsResponse> createUser(@RequestBody UserRequest request){
        return BaseResponse.<UserDetailsResponse>createSuccess()
                .setPayload(userService.createUser(request));
    }
}
