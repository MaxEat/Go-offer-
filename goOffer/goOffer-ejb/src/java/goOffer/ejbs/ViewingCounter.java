/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.ejbs;

import javax.ejb.Singleton;

/**
 *
 * @author jiahao pan
 */

@Singleton
public class ViewingCounter {
    private static int views = 0;
    
    public int getViews() {
        return views;
    }
    
    public void lessViews() {
        if (views > 0) views--;
    }
    
    public void moreViews() {
        views++;
    }
}
