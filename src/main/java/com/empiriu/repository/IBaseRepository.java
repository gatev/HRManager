package com.empiriu.repository;

import com.empiriu.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBaseRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
}
