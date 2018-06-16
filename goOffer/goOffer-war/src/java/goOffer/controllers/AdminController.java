/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.controllers;

import goOffer.Rest.entities.Ad;
import goOffer.Rest.entities.Message;
import goOffer.clientREST.AdClient;
import goOffer.clientREST.MessageClient;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author jiahao pan
 */
@ManagedBean(name = "admin_controller")
@SessionScoped
public class AdminController implements Serializable{
    
    private MessageClient messageClient = new MessageClient();
    private AdClient adClient = new AdClient();

    private Message message;

    private List<Message> messageList;
    private Ad currentAd;
    private String currentAdString;
    private String newAdContent = "";
    private String adID;

    public String getNewAdContent() {
        return newAdContent;
    }

    public void setNewAdContent(String newAdContent) {
        this.newAdContent = newAdContent;
    }
    private List<Ad> adList;

    public String getCurrentAd() {
        adID = adClient.countREST();
        Ad response = adClient.find_XML(Ad.class, "1");
        setCurrentAd(response);
        currentAdString = response.getAdcontent();
        return currentAdString;
//        return adClient.findAdWithMaxID();
    }

    public void setCurrentAd(Ad currentAd) {
        this.currentAd = currentAd;
    }

    public List<Ad> getAdLists() {
        GenericType<List<Ad>> gt = new GenericType<List<Ad>>() {
        };
        this.setAdLists(adClient.findAll_XML(gt));
        return adList;
    }

    public void setAdLists(List<Ad> adList) {
        this.adList = adList;
    }
//    

    public String addNewAd() {
        Ad newAd = new Ad();
        newAd.setAdcontent(this.newAdContent);
        adClient.create_JSON(newAd);
        return "jsf_ad_overview.xhtml?faces-redirect=true";
    }

    public void updateAd() {
        Ad newAd = new Ad();
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
    
}
