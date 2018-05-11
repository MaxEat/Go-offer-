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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author ASUS
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Job.findAll", query = "SELECT u FROM Job u")
    , @NamedQuery(name = "Job.findByCompany", query = "SELECT u FROM Job u WHERE u.companyID = :companyID")})
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobID;    
    private String jobName;   
    private String location;    
    private long companyID;
    private String description;
    //JobClassification jobclassification;
    protected Calendar expirationDate;
    
    

//    public JobClassification getJobclassification() {
//        return jobclassification;
//    }
//
//    public void setJobclassification(JobClassification jobclassification) {
//        this.jobclassification = jobclassification;
//    }
    
    public Job() {
    }

    public Calendar getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Calendar expirationDate) {
        this.expirationDate = expirationDate;
    }


    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    
    

    /**
     * Get the value of companyID
     *
     * @return the value of companyID
     */
    public long getCompanyID() {
        return companyID;
    }

    /**
     * Set the value of companyID
     *
     * @param companyID new value of companyID
     */
    public void setCompanyID(long companyID) {
        this.companyID = companyID;
    }


    /**
     * Get the value of location
     *
     * @return the value of location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set the value of location
     *
     * @param location new value of location
     */
    public void setLocation(String location) {
        this.location = location;
    }


    /**
     * Get the value of jobName
     *
     * @return the value of jobName
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * Set the value of jobName
     *
     * @param jobName new value of jobName
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }


    public Long getId() {
        return jobID;
    }

    public void setId(Long jobID) {
        this.jobID = jobID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobID != null ? jobID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.jobID == null && other.jobID != null) || (this.jobID != null && !this.jobID.equals(other.jobID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "goOffer.entities.Job[ jobID=" + jobID + " ]";
    }
    
}