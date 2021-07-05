package com.empiriu.service;

import com.empiriu.model.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmpleyeeService extends IBaseService<Employee>{
    Boolean existsByEmail(String email);
    Optional<Employee> findByEmail(String email);
    List<Employee> findByIdIn(List<Long> userIds);


}
