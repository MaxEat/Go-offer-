/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.controllers;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author jiahao pan
 */
@ManagedBean(name = "login_overview")
@SessionScoped
public class LoginRegisterController implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/LoginRegisterService/LoginRegisterService.wsdl")
    private LoginRegisterService_Service service;


    private String username;
    private String password;
    private String result;

  
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

 
    
    public String callWebServiceLogin() {
        if(loginUser(username, password))
            return "jsf_user_overview.xhtml?redirect=true";
        if(loginCompany(username, password))
            return "jsf_company_overview.xhtml?redirect=true";
        else
            return "test_error.xhtml";
    }

    public String callWebServiceRegisterUser() {
        if(registerUser(username, password).equals("user exsit")){
            return "jsf_user_register.xhtml?redirect=true";
        }
        else
            return "jsf_user_overview.xhtml?redirect=true";
    }
        
    public String callWebServiceRegisterCompany() {
        if(registerCompany(username, password).equals("company exsit")){
            return "jsf_company_overview.xhtml?redirect=true";
        }
        else
            return "jsf_company_overview.xhtml?redirect=true";
    }
    

    
    
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    private boolean loginUser(java.lang.String username, java.lang.String password) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        goOffer.controllers.LoginRegisterService port = service.getLoginRegisterServicePort();
        return port.loginUser(username, password);
    }

    private boolean loginCompany(java.lang.String username, java.lang.String password) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        goOffer.controllers.LoginRegisterService port = service.getLoginRegisterServicePort();
        return port.loginCompany(username, password);
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










    


    
}
