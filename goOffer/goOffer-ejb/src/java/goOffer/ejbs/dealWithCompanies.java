/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.ejbs;

import goOffer.entities.Company;
import goOffer.entities.Job;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.StatefulTimeout;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jiahao pan
 */
@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 30)
@LocalBean
public class dealWithCompanies {

    @PersistenceContext(unitName = "goOffer-ejbPU")
    private EntityManager em;

    @Remove
    public void remove() {
    }

    public Company getCompanyByUsername(String username) {
        Company result = (Company) em.createNamedQuery("Company.findByUsername")
                .setParameter("username", username)
                .getSingleResult();
        return result;
    }

    public boolean checkCompanyName(String username) {
        List result = em.createNamedQuery("Company.findByUsername")
                .setParameter("username", username)
                .getResultList();
        return !result.isEmpty();
    }

    public boolean checkCompany(String username, String password) {
        List result = em.createNamedQuery("Company.checkCredential")
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList();

        return !result.isEmpty();

    }

    public void addJobToCompanyByUsername(String username, Job job) {
        Company c = getCompanyByUsername(username);
        c.getJobs().add(job);
    }

    public void removeJobFromCompanyByUsername(String username, long jobID) {
        Company c = getCompanyByUsername(username);
        c.removeJobFromCompany(jobID);
    }

    public void setAddressByUserName(String address, String name) {
       
        Company c = getCompanyByUsername(name);
        c.setAddress(address);
//        em.find(Company, name)
//        em.createNamedQuery("Company.updateAddress")
//                .setParameter("address", address)
//                .setParameter("username", name)
//                .executeUpdate();
      
    }

    public void setPopulationByUserName(int number, String name) {
                
        Company c = getCompanyByUsername(name);
        c.setPopulation(number);
      
    }

    public void setCompanyNameByUserName(String companyName, String username) {
        
        Company c = getCompanyByUsername(username);
        c.setCompanyName(companyName);
        
    }

    public void addNewCompany(String username, String password) {
        Company company = new Company(username, password);

        em.persist(company);
    }

    public void persist(Object object) {
        em.persist(object);
    }

}
