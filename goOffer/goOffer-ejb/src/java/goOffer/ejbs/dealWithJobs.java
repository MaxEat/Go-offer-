/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.ejbs;

import goOffer.entities.Job;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jiahao pan
 */
@Stateless
@LocalBean
public class dealWithJobs {

    @PersistenceContext(unitName = "goOffer-ejbPU")
    private EntityManager em;
    
    List<Job> expiredList;
    
    public void deleteJobWithJobID(long jobID)
    {
        em.createNamedQuery("Job.deleteByID")
                .setParameter("jobID", jobID)
                .executeUpdate();
    }

    public void persist(Object object) {
        em.persist(object);
    }

    
    
    public List<Job> getExpiredJobs() {
        return expiredList;
    }
    
    public void  refreshExpiredJobs() 
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	System.out.println(dateFormat.format(date)); 
        
        expiredList =  em.createNamedQuery("Job.findJobsBefore")
                .setParameter("expirationDate", date)
                .getResultList();
       
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
