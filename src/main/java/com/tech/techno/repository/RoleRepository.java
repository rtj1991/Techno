package com.tech.techno.repository;

import com.tech.techno.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {
    public List<Role> findAllByEnabled(boolean enable);
}
