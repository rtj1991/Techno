package com.tech.techno.repository;

import com.tech.techno.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

    Page<Employee> findAllByStatus(int status, Pageable pageable);

    Employee findById(int id);
}
