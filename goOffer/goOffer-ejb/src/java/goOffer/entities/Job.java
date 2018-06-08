/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "Job")
@NamedQueries({
    @NamedQuery(name = "Job.findAll", query = "SELECT u FROM Job u")
    , @NamedQuery(name = "Job.deleteByID", query = "DELETE FROM Job u WHERE u.jobID = :jobID")
//    , @NamedQuery(name = "Job.findByCompany", query = "SELECT u FROM Job u WHERE u.companyID = :companyID")
    , @NamedQuery(name = "Job.findByJobType", query = "SELECT u FROM Job u WHERE u.type = :type")
    , @NamedQuery(name = "Job.findJobsBefore", query = "SELECT u FROM Job u WHERE u.expirationDate < :expirationDate")})
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public enum jobType {MANAGER, PROGRAMMER, HR};
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobID;   
    @Column
    private String jobName;
    @Column
    private String location;    
    @Column
    private String description;
    @Column
    private Date expirationDate;
    @Column
    @Enumerated(EnumType.STRING)
    private jobType type;
   
    @ManyToOne
    @JoinColumn(name = "companyID")
    private Company company;
    
    @ManyToMany(mappedBy="appliedJobs")
    private List<Usertable> appliedUsers;
    
    
    public Job() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    
    
    public List<Usertable> getAppliedUsers() {
        return appliedUsers;
    }

    public void setAppliedUsers(List<Usertable> appliedUsers) {
        this.appliedUsers = appliedUsers;
    }

    public Long getJobID() {
        return jobID;
    }

    public void setJobID(Long jobID) {
        this.jobID = jobID;
    }
    
    public jobType getType() {
        return type;
    }

    public void setType(jobType type) {
        this.type = type;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
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
//    public long getCompanyID() {
//        return companyID;
//    }
//
//    /**
//     * Set the value of companyID
//     *
//     * @param companyID new value of companyID
//     */
//    public void setCompanyID(long companyID) {
//        this.companyID = companyID;
//    }


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