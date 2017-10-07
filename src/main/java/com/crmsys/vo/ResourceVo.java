package com.crmsys.vo;

import com.crmsys.po.Resource;

/**
 * Created by Paosin Von Scarlet on 2017/9/30.
 */
public class ResourceVo {
    private Integer id;
    private String name;
    private Integer parent;
    private Boolean checked;
    private Boolean enabled;

    public ResourceVo(Resource resource) {
        this.id = resource.getId();
        this.name=resource.getName();
        this.enabled=resource.getEnabled()==1;

        Resource parent = resource.getParent();
        if (parent != null) {
            this.parent = parent.getId();
        }
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

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "ResourceVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                ", checked=" + checked +
                ", enabled=" + enabled +
                '}';
    }
}
