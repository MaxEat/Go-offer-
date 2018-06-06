/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.controllers;

import goOffer.ejbs.dealWithUsers;
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
@ManagedBean(name = "userlogin")
@SessionScoped
public class loginController implements Serializable {

    dealWithUsers dealWithUsers = lookupdealWithUsersBean();

    private String username;
    private String password;
    private String result;

    public String callWebServiceLogin() {
        result = login(username, password);
        if ((login(username, password)).equals("exist")) {
            return "jsf_company_overview.xhtml?faces-redirect=true";
        } else {
            return "test_login.xhtml?faces-redirect=true";
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    
    public void callWebServiceRegister() {
        result = register(username, password);
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

    private static String login(java.lang.String name, java.lang.String password) {
        try {
            xfiredemo.liuxiang.com.helloservice.LoginService service = new xfiredemo.liuxiang.com.helloservice.LoginService();
            xfiredemo.liuxiang.com.helloservice.LoginServicePortType port = service.getLoginServiceHttpPort();
            return port.login(name, password);
        } catch (Exception ex) {
            return ex.toString();
        }

    }

    private static String register(java.lang.String name, java.lang.String password) {
        try {

            xfiredemo.liuxiang.com.helloservice.LoginService service = new xfiredemo.liuxiang.com.helloservice.LoginService();
            xfiredemo.liuxiang.com.helloservice.LoginServicePortType port = service.getLoginServiceHttpPort();
            return port.register(name, password);
        } catch (Exception ex) {
            return ex.toString();
        }
    }

    private dealWithUsers lookupdealWithUsersBean() {
        try {
            Context c = new InitialContext();
            return (dealWithUsers) c.lookup("java:global/goOffer/goOffer-ejb/dealWithUsers!goOffer.ejbs.dealWithUsers");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
