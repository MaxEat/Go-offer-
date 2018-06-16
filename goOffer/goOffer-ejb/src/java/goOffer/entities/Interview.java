/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author max
 */
@Entity
@Table(name = "Interview")
@NamedQueries({
    @NamedQuery(name = "Interview.findByJobID", query = "SELECT u FROM Interview u WHERE u.jobID = :jobID")
    , @NamedQuery(name = "Interview.findByUsername", query = "SELECT u FROM Interview u WHERE u.username = :username")})
public class Interview implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String interview_date;
    @Column
    private Long jobID;
    @Column
    private String username;

    public Interview() {
    }

    public Long getJobID() {
        return jobID;
    }

    public void setJobID(Long jobID) {
        this.jobID = jobID;
    }

    public String getUserID() {
        return username;
    }

    public void setUserID(String name) {
        this.username = name;
    }

    public Interview(Long jobid, String name ){
        jobID = jobid;
        username = name;
    }
    
    public Long getId() {
        return id;
    }

    public String getInterview_date() {
        return interview_date;
    }

    public void setInterview_date(String interview_date) {
        this.interview_date = interview_date;
    }

 

 


    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Interview)) {
            return false;
        }
        Interview other = (Interview) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "goOffer.ejbs.Interview[ id=" + id + " ]";
    }
    
}
