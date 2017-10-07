package com.crmsys.vo;

import com.crmsys.po.Company;
import com.crmsys.po.Dept;
import com.crmsys.util.BeanCopyUtils;

/**
 * Created by Paosin Von Scarlet on 2017/10/2.
 */
public class UserVo {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Integer sex;
    private Integer enabled;
    private Integer locked;
    private CompanyVo company;
    private Dept dept;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public CompanyVo getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = BeanCopyUtils.copyProperties(CompanyVo.class,company);
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
