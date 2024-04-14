package org.project.spring_mini_project.features.category;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.features.category.dto.CategoryRespone;
import org.project.spring_mini_project.mapper.CategoryMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catestories")
@RequiredArgsConstructor
public class CategoryRestController {
    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    @GetMapping
    public List<CategoryRespone> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
