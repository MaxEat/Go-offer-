/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.controllers;

import goOffer.Rest.entities.Advertisement;
import goOffer.Rest.entities.Message;
import goOffer.clientREST.AdvertisementClient;
import goOffer.clientREST.MessageClient;
import goOffer.ejbs.ViewingCounter;
import goOffer.ejbs.dealWithInterviews;
import goOffer.ejbs.dealWithJobs;
import goOffer.ejbs.dealWithUsers;
import goOffer.entities.Interview;
import goOffer.entities.Job;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.jms.MessageListener;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author jiahao pan
 */
@ManagedBean(name = "user_controller")
@SessionScoped
public class UserController implements Serializable{

    @EJB
    private dealWithInterviews dealWithInterviews;
    @EJB
    private dealWithJobs dealWithJobs;

    @EJB
    private dealWithUsers dealWithUsers;

    @EJB
    private ViewingCounter viewingCounter;

    private int currentViews;
    private List<Job> allJobs;
    private List<Job> appliedJobs;

    public UserController() {

    }

    public void logout() {
        viewingCounter.lessViews();
        dealWithUsers.userLogout();
    }

    
    public List<Interview> getInterviews(String username) {
        return dealWithInterviews.getInterviewByUsername(username);
    }
    public List<Job> getAppliedJobsWithUsername(String username) {
       
        currentViews = viewingCounter.getViews();
        allJobs = dealWithJobs.getAllJobs();
        appliedJobs = dealWithUsers.findUserByUsername(username).getAppliedJobs();
        return appliedJobs;
    }

    public boolean decideApplyVisable(long jobID) {
        for (Job job : appliedJobs) {
            if (job.getJobID() == jobID) {
                return false;
            }
        }
        return true;
    }

    public boolean decideUnApplyVisable(long jobID) {
        for (Job job : appliedJobs) {
            if (job.getJobID() == jobID) {
                return true;
            }
        }
        return false;
    }

    public List<Job> getExpiredList() {
        return dealWithJobs.getExpiredJobs();
    }

    public String applyJob(String username, Job newJob) {
        dealWithUsers.addNewJobToUserByUsername(username, newJob);
        return "classified/jsf_user_overview.xhtml?faces-redirect=true";
    }    
        
    public String unapplyJob(String username, Job job){
        dealWithUsers.removeJobFromUserByUsername(username, job);
        return "classified/jsf_user_overview.xhtml?faces-redirect=true";
    }
    public int getCurrentViews() {
        return currentViews;
    }

    public List<Job> getAllJobs() {
        return allJobs;
    }

    public List<Job> getAppliedJobs() {
        return appliedJobs;
    }

}
