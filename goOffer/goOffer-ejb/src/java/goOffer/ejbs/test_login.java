/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.ejbs;

import goOffer.entities.Logintest;
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
public class test_login {

    @PersistenceContext(unitName = "goOffer-ejbPU")
    private EntityManager em;

    public boolean loginControl(String username, String password) {
        try {
            Logintest l = em.createNamedQuery("Logintest.control", Logintest.class).setParameter("username", username).setParameter("password", password).getSingleResult();
            if (l != null) {
                return true;
            }
            return false;
        } catch(Exception e)  {
            return false;
        }
    }

    public void persist(Object object) {
        em.persist(object);
    }

}
