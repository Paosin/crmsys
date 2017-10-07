package com.crmsys.vo;


import com.crmsys.po.Dept;
import com.crmsys.po.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/10/3.
 */
public class DeptRoleTreeVo {
    private Integer id;
    private String name;
    private boolean nocheck = true;
    private List<RoleTreeVo> children;

    public DeptRoleTreeVo(Dept dept,List<RoleTreeVo> list) {
        this.id=dept.getId();
        this.name = dept.getDname();
        this.children = list;
    }

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

    public List<RoleTreeVo> getChildren() {
        return children;
    }

    public boolean isNocheck() {
        return nocheck;
    }

    public void setNocheck(boolean nocheck) {
        this.nocheck = nocheck;
    }

    public void setChildren(List<RoleTreeVo> children) {
        this.children = children;
    }
}
