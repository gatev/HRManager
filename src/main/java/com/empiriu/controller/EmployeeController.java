package com.empiriu.controller;

import com.empiriu.model.Employee;
import com.empiriu.service.IEmpleyeeService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api")
public class EmployeeController {

    private static Log logger = LogFactory.getLog(EmployeeController.class);

    private IEmpleyeeService employeeService;

    public EmployeeController(IEmpleyeeService empleyeeService) {
        this.employeeService = empleyeeService;
    }

    @GetMapping(value = "employees")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Iterable<Employee>> getAllEmployees() {
        logger.debug("Get all employees mapping was called");
        return new ResponseEntity<Iterable<Employee>>(employeeService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = "employees/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") Long employeeId) throws EntityNotFoundException {
        logger.info("Get employee by ID mapping was called: ID: " + employeeId);
        try {
//            return new ResponseEntity<>(employeeRepository.findById(employeeId), HttpStatus.OK);
            Employee emp = employeeService.findById(employeeId);
            return new ResponseEntity<>(emp, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            logger.error("Not found user with id: " + employeeId);
            throw nsee;
        }
    }

//    @GetMapping(value = "/{employeeEmail}")
//    public ResponseEntity<List<Employee>> getEmployeebyName(@PathVariable("employeeEmail") String employeeEmail) throws EntityNotFoundException {
//        logger.info("Get all employees mapping was called");
//        try {
//            return new ResponseEntity<>(employeeService.findByEmail(employeeEmail), HttpStatus.OK);
//        } catch (NoSuchElementException nsee) {
//            logger.error("Not found user with id: " + employeeEmail);
//            throw nsee;
//        }
//    }
//
//

    @GetMapping
    public String hello() {
        return "Hello World";
    }
}
