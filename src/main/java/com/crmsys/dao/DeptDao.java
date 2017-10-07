package com.crmsys.dao;

import com.crmsys.dao.base.BaseDao;
import com.crmsys.po.Dept;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/9/29.
 */
public interface DeptDao extends BaseDao<Dept> {
    List<Dept> listByComId(Integer id);
}
