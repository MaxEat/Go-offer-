/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.controllers;

import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author max
 */
@Named("messageModel")
@RequestScoped
public class MessageModel {
    private String msgText;
    private Date interviewDate;

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }
    
    
    public String getMsgText() {
        return msgText;
    }


    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }
    
}
