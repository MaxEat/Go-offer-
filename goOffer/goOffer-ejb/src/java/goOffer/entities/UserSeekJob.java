/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.entities;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author ASUS
 */
@Entity
public class UserSeekJob implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userSeekJobID;
    private long jobID;
    private long userID;
    private Calendar applicationDate;

    public Calendar getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Calendar applicationDate) {
        this.applicationDate = applicationDate;
    }


    /**
     * Get the value of userID
     *
     * @return the value of userID
     */
    public long getUserID() {
        return userID;
    }

    /**
     * Set the value of userID
     *
     * @param userID new value of userID
     */
    public void setUserID(long userID) {
        this.userID = userID;
    }


    /**
     * Get the value of jobID
     *
     * @return the value of jobID
     */
    public long getJobID() {
        return jobID;
    }

    /**
     * Set the value of jobID
     *
     * @param jobID new value of jobID
     */
    public void setJobID(long jobID) {
        this.jobID = jobID;
    }


    public Long getId() {
        return userSeekJobID;
    }

    public void setId(Long id) {
        this.userSeekJobID = userSeekJobID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userSeekJobID != null ? userSeekJobID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSeekJob)) {
            return false;
        }
        UserSeekJob other = (UserSeekJob) object;
        if ((this.userSeekJobID == null && other.userSeekJobID != null) || (this.userSeekJobID != null && !this.userSeekJobID.equals(other.userSeekJobID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "goOffer.entities.UserSeekJob[ userSeekJobID=" + userSeekJobID + " ]";
    }
    
}
