package com.tech.techno.repository;

import com.tech.techno.model.Module;
import com.tech.techno.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends PagingAndSortingRepository<Module,Integer> {
    List<Module> findAllByRolesIsInAndParentNotNullAndParentAndStatusVisibility(List<Role> roles, Module module, int visibility);
}
