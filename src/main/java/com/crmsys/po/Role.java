package com.crmsys.po;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by Paosin Von Scarlet on 2017/9/28.
 */
@Entity
@Table(name = "crm_role", schema = "crm")
public class Role {
    private Integer id;
    private String name;
    private Integer enabled;
    private String constant;
    private String description;
    private Dept dept;
    private Company company;
    private Set<RoleResource> roleResources;

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
    @Column(name = "enabled")
    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;
        if (name != null ? !name.equals(role.name) : role.name != null) return false;
        if (enabled != null ? !enabled.equals(role.enabled) : role.enabled != null) return false;
        if (constant != null ? !constant.equals(role.constant) : role.constant != null)
            return false;
        if (description != null ? !description.equals(role.description) : role.description != null)
            return false;
        if (dept != null ? !dept.equals(role.dept) : role.dept != null) return false;
        return company != null ? company.equals(role.company) : role.company == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        result = 31 * result + (constant != null ? constant.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dept != null ? dept.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "id")
    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @OneToMany(mappedBy = "role")
    public Set<RoleResource> getRoleResources() {
        return roleResources;
    }

    public void setRoleResources(Set<RoleResource> roleResources) {
        this.roleResources = roleResources;
    }

    private Set<User> users;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "roles")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    @Basic
    public String getConstant() {
        return constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }
}
