package com.crmsys.service.impl;

import com.crmsys.dao.DeptDao;
import com.crmsys.dao.RoleDao;
import com.crmsys.dao.UserDao;
import com.crmsys.po.Dept;
import com.crmsys.po.Role;
import com.crmsys.po.User;
import com.crmsys.service.RoleService;
import com.crmsys.service.base.impl.BaseServiceImpl;
import com.crmsys.util.BeanCopyUtils;
import com.crmsys.vo.DeptRoleTreeVo;
import com.crmsys.vo.RoleTreeVo;
import com.crmsys.vo.RoleVo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Paosin Von Scarlet on 2017/9/30.
 */
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    RoleDao roleDao;

    @Autowired
    UserDao userDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        super.setBaseDao(roleDao);
        this.roleDao = roleDao;
    }

    @Override
    public List<DeptRoleTreeVo> listByUid(Integer uid) {
        User user = this.userDao.getById(uid);
        // 获取当前用户所在公司的所有部门
        Set<Dept> depts = user.getCompany().getDepts();
        // 部门里含有该部门下所有的角色

        List<DeptRoleTreeVo> rs = new ArrayList<>();

        for (Dept dept : depts) {
            List<RoleTreeVo> temps = new ArrayList<>();
            for (Role role : dept.getRoles()) {
                RoleTreeVo roleTreeVo = new RoleTreeVo(role);
                temps.add(roleTreeVo);
                if (user.getRoles().contains(role)) {
                    roleTreeVo.setChecked(true);
                } else {
                    roleTreeVo.setChecked(false);
                }
            }
            rs.add(new DeptRoleTreeVo(dept, temps));
        }
        return rs;
    }

    @Override
    public List<RoleVo> listByDept(Integer deptno) {
        return BeanCopyUtils.copyProperties(RoleVo.class, this.roleDao.listByDeptNo(deptno));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateByIds(Integer uid, Integer[] ids) {
        List<Role> roles = this.roleDao.getByIds(ids);
        User user = this.userDao.getById(uid);
        user.getRoles().clear();
        user.getRoles().addAll(roles);
        this.userDao.update(user);
    }

    @Override
    public List<RoleVo> getByUid(Integer uid) {
        User user = this.userDao.getById(uid);
        List<Role> roles = new ArrayList<>();
        roles.addAll(user.getDept().getRoles());
        return BeanCopyUtils.copyProperties(RoleVo.class, roles);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateNameAndConstantById(Integer roid, String name, String constant) {
        Role role = this.roleDao.getById(roid);
        role.setName(name);
        role.setConstant(constant);
        this.roleDao.update(role);
    }

    @Override
    public List<RoleVo> listByAdmin(Integer uid) {
        if (checkAdmin(uid)) {
            return BeanCopyUtils.copyProperties(RoleVo.class, this.roleDao.listAll());
        }
        return this.getByUid(uid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean checkAdmin(Integer uid) {
        return this.userDao.getById(uid).getRoles().contains(this.roleDao.getById(1));
    }

}
