package com.mt.demo.springsecurity.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;

/**
 * BaseService
 *
 * @author MT.LUO
 * 2018/1/24 11:15
 * @Description:
 */
public interface BaseService<T, ID extends Serializable> {
    void save(T entity);

    void del(ID id);

    int setDeletedTrue(ID id);

    List<T> findAll();

    List<T> findAll(Specification<T> specification);

    T findOne(ID id);

    Page<T> findAll(Specification<T> specification, Pageable pageable, JSONObject object);

    boolean nameExsit(String name);

    void test(ID id);
}
