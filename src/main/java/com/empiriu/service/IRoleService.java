package com.empiriu.service;

import com.empiriu.model.Role;
import com.empiriu.model.RoleName;

import java.util.Optional;

public interface IRoleService extends IBaseService<Role>{
    Optional<Role> findByName(RoleName roleName);

}
