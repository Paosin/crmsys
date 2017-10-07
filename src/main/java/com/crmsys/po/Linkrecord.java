package com.crmsys.po;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Paosin Von Scarlet on 2017/9/28.
 */
@Entity
@Table(name = "crm_linkrecord", schema = "crm")
public class Linkrecord {
    private Integer id;
    private String content;
    private Date linkDate;
    private String linkType;
    private String linkWay;
    private Linkman linkman;
    private User user;
    private Client client;

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
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "link_date")
    public Date getLinkDate() {
        return linkDate;
    }

    public void setLinkDate(Date linkDate) {
        this.linkDate = linkDate;
    }

    @Basic
    @Column(name = "link_type")
    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    @Basic
    @Column(name = "link_way")
    public String getLinkWay() {
        return linkWay;
    }

    public void setLinkWay(String linkWay) {
        this.linkWay = linkWay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Linkrecord that = (Linkrecord) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (linkDate != null ? !linkDate.equals(that.linkDate) : that.linkDate != null)
            return false;
        if (linkType != null ? !linkType.equals(that.linkType) : that.linkType != null)
            return false;
        if (linkWay != null ? !linkWay.equals(that.linkWay) : that.linkWay != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (linkDate != null ? linkDate.hashCode() : 0);
        result = 31 * result + (linkType != null ? linkType.hashCode() : 0);
        result = 31 * result + (linkWay != null ? linkWay.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "linkman_id", referencedColumnName = "id")
    public Linkman getLinkman() {
        return linkman;
    }

    public void setLinkman(Linkman linkman) {
        this.linkman = linkman;
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
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
