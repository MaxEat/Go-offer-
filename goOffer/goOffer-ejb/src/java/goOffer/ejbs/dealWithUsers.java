/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.ejbs;

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
    
     // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List getAllUsers() {
        List userList = em.createNamedQuery("Usertable.findAll").getResultList();
        return userList;
    }
    
    public void addNewUser(Usertable newUser) {
        em.persist(newUser);
    }

   

    public void persist(Object object) {
        em.persist(object);
    }

    
    
}
