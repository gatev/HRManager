package com.empiriu.service.impl;

import com.empiriu.model.BaseEntity;
import com.empiriu.repository.IBaseRepository;
import com.empiriu.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T extends BaseEntity> implements IBaseService<T> {

    @Autowired
    private IBaseRepository<T> baseRepository;

    @Override
    public T save(T t) {
        return baseRepository.save(t);
    }

    @Override
    public void delete(T t) {
        baseRepository.delete(t);
    }

    @Override
    public T findById(Long id) {
        return baseRepository.findById(id).get();
    }

    @Override
    public Iterable<T> findAll() {
        return baseRepository.findAll();
    }
}
