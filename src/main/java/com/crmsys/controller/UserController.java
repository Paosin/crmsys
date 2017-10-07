package com.crmsys.controller;

import com.crmsys.comm.ResourceConstant;
import com.crmsys.dao.RoleDao;
import com.crmsys.po.Company;
import com.crmsys.po.User;
import com.crmsys.security.annotation.Authorize;
import com.crmsys.service.CompanyService;
import com.crmsys.service.DeptService;
import com.crmsys.service.RoleService;
import com.crmsys.service.UserService;
import com.crmsys.vo.UserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * Created by Paosin Von Scarlet on 2017/9/29.
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    CompanyService companyService;

    @Autowired
    DeptService deptService;

    @Autowired
    RoleService roleService;

    /**
     * 通过部门编号为deptno下的所有用户
     *
     * @param deptno
     * @param mav
     * @return
     */
    @Authorize(ResourceConstant.SYS_USER_VIEW)
    @RequestMapping(value = "/users/dept/{deptno}", method = RequestMethod.GET)
    public ModelAndView getUserByDept(@PathVariable("deptno") Integer deptno, ModelAndView mav) {
        List<UserVo> users = this.userService.listByDeptNo(deptno);
        mav.addObject("users", users);
        List<Company> companies = this.companyService.listAll();
        mav.addObject("companies", companies);
        mav.setViewName("sysmgr/usermgr.jsp");
        return mav;
    }

    /**
     * 侧边栏 员工管理按钮 获取当前用户所在部门的所有员工
     *
     * @param mav
     * @return
     */
    @Authorize(ResourceConstant.SYS_ROLE_VIEW)
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public ModelAndView getUsers(HttpSession session, ModelAndView mav) {
        User user = (User) session.getAttribute("user");
        if (this.roleService.checkAdmin(user.getId())) {
            List<User> users = this.userService.listAll();
            mav.addObject("users", users);
            List<Company> companies = this.companyService.listAll();
            mav.addObject("companies", companies);
            mav.setViewName("sysmgr/usermgr.jsp");
        } else {
            mav.setViewName("redirect:/users/dept/" + deptService.getDeptByUser(user.getId()).getId());
        }
        return mav;
    }

    /**
     * ajax请求获得一个用户的信息
     *
     * @param uid
     * @return
     */
    @RequestMapping(value = "user/{uid}", method = RequestMethod.GET)
    public @ResponseBody
    UserVo getUser(@PathVariable("uid") Integer uid) {
        UserVo user = this.userService.getAjaxById(uid);
        return user;
    }

    @Authorize(ResourceConstant.SYS_USER_DELETE)
    @RequestMapping(value = "/user/{uid}", method = RequestMethod.DELETE)
    public String delUser(@PathVariable("uid") Integer uid) {
        this.userService.removeById(uid);
        return "redirect:/users";
    }


    @Authorize(ResourceConstant.SYS_USER_UPDATE)
    @RequestMapping(value = "/user/{uid}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable("uid") Integer uid, User user, Integer company_id, Integer deptno) {
        this.userService.updateByComIdAndDeptNo(uid, user, company_id, deptno);
        return "redirect:/users";
    }

    @Authorize(ResourceConstant.SYS_USER_SAVE)
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String addUser(User user, Integer company_id, Integer deptno) {
        user.setPassword("000");
        user.setDept(this.deptService.getById(deptno));
        user.setCompany(this.companyService.getById(company_id));
        this.userService.save(user);
        return "redirect:/users";
    }
}
