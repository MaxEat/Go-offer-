/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.ejbs;

import goOffer.entities.Company;
import goOffer.entities.Job;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jiahao pan
 */
@Stateful
@LocalBean
public class dealWithCompanies {

    @PersistenceContext(unitName = "goOffer-ejbPU")
    private EntityManager em;
    
    public Company getCompanyByUsername(String username) {
        Company result = (Company) em.createNamedQuery("Company.findByUsername")
                .setParameter("username", username)
                .getSingleResult();
        return result;
    }
    
    public boolean checkCompany(String username, String password){
        Company result = (Company) em.createNamedQuery("Company.checkCredential")
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();
        return result != null;
    }
        
    public void addJobToCompanyByUsername(String username, Job job){
        Company c = getCompanyByUsername(username);
        c.getJobs().add(job);
    }
    
    public void removeJobFromCompanyByUsername(String username, long jobID) {
        Company c = getCompanyByUsername(username);
        c.removeJobFromCompany(jobID);
    }
    
    public void setAddressByUserName(String address, String name) {
        Company company = (Company)em.find(Company.class ,1);
        company.setAddress(address);
    }
    
    public void setPopulationByUserName(int number, String name) {
        
        Company company = (Company)em.find(Company.class ,1);
        company.setPopulation(number);
    }

    public void persist(Object object) {
        em.persist(object);
    }

    
}
