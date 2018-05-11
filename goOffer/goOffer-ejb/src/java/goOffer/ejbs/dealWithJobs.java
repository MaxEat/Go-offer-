/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.ejbs;

import goOffer.entities.Job;
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
    
    public List getAllJobsByCompanyID(long companyID) {
        List jobList = em.createNamedQuery("Job.findByCompany")
                .setParameter("companyID", companyID)
                .getResultList();
        return jobList;
    }
    
    public void addNewJob(Job newJob) {
        em.persist(newJob);
    }
    
    public void deleteJobWithJobID(long jobID)
    {
        em.createNamedQuery("Job.deleteByID")
                .setParameter("jobID", jobID)
                .executeUpdate();
    }

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
