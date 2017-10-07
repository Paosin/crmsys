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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by Paosin Von Scarlet on 2017/9/28.
 */
@Entity
@Table(name = "crm_resource", schema = "crm")
public class Resource {
    private Integer id;
    private String name;
    private String title;
    private String href;
    private String target;
    private String constant;
    private Integer shown;
    private Integer enabled;
    private Integer type;
    private Resource parent;
    private Set<Resource> resources;
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
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "href")
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Basic
    @Column(name = "target")
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Basic
    @Column(name = "constant")
    public String getConstant() {
        return constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }

    @Basic
    @Column(name = "shown")
    public Integer getShown() {
        return shown;
    }

    public void setShown(Integer shown) {
        this.shown = shown;
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
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        if (id != null ? !id.equals(resource.id) : resource.id != null) return false;
        if (name != null ? !name.equals(resource.name) : resource.name != null) return false;
        if (title != null ? !title.equals(resource.title) : resource.title != null) return false;
        if (href != null ? !href.equals(resource.href) : resource.href != null) return false;
        if (target != null ? !target.equals(resource.target) : resource.target != null)
            return false;
        if (constant != null ? !constant.equals(resource.constant) : resource.constant != null)
            return false;
        if (shown != null ? !shown.equals(resource.shown) : resource.shown != null) return false;
        if (enabled != null ? !enabled.equals(resource.enabled) : resource.enabled != null)
            return false;
        if (type != null ? !type.equals(resource.type) : resource.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (href != null ? href.hashCode() : 0);
        result = 31 * result + (target != null ? target.hashCode() : 0);
        result = 31 * result + (constant != null ? constant.hashCode() : 0);
        result = 31 * result + (shown != null ? shown.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "parent", referencedColumnName = "id")
    public Resource getParent() {
        return parent;
    }

    public void setParent(Resource parent) {
        this.parent = parent;
    }

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "parent")
    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    @OneToMany(mappedBy = "resource",fetch = FetchType.EAGER)
    public Set<RoleResource> getRoleResources() {
        return roleResources;
    }

    public void setRoleResources(Set<RoleResource> roleResources) {
        this.roleResources = roleResources;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", resources=" + resources +
                '}';
    }
}
