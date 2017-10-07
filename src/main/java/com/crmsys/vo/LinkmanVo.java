package com.crmsys.vo;

import com.crmsys.po.Client;
import com.crmsys.util.BeanCopyUtils;

import java.sql.Date;

/**
 * Created by Paosin Von Scarlet on 2017/10/6.
 */
public class LinkmanVo {
    private Integer id;
    private String name;
    private Integer gender;
    private Date birthday;
    private String job;
    private Integer active;
    private String phone;
    private String email;
    private String content;
    private ClientVo client;

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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ClientVo getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = BeanCopyUtils.copyProperties(ClientVo.class, client);
    }
}
