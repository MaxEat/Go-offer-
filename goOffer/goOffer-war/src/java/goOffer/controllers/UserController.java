/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.controllers;

import goOffer.ejbs.dealWithUsers;
import goOffer.entities.Job;
import java.io.Serializable;
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

@ManagedBean(name = "user_overview")
@SessionScoped
public class UserController implements Serializable{
    
    dealWithUsers dealWithUsers = lookupdealWithUsersBean();
    
    public UserController() {

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



    private dealWithUsers lookupdealWithUsersBean() {
        try {
            Context c = new InitialContext();
            return (dealWithUsers) c.lookup("java:global/goOffer/goOffer-ejb/dealWithUsers!goOffer.ejbs.dealWithUsers");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    
}