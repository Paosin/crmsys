package com.crmsys.service;

import com.crmsys.po.User;
import com.crmsys.service.base.BaseService;
import com.crmsys.vo.UserVo;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/9/29.
 */
public interface UserService extends BaseService<User> {
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
    List<UserVo> listByDeptNo(Integer deptno);

    UserVo getAjaxById(Integer uid);

    void updateByComIdAndDeptNo(Integer uid,User user, Integer company_id, Integer deptno);
}
