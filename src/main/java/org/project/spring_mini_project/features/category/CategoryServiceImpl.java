package org.project.spring_mini_project.features.category;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.features.category.dto.CategoryRespone;
import org.project.spring_mini_project.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;
    @Override
    public List<CategoryRespone> getAllCategories() {

        var categories = categoryRepository.findAll();

        return categories.stream().
                map(categoryMapper::toCategoryResponse).
                toList();
    }
}
