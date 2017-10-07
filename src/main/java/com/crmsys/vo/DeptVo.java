package com.crmsys.vo;

import com.crmsys.po.Dept;

/**
 * Created by Paosin Von Scarlet on 2017/9/29.
 */
public class DeptVo {
    private Integer id;
    private String dname;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
