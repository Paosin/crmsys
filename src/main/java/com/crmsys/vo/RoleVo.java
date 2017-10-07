package com.crmsys.vo;

import com.crmsys.po.Company;
import com.crmsys.po.Dept;
import com.crmsys.util.BeanCopyUtils;

/**
 * Created by Paosin Von Scarlet on 2017/10/3.
 */
public class RoleVo {
    private Integer id;
    private String name;
    private Integer enabled;
    private String description;
    private String constant;
    private DeptVo dept;
    private CompanyVo company;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConstant() {
        return constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }

    public DeptVo getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = BeanCopyUtils.copyProperties(DeptVo.class, dept);
    }

    public CompanyVo getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = BeanCopyUtils.copyProperties(CompanyVo.class,company);
    }
}
