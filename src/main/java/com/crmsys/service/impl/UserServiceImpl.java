package com.crmsys.service.impl;

import com.crmsys.dao.CompanyDao;
import com.crmsys.dao.DeptDao;
import com.crmsys.dao.UserDao;
import com.crmsys.po.Company;
import com.crmsys.po.Dept;
import com.crmsys.service.UserService;
import com.crmsys.po.User;
import com.crmsys.service.base.impl.BaseServiceImpl;
import com.crmsys.util.BeanCopyUtils;
import com.crmsys.vo.UserVo;

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
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private UserDao userDao;

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private DeptDao deptDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        super.setBaseDao(userDao);
        this.userDao = userDao;
    }

    @Override
    public User getByUserNmaeAndPassword(String username, String password) {
        return this.userDao.getByUserNmaeAndPassword(username,password);
    }
    @Override
    public List<UserVo> listByDeptNo(Integer deptno) {
        List<UserVo> rs = BeanCopyUtils.copyProperties(UserVo.class, this.userDao.listByDeptNo(deptno));
        return rs;
    }

    @Override
    public UserVo getAjaxById(Integer uid) {
        return BeanCopyUtils.copyProperties(UserVo.class, this.userDao.getById(uid));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateByComIdAndDeptNo(Integer uid,User user, Integer company_id, Integer deptno) {
        User temp = this.userDao.getById(uid);
        temp.setUsername(user.getUsername());
        temp.setEmail(user.getEmail());
        temp.setEnabled(user.getEnabled());
        temp.setLocked(user.getLocked());
        temp.setSex(user.getSex());
        temp.setDescription(user.getDescription());
        Company company = this.companyDao.getById(company_id);
        temp.setCompany(company);
        Dept dept = this.deptDao.getById(deptno);
        temp.setDept(dept);
        this.userDao.update(temp);
    }
}
