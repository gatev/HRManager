package com.empiriu.service.impl;

import com.empiriu.model.Role;
import com.empiriu.model.RoleName;
import com.empiriu.repository.RoleRepository;
import com.empiriu.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService extends BaseService<Role> implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }
}
