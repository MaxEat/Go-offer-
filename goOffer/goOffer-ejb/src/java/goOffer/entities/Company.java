/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.entities;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jiahao pan
 */
@Entity
@Table(name = "Company")
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT u FROM Company u")
    , @NamedQuery(name = "Company.checkCredential", query = "SELECT u FROM Company u WHERE u.username = :username AND u.password = :password")
    , @NamedQuery(name = "Company.findByUsername", query = "SELECT u FROM Company u WHERE u.username = :username")
    , @NamedQuery(name = "Company.findByID", query = "SELECT u FROM Company u WHERE u.id = :id")
    , @NamedQuery(name = "Company.updateAddress", query = "UPDATE Company u SET u.address = :address WHERE u.username = :username")    
    , @NamedQuery(name = "Company.updateCompanyName", query = "UPDATE Company u SET u.companyName = :companyName WHERE u.username = :username")
    , @NamedQuery(name = "Company.updatePopulation", query = "UPDATE Company u SET u.population = :population WHERE u.username = :username")})
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String companyName;
    @Column
    private String address;
    @Column
    private int population;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Job> jobs;

    public Company() {
    }

    public Company(String name, String pass) {
        username = name;
        password = pass;
    }
    
    public void removeJobFromCompany(long jobID) {
        for (Iterator<Job> iter = jobs.listIterator(); iter.hasNext();) {
            Job j = iter.next();
            if (j.getJobID() == jobID) {
                iter.remove();
            }
        }
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
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
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "goOffer.entities.Company[ id=" + id + " ]";
    }

}
