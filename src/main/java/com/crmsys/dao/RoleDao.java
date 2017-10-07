package com.crmsys.dao;

import com.crmsys.dao.base.BaseDao;
import com.crmsys.po.Role;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/9/29.
 */
public interface RoleDao extends BaseDao<Role>{
    /**
     * 通过部门编号查找对应的角色
     * @param deptno 部门编号
     * @return 角色列表
     */
    List<Role> listByDeptNo(Integer deptno);

    /**
     * 通过用户id获取该用户所有的角色 id
     * @param uid
     * @return
     */
    List<Integer> getByUid(Integer uid);

    List<Role> getByIds(Integer[] ids);
}
