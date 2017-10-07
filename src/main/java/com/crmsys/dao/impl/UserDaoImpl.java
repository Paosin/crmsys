package com.crmsys.dao.impl;

import com.crmsys.dao.UserDao;
import com.crmsys.dao.base.impl.BaseDaoImpl;
import com.crmsys.po.Company;
import com.crmsys.po.Dept;
import com.crmsys.po.User;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Paosin Von Scarlet on 2017/9/28.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{
    @Override
    public User getByUserNmaeAndPassword(String username, String password) {
        Map<String, String> rs = new HashMap<>();
        rs.put("username", username);
        rs.put("password", password);
        return (User) this.getSession().createCriteria(User.class).add(Restrictions.allEq(rs)).uniqueResult();
    }

    @Override
    public List<User> listByDeptNo(Integer deptno) {
        Dept dept = (Dept) this.getSession().get(Dept.class, deptno);
        List<User> rs = new ArrayList<>();
        rs.addAll(dept.getUsers());
        return rs;
    }

    @Override
    public void updateByComIdAndDeptNo(Integer uid, User user, Integer company_id, Integer deptno) {
//        User temp = (User) this.getSession().get(User.class, uid);
//        temp.setUsername(user.getUsername());
//        temp.setEmail(user.getEmail());
//        temp.setEnabled(user.getEnabled());
//        temp.setLocked(user.getLocked());
//        temp.setSex(user.getSex());
//        temp.setDescription(user.getDescription());
//        Company company = (Company) this.getSession().get(Company.class, company_id);
//        user.setCompany(company);
//        Dept dept = this.deptDao.getById(deptno);
//        user.setDept(dept);
//        dept.getUsers().add(temp);
//        company.getUsers().add(temp);
    }

}
