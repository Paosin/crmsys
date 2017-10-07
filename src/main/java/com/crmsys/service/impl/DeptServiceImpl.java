package com.crmsys.service.impl;

import com.crmsys.dao.DeptDao;
import com.crmsys.dao.UserDao;
import com.crmsys.po.User;
import com.crmsys.service.DeptService;
import com.crmsys.po.Dept;
import com.crmsys.service.base.BaseService;
import com.crmsys.service.base.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/9/29.
 */
@Service
@Transactional(readOnly = true)
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements DeptService {
    private DeptDao deptDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    public void setDeptDao(DeptDao deptDao) {
        super.setBaseDao(deptDao);
        this.deptDao = deptDao;
    }

    @Override
    public List<Dept> listByComId(Integer id) {
        return deptDao.listByComId(id);
    }

    @Override
    public Dept getDeptByUser(Integer uid) {
        User user = this.userDao.getById(uid);
        return user.getDept();
    }
}
