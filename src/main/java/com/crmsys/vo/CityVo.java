package com.crmsys.vo;

import com.crmsys.po.City;
import com.crmsys.util.BeanCopyUtils;

/**
 * Created by Paosin Von Scarlet on 2017/10/6.
 */
public class CityVo {
    private Integer id;
    private String code;
    private String name;
    private String firstLetter;
    private Integer level;
    private CityVo parent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public CityVo getParent() {
        return parent;
    }

    public void setParent(City parent) {
        this.parent = BeanCopyUtils.copyProperties(CityVo.class, parent);
    }
}
