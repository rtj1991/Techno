package com.tech.techno.service.category;

import com.tech.techno.model.Category;
import com.tech.techno.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category categorySave(Category category) {
        categoryRepository.save(category);

        int id = category.getId();
        Category ref = categoryRepository.findById(id);
        category.setCategoryRefCode("sk" + id);
        categoryRepository.save(ref);

        return category;
    }
}
