/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.ejbs;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author max
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "mytopic")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MessageReceiver implements MessageListener {

    @EJB
    private dealWithInterviews dealWithInterviews;

    
    public MessageReceiver() {
    }
    
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
           try {
               String text = textMessage.getText();
               String[] text_array = text.split(",");
               String date = text_array[0]; 
               String username = text_array[1];
               String jobid = text_array[2];
               dealWithInterviews.addInterviewToUser(Integer.parseInt(jobid), username, date);
               System.out.println("received message: " +
                   textMessage.getText());
               
           } catch (JMSException ex) {
               Logger.getLogger(MessageReceiver.class.getName()).log(
                   Level.SEVERE, null, ex);
           }
           
         
        //dealWithUsers.newJobsPopsMessage();
    

       
    }
    
}
