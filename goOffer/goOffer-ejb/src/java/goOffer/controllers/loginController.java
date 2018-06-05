/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.controllers;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author jiahao pan
 */
@ManagedBean(name = "userlogin")
@SessionScoped

public class loginController implements Serializable {

    private String username;
    private String password;
    private String result;

    public String callWebServiceLogin() {
        result = login(username, password);
        if(result.equals("user exist")){
            return "jsf_user_overview.xhtml";
        }
        else if(result.equals("company exist")){
            return "jsf_company_overview.xhtml";
        }
        else {
            return "test_login.xhtml";
        }
   
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    
    public String callWebServiceRegister() {
        result = register(username, password);
        if(result.equals("user exist")){
            return "test_login";
        }
        else
        {
            return "jsf_user_overview";
        }
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

}
