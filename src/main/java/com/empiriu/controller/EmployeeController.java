package com.empiriu.controller;

import com.empiriu.model.Holiday;
import com.empiriu.model.Employee;
import com.empiriu.payload.HolidayDTO;
import com.empiriu.repository.DayOffRequestRepository;
import com.empiriu.repository.EmployeeRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    private static Log logger = LogFactory.getLog(EmployeeController.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/all")
//    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        logger.debug("Get all employees mapping was called");
        return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/{employeeId}")
    public ResponseEntity<Optional> getEmployeeById(@PathVariable("employeeId") Long employeeId) throws EntityNotFoundException {
        logger.info("Get employee by ID mapping was called: ID: " + employeeId);
        try {
//            return new ResponseEntity<>(employeeRepository.findById(employeeId), HttpStatus.OK);
            return new ResponseEntity<>(employeeRepository.findById(employeeId), HttpStatus.OK);
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
