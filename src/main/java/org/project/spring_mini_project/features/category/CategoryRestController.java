package org.project.spring_mini_project.features.category;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.features.category.dto.CategoryRequest;
import org.project.spring_mini_project.features.category.dto.CategoryResponse;
import org.project.spring_mini_project.utils.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryRestController {
    private final CategoryService categoryService;

    @PostMapping
    public BaseResponse<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        return BaseResponse.<CategoryResponse>createSuccess()
                .setPayload(categoryService.createCategory(categoryRequest));
    }

    @GetMapping
    public BaseResponse<List<CategoryResponse>> findAllCategories(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return BaseResponse.<List<CategoryResponse>>ok()
                .setPayload(categoryService.findAllCategories(page, size));
    }
    @GetMapping("/parent")
    public BaseResponse<List<CategoryResponse>> findParentCategories() {
        return BaseResponse.<List<CategoryResponse>>ok()
                .setPayload(categoryService.findParentCategories());
    }
    @PutMapping("/{alias}")
    public BaseResponse<CategoryResponse> updateCategory(@PathVariable String alias, @RequestBody CategoryRequest categoryRequest) {
        return BaseResponse.<CategoryResponse>createSuccess()
                .setPayload(categoryService.updateCategory(alias, categoryRequest));
    }
    @GetMapping("/{alias}")
    public BaseResponse<CategoryResponse> findCategoryByAlias(@PathVariable String alias) {
        return BaseResponse.<CategoryResponse>createSuccess()
                .setPayload(categoryService.findCategoryByAlias(alias));
    }
    @PatchMapping("/{alias}/disable")
    public BaseResponse<CategoryResponse> disableCategory(@PathVariable String alias) {
        return BaseResponse.<CategoryResponse>createSuccess()
                .setPayload(categoryService.disableCategory(alias));
    }
    @PatchMapping("/{alias}/enable")
    public BaseResponse<CategoryResponse> enableCategory(@PathVariable String alias) {
        return BaseResponse.<CategoryResponse>createSuccess()
                .setPayload(categoryService.enableCategory(alias));
    }
}
