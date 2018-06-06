/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.ejbs;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author max
 */
@Stateless
@LocalBean
public class reminderSessionBean {

    @EJB
    private dealWithJobs dealWithJobs;


    @Schedule(dayOfWeek = "Mon-Sun", month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "*", second = "0")
    
    public void myTimer() {
        dealWithJobs.refreshExpiredJobs();
        System.out.println("Timer event: " + new Date());
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
