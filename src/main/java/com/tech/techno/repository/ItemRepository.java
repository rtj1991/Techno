package com.tech.techno.repository;

import com.tech.techno.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item, Integer> {
    Page<Item> findAllByStatus(int status, Pageable pageable);

    Item findByItemcode(Integer id);


}
