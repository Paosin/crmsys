package com.crmsys.service.base.impl;

import com.crmsys.dao.base.BaseDao;
import com.crmsys.service.base.BaseService;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

/**
 * Created by Paosin Von Scarlet on 2017/9/30.
 */
@Transactional(readOnly = true)
public class BaseServiceImpl<T> implements BaseService<T> {

    BaseDao<T> baseDao;

    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(T t) {
        this.baseDao.save(t);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeById(Integer id) {
        this.baseDao.removeById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeAll(Integer[] ids) {
        this.baseDao.removeAll(ids);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(T t) {
        this.baseDao.update(t);
    }

    @Override
    public List<T> listAll() {
        return this.baseDao.listAll();
    }

    @Override
    public T getById(Integer id) {
        return (T) this.baseDao.getById(id);
    }
}
