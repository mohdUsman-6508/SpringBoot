package com.lineup.lineup.service;

import com.lineup.lineup.model.Category;
import com.lineup.lineup.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private final CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        if (categoryRepository.findByCategoryName(category.getCategoryName()).isPresent()) {
            return category;
        } else {
            return categoryRepository.save(category);
        }
    }

}
