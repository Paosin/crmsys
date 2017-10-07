package com.crmsys.service;

import com.crmsys.po.Dept;
import com.crmsys.service.base.BaseService;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/9/29.
 */
public interface DeptService extends BaseService<Dept>{
    List<Dept> listByComId(Integer id);

    /**
     * 通过员工 id获取相应的部门
     * @param uid
     * @return
     */
    Dept getDeptByUser(Integer uid);
}
