package com.empiriu.repository;

import com.empiriu.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends IBaseRepository<Holiday> {
}
