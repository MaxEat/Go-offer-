/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.controllers;

import goOffer.ejbs.ViewingCounter;
import goOffer.ejbs.dealWithJobs;
import goOffer.ejbs.dealWithUsers;
import goOffer.entities.Job;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author jiahao pan
 */

@ManagedBean(name = "user_controller")
@SessionScoped
public class UserController implements Serializable{
    @EJB
    private dealWithJobs dealWithJobs;

    @EJB
    private dealWithUsers dealWithUsers;

    @EJB
    private ViewingCounter viewingCounter;

    
    public UserController() {

    }
    
    public void logout() {
        viewingCounter.lessViews();
        dealWithUsers.userLogout();
    }
    
    public List<Job> getAppliedJobsWithUsername(String username) {
        return dealWithUsers.findUserByUsername(username).getAppliedJobs();
    }
    
    public String deleteJob(String username) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        long deleteID = Long.parseLong(params.get("deleteJobID"));
        dealWithUsers.removeJobFromUserByUsername(username, deleteID);
//        dealWithJobs.deleteJobWithJobID(deleteID);
        return "jsf_user_overview.xhtml?faces-redirect=true";
    }

    public List<Job> getExpiredList() {
        return dealWithJobs.getExpiredJobs();
    }

    public void applyJob(String username, Job newJob) {
        dealWithUsers.addNewJobToUserByUsername(username, newJob);
    }    
    
}
