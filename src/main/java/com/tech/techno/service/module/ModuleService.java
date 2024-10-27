package com.tech.techno.service.module;

import com.tech.techno.model.Module;
import com.tech.techno.model.Role;

import java.util.List;

public interface ModuleService {

    List<Module>userHasModules(List<Role> roles,Module module,int visibility)throws Exception;

}
