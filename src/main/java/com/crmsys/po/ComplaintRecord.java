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
@Table(name = "crm_complaint_record", schema = "crm")
public class ComplaintRecord {
    private Integer id;
    private String complaintTitle;
    private String content;
    private Date recordDate;
    private Set<ComplaintDispose> disposes;
    private ComplaintState state;
    private Complaint complaint;
    private User user;

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
    @Column(name = "complaint_title")
    public String getComplaintTitle() {
        return complaintTitle;
    }

    public void setComplaintTitle(String complaintTitle) {
        this.complaintTitle = complaintTitle;
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
    @Column(name = "record_date")
    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplaintRecord that = (ComplaintRecord) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (complaintTitle != null ? !complaintTitle.equals(that.complaintTitle) : that.complaintTitle != null)
            return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (recordDate != null ? !recordDate.equals(that.recordDate) : that.recordDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (complaintTitle != null ? complaintTitle.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (recordDate != null ? recordDate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "record")
    public Set<ComplaintDispose> getDisposes() {
        return disposes;
    }

    public void setDisposes(Set<ComplaintDispose> disposes) {
        this.disposes = disposes;
    }

    @ManyToOne
    @JoinColumn(name = "state", referencedColumnName = "id")
    public ComplaintState getState() {
        return state;
    }

    public void setState(ComplaintState state) {
        this.state = state;
    }

    @ManyToOne
    @JoinColumn(name = "comp_Id", referencedColumnName = "id")
    public Complaint getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
