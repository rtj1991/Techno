package com.tech.techno.repository;

import com.tech.techno.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category,Integer> {
    List<Category> findAllByDeleted(int deleted);

    Category findById(int id);

}
