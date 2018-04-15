package com.mt.demo.springsecurity.manager;

import com.alibaba.fastjson.JSONObject;
import com.mt.demo.springsecurity.repository.BaseRepository;
import com.mt.demo.springsecurity.service.BaseService;
import com.mt.demo.springsecurity.utils.LogController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;

/**
 * BaseManager
 *
 * @author MT.LUO
 * 2018/1/24 11:29
 * @Description:
 */
public class BaseManager< T, ID extends Serializable> implements BaseService<T,ID> {

    @Autowired
    protected BaseRepository<T, ID> baseRepository;

    @Override
    public void save(T entity) {
        baseRepository.save(entity);
    }

    @Override
    public void del(ID id) {
        baseRepository.delete(id);
    }

    @Override
    public int setDeletedTrue(ID id) {
        return baseRepository.setDeletedTrue(id);
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public List<T> findAll(Specification<T> specification) {
        return baseRepository.findAll(specification);
    }

    @Override
    public T findOne(ID id) {
        return baseRepository.findOne(id);
    }

    @Override
    public Page<T> findAll(Specification<T> specification, Pageable pageable, JSONObject object) {

        return baseRepository.findAll(specification, pageable);
    }

    @Override
    public boolean nameExsit(String name) {
        return false;
    }

    @Override
    public void test(ID id) {
        LogController.info("BaseManager: ",id.toString());
        baseRepository.testSelect(id);
       // baseRepository.testUpdate(id);
    }

}
