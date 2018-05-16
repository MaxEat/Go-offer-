/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.controllers;
import goOffer.ejbs.test_login;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author jiahao pan
 */

@ManagedBean(name = "logintest")
@SessionScoped
public class loginController implements Serializable {

    test_login test_login = lookuptest_loginBean();
    
    private String username;
    private String password;
    
    public String loginControl() {
        if (test_login.loginControl(username, password)){
            return "test_home.xhtml?faces-redirect=true";
        }
        return "test_error.xhtml?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private test_login lookuptest_loginBean() {
        try {
            Context c = new InitialContext();
            return (test_login) c.lookup("java:global/goOffer/goOffer-ejb/test_login!goOffer.ejbs.test_login");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
