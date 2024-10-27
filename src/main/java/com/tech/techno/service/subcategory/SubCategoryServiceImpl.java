package com.tech.techno.service.subcategory;

import com.tech.techno.model.SubCategory;
import com.tech.techno.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubCategoryServiceImpl implements SubCategoryService {
    @Autowired
    private SubCategoryRepository subCategoryRepository;


    @Override
    public SubCategory savesubCategory(SubCategory subCategory) {
        subCategoryRepository.save(subCategory);

        int byId = subCategory.getId();
        SubCategory subCategory1 = subCategoryRepository.findById(byId);
        subCategory.setSubCategoryRefCode("sksu" + byId);
        subCategoryRepository.save(subCategory1);

        return subCategory;
    }
}
