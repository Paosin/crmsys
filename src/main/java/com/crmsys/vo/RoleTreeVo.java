package com.crmsys.vo;

import com.crmsys.po.Role;

/**
 * Created by Paosin Von Scarlet on 2017/10/3.
 */
public class RoleTreeVo {
    private Integer id;
    private String name;
    private boolean enabled;
    private boolean checked;

    public RoleTreeVo(Role role) {
        this.id=role.getId();
        this.name=role.getName();
        this.enabled = role.getEnabled() == 1;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
