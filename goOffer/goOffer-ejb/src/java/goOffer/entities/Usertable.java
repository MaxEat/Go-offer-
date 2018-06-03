/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jiahao pan
 */
@Entity
@Table(name = "USERTABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usertable.findAll", query = "SELECT u FROM Usertable u")
    , @NamedQuery(name = "Usertable.findByUserid", query = "SELECT u FROM Usertable u WHERE u.userid = :userid")
    , @NamedQuery(name = "Usertable.findByUsername", query = "SELECT u FROM Usertable u WHERE u.username = :username")
    , @NamedQuery(name = "Usertable.findByPassword", query = "SELECT u FROM Usertable u WHERE u.password = :password")
    , @NamedQuery(name = "Usertable.checkCredential", query = "SELECT u FROM Usertable u WHERE u.password = :password AND u.username = :username")})
public class Usertable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "USERID")
    private Long userid;
    @Basic(optional = false)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    
    @ManyToMany
    @JoinTable(name="UsersAndJobs")
    private List<Job> appliedJobs;

    public Usertable() {
    }

    public Usertable(Long userid) {
        this.userid = userid;
    }

    public Usertable( String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public Usertable(Long userid, String username, String password) {
        this.userid = userid;
        this.username = username;
        this.password = password;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Job> getAppliedJobs() {
        return appliedJobs;
    }

    public void setAppliedJobs(List<Job> appliedJobs) {
        this.appliedJobs = appliedJobs;
    }
    
    public void addJob(Job newJob){
        appliedJobs.add(newJob);
        if (!newJob.getAppliedUsers().contains(this)) {
            newJob.getAppliedUsers().add(this);
        }
    }
    
    public void removeJob(long jobID) {
        Job removedJob = new Job();
        
        for (Job job : appliedJobs) {
            if (job.getJobID() == jobID) {
                removedJob = job;
                appliedJobs.remove(job);
                break;
            }
        }
        
        List<Usertable> list = removedJob.getAppliedUsers();
        for (Usertable u : list) {
            if (Objects.equals(u.getUserid(), this.userid)) {
                list.remove(u);
                break;
            }   
        }

    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usertable)) {
            return false;
        }
        Usertable other = (Usertable) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "goOffer.entities.Usertable[ userid=" + userid + " ]";
    }
    
}
