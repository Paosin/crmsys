package com.crmsys.po;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by Paosin Von Scarlet on 2017/9/28.
 */
@Entity
@Table(name = "crm_user", schema = "crm")
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Integer sex;
    private Integer enabled;
    private Integer locked;
    private Set<Client> clients;
    private Set<Complaint> complaints;
    private Set<ComplaintRecord> complaintRecords;
    private Set<Document> documents;
    private Set<Linkrecord> records;
    private Company company;
    private Dept dept;
    private String description;

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    @Column(name = "sex")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "enabled")
    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "locked")
    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null)
            return false;
        if (password != null ? !password.equals(user.password) : user.password != null)
            return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (sex != null ? !sex.equals(user.sex) : user.sex != null) return false;
        if (enabled != null ? !enabled.equals(user.enabled) : user.enabled != null) return false;
        if (locked != null ? !locked.equals(user.locked) : user.locked != null) return false;
        if (company != null ? !company.equals(user.company) : user.company != null) return false;
        if (dept != null ? !dept.equals(user.dept) : user.dept != null) return false;
        return description != null ? description.equals(user.description) : user.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        result = 31 * result + (locked != null ? locked.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (dept != null ? dept.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "user")
    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    @OneToMany(mappedBy = "user")
    public Set<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(Set<Complaint> complaints) {
        this.complaints = complaints;
    }

    @OneToMany(mappedBy = "user")
    public Set<ComplaintRecord> getComplaintRecords() {
        return complaintRecords;
    }

    public void setComplaintRecords(Set<ComplaintRecord> complaintRecords) {
        this.complaintRecords = complaintRecords;
    }

    @OneToMany(mappedBy = "user")
    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    @OneToMany(mappedBy = "user")
    public Set<Linkrecord> getRecords() {
        return records;
    }

    public void setRecords(Set<Linkrecord> records) {
        this.records = records;
    }

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "deptno", referencedColumnName = "id")
    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "crm_user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            }, inverseJoinColumns = {
                @JoinColumn(name = "role_id")
        }
    )
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
