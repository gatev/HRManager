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
public class CustomEmployeeDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsernameOrEmail(userNameOrEmail,userNameOrEmail).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username or email : " + userNameOrEmail)
        );
        return EmployeePrincipal.create(employee);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return EmployeePrincipal.create(employee);
    }
}
