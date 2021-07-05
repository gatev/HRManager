package com.empiriu.repository;

import com.empiriu.model.Role;
import com.empiriu.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends IBaseRepository<Role> {
    Optional<Role> findByName(RoleName roleName);
}
