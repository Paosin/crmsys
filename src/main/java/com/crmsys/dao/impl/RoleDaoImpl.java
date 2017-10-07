package com.crmsys.dao.impl;

import com.crmsys.dao.RoleDao;
import com.crmsys.dao.base.impl.BaseDaoImpl;
import com.crmsys.po.Dept;
import com.crmsys.po.Role;
import com.crmsys.po.User;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/9/29.
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
    @Override
    public List<Role> listByDeptNo(Integer deptno) {
        Dept dept = (Dept) this.getSession().get(Dept.class, deptno);
        List<Role> rs = new ArrayList<>();
        rs.addAll(dept.getRoles());
        return rs;
    }

    @Override
    public List<Integer> getByUid(Integer uid) {
        User user = (User) this.getSession().get(User.class, uid);
        List<Integer> rs = new ArrayList<>();
        for (Role role : user.getRoles()) {
            rs.add(role.getId());
        }
        return rs;
    }

    @Override
    public List<Role> getByIds(Integer[] ids) {
        return this.getSession().createQuery("from Role r where r.id in (:ids)").setParameterList("ids", ids).list();
    }
}
