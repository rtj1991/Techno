package com.tech.techno.repository;

import com.tech.techno.model.Item;
import com.tech.techno.model.PriceList;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceListRepository extends PagingAndSortingRepository<PriceList,Integer>{

    PriceList findById(int id);

    PriceList findById(Integer pitem);

    PriceList findByItem(Item item);
}
