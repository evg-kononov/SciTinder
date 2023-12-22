package com.example.scopusservice.service.converter;

import com.example.scopusservice.model.dto.CategoryDTO;
import com.example.scopusservice.model.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    public Category fromCategoryDTOToCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        return category;
    }

    public CategoryDTO fromCategoryToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }
}
