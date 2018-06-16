/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.ejbs;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import goOffer.entities.Company;
import goOffer.entities.Interview;
import goOffer.entities.Job;
import goOffer.entities.Usertable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author max
 */
@Stateless
@LocalBean
public class dealWithInterviews{

    @PersistenceContext(unitName = "goOffer-ejbPU")
    private EntityManager em;
        
    public void addInterviewToUser(long jobID, String username, String  date) {
       
        Interview interview = new Interview(jobID, username);
        em.persist(interview);
        interview.setInterview_date(date);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<Interview> getInterviewByUsername(String username) {
        List<Interview> interviews =  em.createNamedQuery("Interview.findByUsername")
                .setParameter("username", username)
                .getResultList();
        return interviews;
    }
    public void persist(Object object) {
        em.persist(object);
    }
}
