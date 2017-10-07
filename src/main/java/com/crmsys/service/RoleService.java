package com.crmsys.service;

import com.crmsys.po.Role;
import com.crmsys.service.base.BaseService;
import com.crmsys.vo.DeptRoleTreeVo;
import com.crmsys.vo.RoleVo;

import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/9/29.
 */
public interface RoleService extends BaseService<Role>{

    /**
     * 获取用户的所有角色
     * @param uid
     * @return
     */
    List<DeptRoleTreeVo> listByUid(Integer uid);

    /**
     * 通过部门编号查找对应的角色
     * @param deptno 部门编号
     * @return 角色列表
     */
    List<RoleVo> listByDept(Integer deptno);

    /**
     * 更新当前用户下所有的角色
     * @param uid 用户id
     * @param ids 所有的角色 id
     */
    void updateByIds(Integer uid,Integer[] ids);

    /**
     * 根据用户id获取用户部门的所有角色
     * @param uid
     * @return
     */
    List<RoleVo> getByUid(Integer uid);

    /**
     * 更新角色名字以及唯一标识名
     * @param roid
     * @param name
     * @param constant
     */
    void updateNameAndConstantById(Integer roid, String name, String constant);

    /**
     * 判断当前用户是否为管理员，如果是，则返回所有角色
     * 如果不是，则返回当前用户的角色
     * @param uid
     * @return
     */
    List<RoleVo> listByAdmin(Integer uid);

    /**
     * 判断当前用户是否是管理员
     * @param uid
     * @return
     */
    Boolean checkAdmin(Integer uid);
}
