package com.crmsys.dao;

import com.crmsys.dao.base.BaseDao;
import com.crmsys.po.User;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/9/28.
 */
public interface UserDao extends BaseDao<User>{
    /**
     * 登录用，根据用户名和密码查询用户是否存在
     * @param username
     * @param password
     * @return
     */
    User getByUserNmaeAndPassword(String username, String password);

    /**
     * 根据部门编号获取所有员工
     * @param deptno
     * @return
     */
    List<User> listByDeptNo(Integer deptno);
    void updateByComIdAndDeptNo(Integer uid,User user, Integer company_id, Integer deptno);
}
