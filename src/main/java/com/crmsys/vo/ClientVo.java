package com.crmsys.vo;

import com.crmsys.po.City;
import com.crmsys.po.User;
import com.crmsys.util.BeanCopyUtils;

import java.sql.Date;

/**
 * Created by Paosin Von Scarlet on 2017/10/6.
 */
public class ClientVo {
    private Integer id;
    private String name;
    private String legal;
    private Integer postcode;
    private String telphone;
    private String phone;
    private String fax;
    private String email;
    private String url;
    private Date registerDate;
    private Date nextTime;
    private Integer level;
    private UserVo user;
    private CityVo city;

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

    public String getLegal() {
        return legal;
    }

    public void setLegal(String legal) {
        this.legal = legal;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getNextTime() {
        return nextTime;
    }

    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = BeanCopyUtils.copyProperties(UserVo.class, user);
    }

    public CityVo getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = BeanCopyUtils.copyProperties(CityVo.class, city);
    }
}
