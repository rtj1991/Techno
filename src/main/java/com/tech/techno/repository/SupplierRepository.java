package com.tech.techno.repository;

import com.tech.techno.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends PagingAndSortingRepository<Supplier,Integer> {
    Page<Supplier> findAllByStatus(int status, Pageable pageable);

    List<Supplier> findAllByStatus(int status);

    Supplier findById(Integer id);
}
