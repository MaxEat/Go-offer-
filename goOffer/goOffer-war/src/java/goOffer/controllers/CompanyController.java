/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.controllers;

import goOffer.ejbs.dealWithCompanies;
import goOffer.ejbs.dealWithJobs;
import goOffer.ejbs.dealWithUsers;
import goOffer.entities.Company;
import goOffer.entities.Job;
import goOffer.entities.Job.jobType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
public class CompanyController implements Serializable {

    @EJB
    private dealWithJobs dealWithJobs;

    @EJB
    private dealWithUsers dealWithUsers;

    @EJB
    private dealWithCompanies dealWithCompanies;


    private Boolean addButtonShown = true;
    private Boolean addFormShown = false;

    private jobType[] types;

    private String jobName;
    private String jobLocation;
    private String jobDescription;
    private Date jobExpirationDate;
    private jobType chosenType;
    
    public CompanyController(){
        types = jobType.values();
        
    }
    
    public List<Job> getJobsByUsername(String username) {
        return dealWithCompanies.getCompanyByUsername(username).getJobs();
    }
    
    public String getCompanyNameByUsername(String username) {
        return dealWithCompanies.getCompanyByUsername(username).getCompanyName();
    }
    
    public String getCompanyAddressByUsername(String username) {
        return dealWithCompanies.getCompanyByUsername(username).getAddress();
    }
            
            
    public String addButtonListener() {
        addButtonShown = false;
        addFormShown = true;
        return "classified/jsf_company_overview.xhtml?faces-redirect=true";
    }

    public String addNewJob(String username) {
        addButtonShown = true;
        addFormShown = false;

        Job newjob = new Job();
        newjob.setDescription(jobDescription);
        newjob.setLocation(jobLocation);
        newjob.setJobName(jobName);
        newjob.setExpirationDate(jobExpirationDate);
        newjob.setType(chosenType);
        
        dealWithCompanies.addJobToCompanyByUsername(username, newjob);

        return "classified/jsf_company_overview.xhtml?faces-redirect=true";
    }

    public String deleteJob(String username) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        long deleteID = Long.parseLong(params.get("deleteJobID"));
        dealWithCompanies.removeJobFromCompanyByUsername(username, deleteID);
        dealWithJobs.deleteJobWithJobID(deleteID);

        return "classified/jsf_company_overview.xhtml?faces-redirect=true";
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

    public String initialAddressAndPeople(String companyName, String address, int population, String username) {
   //     dealWithCompanies.setCompanyNameByUserName(companyName, username);
        dealWithCompanies.setAddressByUserName(address, username);
        dealWithCompanies.setPopulationByUserName(population, username);
        return "returnFromCompanyFlow";
    }

}
