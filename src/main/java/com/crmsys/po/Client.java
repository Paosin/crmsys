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
@Table(name = "crm_client", schema = "crm")
public class Client {
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
    private User user;
    private City city;
    private Set<Complaint> complaints;
    private Set<Document> documents;
    private Set<Linkman> linkmen;
    private Set<Linkrecord> linkrecords;

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
    @Column(name = "legal")
    public String getLegal() {
        return legal;
    }

    public void setLegal(String legal) {
        this.legal = legal;
    }

    @Basic
    @Column(name = "postcode")
    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    @Basic
    @Column(name = "telphone")
    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
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
    @Column(name = "fax")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "register_date")
    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Basic
    @Column(name = "next_time")
    public Date getNextTime() {
        return nextTime;
    }

    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
    }

    @Basic
    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (id != null ? !id.equals(client.id) : client.id != null) return false;
        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        if (legal != null ? !legal.equals(client.legal) : client.legal != null) return false;
        if (postcode != null ? !postcode.equals(client.postcode) : client.postcode != null)
            return false;
        if (telphone != null ? !telphone.equals(client.telphone) : client.telphone != null)
            return false;
        if (phone != null ? !phone.equals(client.phone) : client.phone != null) return false;
        if (fax != null ? !fax.equals(client.fax) : client.fax != null) return false;
        if (email != null ? !email.equals(client.email) : client.email != null) return false;
        if (url != null ? !url.equals(client.url) : client.url != null) return false;
        if (registerDate != null ? !registerDate.equals(client.registerDate) : client.registerDate != null)
            return false;
        if (nextTime != null ? !nextTime.equals(client.nextTime) : client.nextTime != null)
            return false;
        if (level != null ? !level.equals(client.level) : client.level != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (legal != null ? legal.hashCode() : 0);
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        result = 31 * result + (telphone != null ? telphone.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (registerDate != null ? registerDate.hashCode() : 0);
        result = 31 * result + (nextTime != null ? nextTime.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "city", referencedColumnName = "id")
    public City getCity() {
        return city;
    }

    public void setCity(City cities) {
        this.city = cities;
    }

    @OneToMany(mappedBy = "client")
    public Set<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(Set<Complaint> complaints) {
        this.complaints = complaints;
    }

    @OneToMany(mappedBy = "client")
    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    @OneToMany(mappedBy = "client")
    public Set<Linkman> getLinkmen() {
        return linkmen;
    }

    public void setLinkmen(Set<Linkman> linkmen) {
        this.linkmen = linkmen;
    }

    @OneToMany(mappedBy = "client")
    public Set<Linkrecord> getLinkrecords() {
        return linkrecords;
    }

    public void setLinkrecords(Set<Linkrecord> linkrecords) {
        this.linkrecords = linkrecords;
    }
}
