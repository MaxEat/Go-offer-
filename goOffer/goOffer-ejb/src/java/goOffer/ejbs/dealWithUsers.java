/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.ejbs;

import goOffer.entities.Job;
import goOffer.entities.Usertable;
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
public class dealWithUsers {

    @PersistenceContext(unitName = "goOffer-ejbPU")
    private EntityManager em;
    

    public List getAllUsers() {
        List userList = em.createNamedQuery("Usertable.findAll").getResultList();
        return userList;
    }
    
    public void addNewUser(Usertable newUser) {
        em.persist(newUser);
    }
    
    public boolean checkUser(String username, String password){
        Usertable result = (Usertable) em.createNamedQuery("Usertable.checkCredential")
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();
        return result != null;
    }
    
    public Usertable findUserByUserID(long userID){
        return em.find(Usertable.class, userID);
    }

    public void addNewJobToUserByUserID(long userID, Job newJob) {
        Usertable u = findUserByUserID(userID);
        u.addJob(newJob);
    }
    
    public void removeJobFromUserByID(long userID, long jobID) {
        
    }

    public void persist(Object object) {
        em.persist(object);
    }

    
    
}
