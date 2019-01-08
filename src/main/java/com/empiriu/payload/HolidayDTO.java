package com.empiriu.payload;

import javax.validation.constraints.*;

public class HolidayDTO {
    @NotBlank
    private String startDate;

    @NotBlank
    private String endDate;

    private String isPaidHoliday;

    @NotNull
    private Long employeeId;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getIsPaidHoliday() {
        return isPaidHoliday;
    }

    public void setIsPaidHoliday(String isPaidHoliday) {
        this.isPaidHoliday = isPaidHoliday;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
