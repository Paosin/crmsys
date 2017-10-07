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
@Table(name = "crm_complaint", schema = "crm")
public class Complaint {
    private Integer id;
    private String title;
    private Date acceptDate;
    private String tel;
    private String type;
    private Integer level;
    private String attitude;
    private Client client;
    private Linkman linkman;
    private User user;
    private ComplaintState state;
    private Set<ComplaintRecord> records;

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
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "accetp_date")
    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    @Basic
    @Column(name = "tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "attitude")
    public String getAttitude() {
        return attitude;
    }

    public void setAttitude(String attitude) {
        this.attitude = attitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Complaint complaint = (Complaint) o;

        if (id != null ? !id.equals(complaint.id) : complaint.id != null) return false;
        if (title != null ? !title.equals(complaint.title) : complaint.title != null) return false;
        if (acceptDate != null ? !acceptDate.equals(complaint.acceptDate) : complaint.acceptDate != null)
            return false;
        if (tel != null ? !tel.equals(complaint.tel) : complaint.tel != null) return false;
        if (type != null ? !type.equals(complaint.type) : complaint.type != null) return false;
        if (level != null ? !level.equals(complaint.level) : complaint.level != null) return false;
        if (attitude != null ? !attitude.equals(complaint.attitude) : complaint.attitude != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (acceptDate != null ? acceptDate.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (attitude != null ? attitude.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
    @JoinColumn(name = "state", referencedColumnName = "id")
    public ComplaintState getState() {
        return state;
    }

    public void setState(ComplaintState state) {
        this.state = state;
    }

    @OneToMany(mappedBy = "complaint")
    public Set<ComplaintRecord> getRecords() {
        return records;
    }

    public void setRecords(Set<ComplaintRecord> records) {
        this.records = records;
    }
}
