/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.controllers;

import goOffer.ejbs.dealWithCompanies;
import goOffer.ejbs.dealWithJobs;
import goOffer.entities.Company;
import goOffer.entities.Job;
import goOffer.entities.Job.jobType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author jiahao pan
 */
@ManagedBean(name = "company_overview")
@SessionScoped
public class companyController implements Serializable {

    dealWithCompanies dealWithCompanies = lookupdealWithCompaniesBean();
    dealWithJobs dealWithJobs = lookupdealWithJobsBean();

    private Boolean addButtonShown = true;
    private Boolean addFormShown = false;

    private List<Job> jobs;
    private jobType[] types;
    private String companyName;
    private String companyAddress;

    private String jobName;
    private String jobLocation;
    private String jobDescription;
    private Date jobExpirationDate;
    private jobType chosenType;
    
    public companyController(){
        jobs = dealWithCompanies.getCompanyByCompanyID(1).getJobs();
        types = jobType.values();
        companyName = dealWithCompanies.getCompanyByCompanyID(1).getCompanyName();
        companyAddress = dealWithCompanies.getCompanyByCompanyID(1).getAddress();
        
    }

    
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
    
    public jobType getChosenType() {
        return chosenType;
    }

    public void setChosenType(jobType chosenType) {
        this.chosenType = chosenType;
    }

    public String getJobName() {
        return jobName;
    }

    public jobType[] getTypes() {
        return types;
    }

    public void setTypes(jobType[] types) {
        this.types = types;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Date getJobExpirationDate() {
        return jobExpirationDate;
    }

    public void setJobExpirationDate(Date jobExpirationDate) {
        this.jobExpirationDate = jobExpirationDate;
    }

    public Boolean getAddFormShown() {
        return addFormShown;
    }

    public void setAddFormShown(Boolean addFormShown) {
        this.addFormShown = addFormShown;
    }

    public Boolean getAddButtonShown() {
        return addButtonShown;
    }

    public void setAddButtonShown(Boolean addButtonShown) {
        this.addButtonShown = addButtonShown;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public String addButtonListener() {
        addButtonShown = false;
        addFormShown = true;
        return "jsf_company_overview.xhtml?faces-redirect=true";
    }

    public String addNewJob() {
        addButtonShown = true;
        addFormShown = false;

        Job newjob = new Job();
        newjob.setDescription(jobDescription);
        newjob.setLocation(jobLocation);
        newjob.setJobName(jobName);
        newjob.setExpirationDate(jobExpirationDate);
        newjob.setType(chosenType);
        
        dealWithCompanies.addJobToCompanyByID(1, newjob);
        
        jobs = dealWithCompanies.getCompanyByCompanyID(1).getJobs();

        return "jsf_company_overview.xhtml?faces-redirect=true";
    }

    public String deleteJob() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        long deleteID = Integer.parseInt(params.get("deleteJobID"));
        dealWithCompanies.removeJobFromCompanyByID(1, deleteID);
        dealWithJobs.deleteJobWithJobID(deleteID);
        
        jobs = dealWithCompanies.getCompanyByCompanyID(1).getJobs();

        return "jsf_company_overview.xhtml?faces-redirect=true";
    }

    private dealWithJobs lookupdealWithJobsBean() {
        try {
            Context c = new InitialContext();
            return (dealWithJobs) c.lookup("java:global/goOffer/goOffer-ejb/dealWithJobs!goOffer.ejbs.dealWithJobs");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private dealWithCompanies lookupdealWithCompaniesBean() {
        try {
            Context c = new InitialContext();
            return (dealWithCompanies) c.lookup("java:global/goOffer/goOffer-ejb/dealWithCompanies!goOffer.ejbs.dealWithCompanies");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
