package com.tech.techno.service.module;

import com.tech.techno.model.Module;
import com.tech.techno.model.Role;
import com.tech.techno.repository.ModuleRepository;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService{

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    @LazyCollection(value = LazyCollectionOption.TRUE)
    public List<Module> userHasModules(List<Role> roles, Module module, int visibility) throws Exception {
        return moduleRepository.findAllByRolesIsInAndParentNotNullAndParentAndStatusVisibility(roles,module,visibility);
    }
}
