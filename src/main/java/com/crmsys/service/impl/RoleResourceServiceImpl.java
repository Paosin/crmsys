package com.crmsys.service.impl;

import com.crmsys.dao.RoleResourceDao;
import com.crmsys.po.RoleResource;
import com.crmsys.service.RoleResourceService;
import com.crmsys.service.base.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/9/30.
 */
@Service
public class RoleResourceServiceImpl extends BaseServiceImpl<RoleResource> implements RoleResourceService {

    RoleResourceDao roleResourceDao;

    @Autowired
    public void setRoleResourceDao(RoleResourceDao roleResourceDao) {
        super.setBaseDao(roleResourceDao);
        this.roleResourceDao = roleResourceDao;
    }

    @Override
    public List<RoleResource> listByResIds(Integer[] ids) {
        return this.roleResourceDao.listByResIds(ids);
    }
}
