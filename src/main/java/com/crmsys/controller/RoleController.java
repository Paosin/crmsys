package com.crmsys.controller;

import com.crmsys.comm.ResourceConstant;
import com.crmsys.po.Role;
import com.crmsys.po.User;
import com.crmsys.security.annotation.Authorize;
import com.crmsys.service.RoleService;
import com.crmsys.service.UserService;
import com.crmsys.util.BeanCopyUtils;
import com.crmsys.vo.DeptRoleTreeVo;
import com.crmsys.vo.RoleVo;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpSession;

/**
 * Created by Paosin Von Scarlet on 2017/9/29.
 */
@Controller
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    /**
     * 获取当前登录的用户下所在部门的所有角色
     *
     * @param mav
     * @param session
     * @return 角色管理页面
     */
    @Authorize(ResourceConstant.SYS_ROLE_VIEW)
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public ModelAndView getAll(ModelAndView mav, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (Objects.isNull(user)) {
            mav.setViewName("redirect:/logout");
            return mav;
        }

        //判断当前用户是否是超级管理员
        Set<Role> userRoles = user.getRoles();
        List<RoleVo> roles = this.roleService.listByAdmin(user.getId());

        mav.addObject("roles", BeanCopyUtils.copyProperties(RoleVo.class, roles));
        mav.setViewName("sysmgr/rolemgr.jsp");
        return mav;
    }

    @Authorize(ResourceConstant.SYS_ROLE_UPDATE)
    @RequestMapping(value = "/role/{roid}", method = RequestMethod.PUT)
    public String update(@PathVariable("roid") Integer roid, String name, String constant) {
        this.roleService.updateNameAndConstantById(roid, name, constant);
        return "redirect:/roles";
    }

    /**
     * TODO: 2017/10/3 便于处理新开一个页面，有时间再改成模态框
     * 获取所有部门下的所有角色，用于页面上的显示
     * 需要所有部门的名称以及相应的id
     *
     * @param mav
     * @return 分配角色页面
     */
    @Authorize(ResourceConstant.SYS_ROLE_VIEW)
    @RequestMapping(value = "/roles/user/{uid}", method = RequestMethod.GET)
    public ModelAndView getAllRole(@PathVariable Integer uid, ModelAndView mav) {

        List<DeptRoleTreeVo> deptRoleVo = this.roleService.listByUid(uid);
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(deptRoleVo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mav.addObject("tree", json);
        mav.addObject("_user", this.userService.getById(uid));
        mav.setViewName("sysmgr/allocrole.jsp");
        return mav;
    }

    /**
     * 更新用户id为 uid的角色
     *
     * @param uid 用户uid
     * @param ids 用户所有的角色 id
     * @return 一个成功的ajax
     */
    @Authorize(ResourceConstant.SYS_ROLE_UPDATE)
    @RequestMapping(value = "/roles/user/{uid}", method = RequestMethod.PUT)
    public @ResponseBody
    String updateRoleByUser(@PathVariable Integer uid, Integer[] ids) {
        this.roleService.updateByIds(uid, ids);
        return "success";
    }

    /**
     * 获取当前角色的所有信息
     *
     * @param roid 角色 id
     * @return
     */
    @Authorize(ResourceConstant.SYS_ROLE_VIEW)
    @RequestMapping(value = "/role/{roid}", method = RequestMethod.GET)
    public @ResponseBody
    RoleVo getRoleById(@PathVariable("roid") Integer roid) {
        Role role = this.roleService.getById(roid);
        return BeanCopyUtils.copyProperties(RoleVo.class, role);
    }

    /**
     * 通过部门获取所有的角色
     *
     * @param deptno
     * @param mav
     * @return 部门角色
     */
    @Authorize(ResourceConstant.SYS_ROLE_VIEW)
    @RequestMapping(value = "/roles/dept/{deptno}", method = RequestMethod.GET)
    public ModelAndView getResByDept(@PathVariable("deptno") Integer deptno, ModelAndView mav) {
        List<RoleVo> roles = this.roleService.listByDept(deptno);
        mav.addObject("roles", roles);
        mav.setViewName("sysmgr/rolemgr.jsp");
        return mav;
    }

    /**
     * 根据角色id删除一个角色
     *
     * @param roid 角色id
     * @return 当前登录用户的角色列表
     */
    @Authorize(ResourceConstant.SYS_ROLE_DELETE)
    @RequestMapping(value = "/role/{roid}", method = RequestMethod.DELETE)
    public String remove(@PathVariable("roid") Integer roid) {
        roleService.removeById(roid);
        return "redirect:/roles";
    }
}
