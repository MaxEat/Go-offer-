/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.ejbs;

import javax.ejb.ConcurrencyManagement;
import static javax.ejb.ConcurrencyManagementType.CONTAINER;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import static javax.ejb.LockType.READ;
import javax.ejb.Startup;
import static javax.ejb.LockType.WRITE;

/**
 *
 * @author jiahao pan
 */
@Startup
@Singleton
@ConcurrencyManagement(CONTAINER)
@LocalBean
public class ViewingCounter {
    private int views = 0;
    
    @Lock(READ)
    public int getViews() {
        return views;
    }
    
    @Lock(WRITE)
    public void lessViews() {
        if (views > 0) views--;
    }
    
    @Lock(WRITE)
    public void moreViews() {
        views++;
    }
}
