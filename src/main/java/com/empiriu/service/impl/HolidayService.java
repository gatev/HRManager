package com.empiriu.service.impl;

import com.empiriu.model.Employee;
import com.empiriu.model.Holiday;
import com.empiriu.repository.HolidayRepository;
import com.empiriu.service.IEmpleyeeService;
import com.empiriu.service.IHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HolidayService extends BaseService<Holiday> implements IHolidayService {

    @Autowired
    private HolidayRepository holidayRepository;
}
