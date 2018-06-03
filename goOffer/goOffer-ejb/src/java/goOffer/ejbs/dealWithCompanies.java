/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.ejbs;

import goOffer.entities.Company;
import goOffer.entities.Job;
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
public class dealWithCompanies {

    @PersistenceContext(unitName = "goOffer-ejbPU")
    private EntityManager em;
    
    public Company getCompanyByCompanyID(long companyID) {
        
          return em.find(Company.class, companyID);
    }
    
    public void addJobToCompanyByID(long companyID, Job job){
        Company c = getCompanyByCompanyID(companyID);
        c.getJobs().add(job);
    }
    
    public void removeJobFromCompanyByID(long companyID, long jobID) {
        Company c = getCompanyByCompanyID(companyID);
        c.removeJobFromCompany(jobID);
    }
    
    

    public void persist(Object object) {
        em.persist(object);
    }

    
}
