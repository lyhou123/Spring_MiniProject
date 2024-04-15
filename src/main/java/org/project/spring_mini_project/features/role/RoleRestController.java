package org.project.spring_mini_project.features.role;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.features.role.dto.RoleResponse;
import org.project.spring_mini_project.utils.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/roles")
public class RoleRestController {

    private final RoleService roleService;

    @GetMapping
    public BaseResponse<List<RoleResponse>> getAllRoles() {
        return BaseResponse.<List<RoleResponse>>ok()
                .setPayload(roleService.getAllRoles());
    }

    @GetMapping("/name")
    public BaseResponse<RoleResponse> getRoleByName(String name) {
        return BaseResponse.<RoleResponse>ok()
                .setPayload(roleService.getRoleByName(name));
    }
}
