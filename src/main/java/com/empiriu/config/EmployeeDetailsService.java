package com.empiriu.config;

import com.empiriu.model.Employee;
import com.empiriu.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EmployeeDetailsService implements UserDetailsService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Employee employee = employeeRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> email : " + email));

        return EmployeePrincipal.build(employee); // UserPrinciple implements UserDetails
    }
}
