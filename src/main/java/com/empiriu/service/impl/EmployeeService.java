package com.empiriu.service.impl;

import com.empiriu.model.Employee;
import com.empiriu.repository.EmployeeRepository;
import com.empiriu.service.IEmpleyeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService extends BaseService<Employee> implements IEmpleyeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Boolean existsByEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public List<Employee> findByIdIn(List<Long> userIds) {
        return employeeRepository.findByIdIn(userIds);
    }
}
