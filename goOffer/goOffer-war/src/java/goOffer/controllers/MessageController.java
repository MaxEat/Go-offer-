/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.controllers;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 *
 * @author max
 */
@Named("messageController")
@RequestScoped
public class MessageController {

    @Resource(mappedName = "mytopic")
    private Queue mytopic;

    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;




    @Inject
    private MessageModel messageModel;
    
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    
    public String sendMsg(String username, Long jobID) {
        result = "successfully added";
        sendJMSMessageToMytopic(messageModel.getMsgText() + " " + username + " " + String.valueOf(jobID));
        return "jsf_company_overview_applicants.xhtml";
       }


    private void sendJMSMessageToMytopic(String messageData) {
        context.createProducer().send(mytopic, messageData);
    }
    
}
