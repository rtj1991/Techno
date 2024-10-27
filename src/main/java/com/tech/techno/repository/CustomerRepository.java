package com.tech.techno.repository;

import com.tech.techno.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Integer> {

    public Page<Customer> findAllByStatus(int status, Pageable pageable);

    public Customer findById(Integer id);
}
