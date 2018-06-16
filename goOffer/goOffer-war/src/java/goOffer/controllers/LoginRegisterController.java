/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.controllers;

import goOffer.ejbs.ViewingCounter;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author jiahao pan
 */
@ManagedBean(name = "login_overview")
@SessionScoped
public class LoginRegisterController implements Serializable {

    @EJB
    private ViewingCounter viewingCounter;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_53547/LoginRegisterService/LoginRegisterService.wsdl")
    private LoginRegisterService_Service service;

    private String username;
    private String password;
    private String result;
    private String identity;

    public LoginRegisterController() {
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

    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("jsf_user_login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginRegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isLoggedIn() {
        return username != null;
    }
    
    public String isLoggedInForward() {
        if (isLoggedIn()) {
            if ("user".equals(identity)) {
                return "jsf_user_overview.xhtml?redirect=true";
            }
            if ("company".equals(identity)) {
                return "jsf_company_overview.xhtml?redirect=true";
            }
        }
        return null;
    }

    public String callWebServiceLogin() {
        if (loginUser(username, password)) {
            viewingCounter.moreViews();
            identity = "user"; 
            return "jsf_user_overview.xhtml?redirect=true";
        }
        if (loginCompany(username, password)) {
            identity = "company"; 
            return "jsf_company_overview.xhtml?redirect=true";
        } else {
            return "test_error.xhtml";
        }
    }

    public String callWebServiceRegisterUser() {
        if (registerUser(username, password).equals("user exsit")) {
            return "jsf_user_register.xhtml?redirect=true";
        } else {
            identity = "user"; 
            return "jsf_user_overview.xhtml?redirect=true";
        }

    }

    public String callWebServiceRegisterCompany() {
        if (registerCompany(username, password).equals("company exsit")) {
            return "jsf_company_overview.xhtml?redirect=true";
        } else {
            identity = "company"; 
            return "jsf_company_overview.xhtml?redirect=true";
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    private String registerCompany(java.lang.String username, java.lang.String password) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        goOffer.controllers.LoginRegisterService port = service.getLoginRegisterServicePort();
        return port.registerCompany(username, password);
    }

    private String registerUser(java.lang.String username, java.lang.String password) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        goOffer.controllers.LoginRegisterService port = service.getLoginRegisterServicePort();
        return port.registerUser(username, password);
    }

    private boolean loginCompany(java.lang.String username, java.lang.String password) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        goOffer.controllers.LoginRegisterService port = service.getLoginRegisterServicePort();
        return port.loginCompany(username, password);
    }

    private boolean loginUser(java.lang.String username, java.lang.String password) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        goOffer.controllers.LoginRegisterService port = service.getLoginRegisterServicePort();
        return port.loginUser(username, password);
    }

}
