/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.ejbs;

import goOffer.entities.Job;
import goOffer.entities.Usertable;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
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
public class dealWithUsers {

    @EJB
    private ViewingCounter viewingCounter;

    @PersistenceContext(unitName = "goOffer-ejbPU")
    private EntityManager em;
   

    public List getAllUsers() {
        List userList = em.createNamedQuery("Usertable.findAll").getResultList();
        return userList;
    }
    
    public void addNewUser(String username, String password) {
        Usertable user = new Usertable(username, password);
        em.persist(user);
    }
    
     @Remove
    public void userLogout() {
    }

    public boolean checkUser(String username, String password) {
        List result =  em.createNamedQuery("Usertable.checkCredential")
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList();
        return !result.isEmpty();
    }

    public boolean checkUserName(String username) {
        List result = em.createNamedQuery("Usertable.findByUsername")
                .setParameter("username", username)
                .getResultList();
        return !result.isEmpty();
    }
    public Usertable findUserByUsername(String username) {
        Usertable result =  (Usertable)em.createNamedQuery("Usertable.findByUsername")
                .setParameter("username", username)
                .getSingleResult();
        return result;
    }

    public void addNewJobToUserByUsername(String username, Job newJob) {
        Usertable u = findUserByUsername(username);
        System.out.print(u.toString());
        u.addJob(newJob);
    }

    public void removeJobFromUserByUsername(String username, long jobID) {
        Usertable u = findUserByUsername(username);
        u.removeJob(jobID);
    }

    public void persist(Object object) {
        em.persist(object);
    }
    

}
