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
import goOffer.ejbs.dealWithJobs;
import goOffer.ejbs.dealWithUsers;
import goOffer.entities.Job;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author jiahao pan
 */
@ManagedBean(name = "user_controller")
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private dealWithJobs dealWithJobs;

    @EJB
    private dealWithUsers dealWithUsers;

    @EJB
    private ViewingCounter viewingCounter;

    private int currentViews;
    private List<Job> allJobs;
    private List<Job> appliedJobs;

    private MessageClient messageClient = new MessageClient();
    private AdvertisementClient adClient = new AdvertisementClient();

    private Message message;

    private List<Message> messageList;
    private Advertisement currentAd;
    private String currentAdString;
    private String newAdContent = "";
    private String adID;

    public String getNewAdContent() {
        return newAdContent;
    }

    public void setNewAdContent(String newAdContent) {
        this.newAdContent = newAdContent;
    }
    private List<Advertisement> adList;

    public String getCurrentAd() {
        adID = adClient.countREST();
        Advertisement response = adClient.find_XML(Advertisement.class, "1");
        setCurrentAd(response);
        currentAdString = response.getAdcontent();
        return currentAdString;
//        return adClient.findAdWithMaxID();
    }

    public void setCurrentAd(Advertisement currentAd) {
        this.currentAd = currentAd;
    }

    public List<Advertisement> getAdLists() {
        GenericType<List<Advertisement>> gt = new GenericType<List<Advertisement>>() {
        };
        this.setAdLists(adClient.findAll_XML(gt));
        return adList;
    }

    public void setAdLists(List<Advertisement> adList) {
        this.adList = adList;
    }
//    

    public String addNewAd() {
        Advertisement newAd = new Advertisement();
        newAd.setAdcontent(this.newAdContent);
        adClient.create_JSON(newAd);
        return "jsf_ad_overview.xhtml?faces-redirect=true";
    }

    public void updateAd() {
        Advertisement newAd = new Advertisement();
        newAd.setAdcontent(this.newAdContent);
        newAd.setId(1);
        adClient.edit_JSON(newAd, "1");
        addNewAd();
//        return "jsf_ad_overview.xhtml?faces-redirect=true";
    }

    public List<Message> getMessageList() {
        GenericType<List<Message>> gt = new GenericType<List<Message>>() {
        };
        this.setMessageList(messageClient.findAll_XML(gt));
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public Message getMessage() {
        Message response = messageClient.find_XML(Message.class, "1");
        setMessage(response);
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public UserController() {

    }

    public void logout() {
        viewingCounter.lessViews();
        dealWithUsers.userLogout();
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
        return "jsf_user_overview.xhtml?faces-redirect=true";
    }

    public String unapplyJob(String username, Job job) {
        dealWithUsers.removeJobFromUserByUsername(username, job);
        return "jsf_user_overview.xhtml?faces-redirect=true";
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
