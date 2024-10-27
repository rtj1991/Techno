package com.tech.techno.repository;

import com.tech.techno.model.Category;
import com.tech.techno.model.SubCategory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface SubCategoryRepository extends PagingAndSortingRepository<SubCategory,Integer> {
     List<SubCategory> findByCategory(Category category);

     SubCategory findById(int id);


}
