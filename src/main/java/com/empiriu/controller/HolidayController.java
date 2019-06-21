package com.empiriu.controller;

import com.empiriu.model.Employee;
import com.empiriu.model.Holiday;
import com.empiriu.payload.HolidayDTO;
import com.empiriu.repository.HolidayRepository;
import com.empiriu.repository.EmployeeRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/holiday")
public class HolidayController {
    private static Log logger = LogFactory.getLog(HolidayController.class);

    private EmployeeRepository employeeRepository;
    private HolidayRepository dayOffRequestRepository;

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    public void setDayOffRequestRepository(HolidayRepository dayOffRequestRepository) {
        this.dayOffRequestRepository = dayOffRequestRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/request")
    public ResponseEntity<Optional> createHolidayRequest(@Valid @RequestBody HolidayDTO holidayDTO) {
        logger.info("POST ask for holiday is called");

        LocalDate startDate = LocalDate.parse(holidayDTO.getStartDate());
        LocalDate endDate = LocalDate.parse(holidayDTO.getEndDate());
        int numberDays = Period.between(startDate, endDate).getDays() + 1;

        Optional<Employee> employee = employeeRepository.findById(holidayDTO.getEmployeeId());

        if (employee.isPresent()) {
            boolean isDayOffPaid = true;
            if (holidayDTO.getIsPaidHoliday().equals("false")) {
                isDayOffPaid = false;
            }

            Holiday holiday = new Holiday(startDate, endDate, numberDays, employee.get(), isDayOffPaid);
            dayOffRequestRepository.save(holiday);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/all")
    public ResponseEntity<List<Holiday>> getAllRequest() {
        logger.info("get all day off requests was called");
        List<Holiday> list = dayOffRequestRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/request/{requestId}")
    public ResponseEntity<Optional> getAllRequest(@PathVariable("requestId") Long requestId) {
        logger.info("get day off requests was called");

        Optional<Holiday> dayOddRequest = dayOffRequestRepository.findById(requestId);
        return new ResponseEntity<>(dayOddRequest, HttpStatus.OK);
    }
}
