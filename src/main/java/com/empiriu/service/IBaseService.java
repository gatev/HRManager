package com.empiriu.service;

import com.empiriu.model.BaseEntity;

public interface IBaseService<T extends BaseEntity> {
    T save(T t);
    void delete(T t);
    T findById(Long id);
    Iterable<T> findAll();
}
