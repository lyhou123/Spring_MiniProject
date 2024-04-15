package org.project.spring_mini_project.features.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.features.user.dto.UserDetailsResponse;
import org.project.spring_mini_project.features.user.dto.UserRequest;
import org.project.spring_mini_project.utils.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserRestController {
    private final UserService userService;

    @GetMapping
    public BaseResponse<List<UserDetailsResponse>> getAllUsers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String national_id_card,
            @RequestParam(required = false) String phone_number,
            @RequestParam(required = false) String given_name,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) Set<String> roleNames) {
        return BaseResponse.<List<UserDetailsResponse>>ok()
                .setPayload(userService.findAllUsers(username, email, national_id_card, phone_number, given_name, gender, roleNames));
    }

    @PostMapping
    public BaseResponse<UserDetailsResponse> createUser(@Valid @RequestBody UserRequest request){
        return BaseResponse.<UserDetailsResponse>createSuccess()
                .setPayload(userService.createUser(request));
    }

    @GetMapping("/{username}")
    public BaseResponse<UserDetailsResponse> getUserByUsername(@PathVariable String username){
        return BaseResponse.<UserDetailsResponse>ok()
                .setPayload(userService.findUserByUsername(username));
    }

    @PatchMapping("/{username}/disable")
    public BaseResponse<UserDetailsResponse> disableUser(@PathVariable String username){
        return BaseResponse.<UserDetailsResponse>ok()
                .setPayload(userService.disableUser(username));
    }

    @PatchMapping("/{username}/enable")
    public BaseResponse<UserDetailsResponse> enableUser(@PathVariable String username){
        return BaseResponse.<UserDetailsResponse>ok()
                .setPayload(userService.enableUser(username));
    }

    @DeleteMapping("/{username}")
    public BaseResponse<Void> deleteUser(@PathVariable String username){
        userService.deleteUser(username);
        return BaseResponse.<Void>ok();
    }
}
