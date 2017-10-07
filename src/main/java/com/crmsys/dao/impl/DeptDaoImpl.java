package com.crmsys.dao.impl;

import com.crmsys.dao.DeptDao;
import com.crmsys.dao.base.impl.BaseDaoImpl;
import com.crmsys.po.Company;
import com.crmsys.po.Dept;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Paosin Von Scarlet on 2017/9/29.
 */
@Repository
public class DeptDaoImpl extends BaseDaoImpl<Dept> implements DeptDao {
    @Override
    public List<Dept> listByComId(Integer id) {
        List<Dept> rs = new ArrayList<>();
        Set<Dept> depts = ((Company) this.getSession().get(Company.class, id)).getDepts();
        rs.addAll(depts);
        return rs;
    }
}
