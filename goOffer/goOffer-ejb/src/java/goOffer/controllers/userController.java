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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author jiahao pan
 */

@ManagedBean(name = "user_overview")
@SessionScoped
public class userController implements Serializable{

    dealWithUsers dealWithUsers = lookupdealWithUsersBean();
    
    private List<Job> appliedJobs;
    private final String currentUserName;
    
    public userController() {
        appliedJobs = dealWithUsers.findUserByUserID(1).getAppliedJobs();
        currentUserName = dealWithUsers.findUserByUserID(1).getUsername();
    }
    
    public String deleteJob() {
        return "jsf_user_overview.xhtml?faces-redirect=true";
    }

    public List<Job> getAppliedJobs() {
        return appliedJobs;
    }

    public void setAppliedJobs(List<Job> appliedJobs) {
        this.appliedJobs = appliedJobs;
    }

    public String getCurrentUserName() {
        return currentUserName;
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