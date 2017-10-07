package com.crmsys.po;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by Paosin Von Scarlet on 2017/9/28.
 */
@Entity
@Table(name = "crm_linkman", schema = "crm", catalog = "")
public class Linkman {
    private Integer id;
    private String name;
    private Integer gender;
    private Date birthday;
    private String job;
    private Integer active;
    private String phone;
    private String email;
    private String content;
    private Set<Complaint> complaints;
    private Client client;
    private Set<Linkrecord> records;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "gender")
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "job")
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Basic
    @Column(name = "active")
    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Linkman linkman = (Linkman) o;

        if (id != null ? !id.equals(linkman.id) : linkman.id != null) return false;
        if (name != null ? !name.equals(linkman.name) : linkman.name != null) return false;
        if (gender != null ? !gender.equals(linkman.gender) : linkman.gender != null) return false;
        if (birthday != null ? !birthday.equals(linkman.birthday) : linkman.birthday != null)
            return false;
        if (job != null ? !job.equals(linkman.job) : linkman.job != null) return false;
        if (active != null ? !active.equals(linkman.active) : linkman.active != null) return false;
        if (phone != null ? !phone.equals(linkman.phone) : linkman.phone != null) return false;
        if (email != null ? !email.equals(linkman.email) : linkman.email != null) return false;
        if (content != null ? !content.equals(linkman.content) : linkman.content != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (job != null ? job.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "linkman")
    public Set<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(Set<Complaint> complaints) {
        this.complaints = complaints;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @OneToMany(mappedBy = "linkman")
    public Set<Linkrecord> getRecords() {
        return records;
    }

    public void setRecords(Set<Linkrecord> records) {
        this.records = records;
    }
}
