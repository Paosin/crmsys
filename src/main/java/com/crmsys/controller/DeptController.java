package com.crmsys.controller;

import com.crmsys.comm.ResourceConstant;
import com.crmsys.po.Company;
import com.crmsys.po.Dept;
import com.crmsys.po.User;
import com.crmsys.security.annotation.Authorize;
import com.crmsys.service.CompanyService;
import com.crmsys.service.DeptService;
import com.crmsys.service.ResourceService;
import com.crmsys.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Paosin Von Scarlet on 2017/9/29.
 */
@Controller
public class DeptController {
    @Autowired
    private DeptService deptService;

    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserService userService;

    /**
     * 获取所有部门
     * @param mav
     * @return
     */
    @Authorize(value = ResourceConstant.SYS_DEPT_VIEW)
    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    public ModelAndView deptManager( HttpSession session,ModelAndView mav){
        User user = (User) session.getAttribute("user");
        List<Dept> depts = deptService.listByComId(user.getCompany().getId());
        mav.addObject("depts", depts);
        mav.addObject("company", user.getCompany());
        mav.setViewName("sysmgr/deptmgr.jsp");
        return mav;
    }

    /**
     * 获取单个部门
     * @param deptno
     * @return
     */
    @Authorize(value = ResourceConstant.SYS_DEPT_VIEW)
    @RequestMapping(value="/dept/{uid}/{deptno}",method = RequestMethod.GET)
    public @ResponseBody Dept getDeptInJson(@PathVariable("deptno") Integer deptno) {
        Dept dept = deptService.getById(deptno);
        return dept;
    }

    @Authorize(value = ResourceConstant.SYS_DEPT_DELETE)
    @RequestMapping(value = "/dept/{uid}/{deptno}",method = RequestMethod.DELETE)
    public String removeDept(@PathVariable("uid") Integer uid,@PathVariable("deptno")Integer deptno){
        deptService.removeById(deptno);
        return "redirect:/depts";
    }

    @Authorize(value = ResourceConstant.SYS_DEPT_UPDATE)
    @RequestMapping(value = "/dept/{uid}/{deptno}",method = RequestMethod.PUT)
    public String updateDept(@PathVariable("uid") Integer uid,@PathVariable("deptno") Integer deptno,String dname,String description) {
        Dept deptTemp = deptService.getById(deptno);
        deptTemp.setDname(dname);
        deptTemp.setDescription(description);
        deptService.update(deptTemp);
        return "redirect:/depts";
    }

    @Authorize(value = ResourceConstant.SYS_DEPT_SAVE)
    @RequestMapping(value = "/dept/{uid}",method = RequestMethod.POST)
    public String saveDept(@PathVariable("uid") Integer uid,Dept dept) {
        Company company = this.companyService.getById(1);
        dept.setCompanies(new HashSet<>());
        dept.getCompanies().add(company);
        this.deptService.save(dept);

        return "redirect:/depts";
    }

    /**
     * 查询该公司下所有的部门
     * @param comid
     * @return
     */
    @Authorize(value = ResourceConstant.SYS_DEPT_VIEW)
    @RequestMapping(value = "/dept/company/{comId}")
    public @ResponseBody
    List<Dept> getDeptsByComId(@PathVariable("comId") Integer comid) {
        List<Dept> depts = this.deptService.listByComId(comid);
        return depts;
    }
}
