package com.crmsys.vo;

import com.crmsys.po.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/9/30.
 */
public class MenuVo {
    private Integer id;
    private String name;
    private String title;
    private String href;
    private String target;
    private String constant;
    private Integer shown;
    private Integer enabled;
    private Integer type;
    private List<MenuVo> children;

    public MenuVo(Resource resource) {
        id=resource.getId();
        name=resource.getName();
        title=resource.getTitle();
        href=resource.getHref();
        target=resource.getTarget();
        constant=resource.getConstant();
        shown=resource.getShown();
        enabled=resource.getEnabled();
        type=resource.getType();
        children = new ArrayList<>();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getConstant() {
        return constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }

    public Integer getShown() {
        return shown;
    }

    public void setShown(Integer shown) {
        this.shown = shown;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<MenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVo> children) {
        this.children = children;
    }
}
