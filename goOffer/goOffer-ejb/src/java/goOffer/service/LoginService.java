/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.service;

import goOffer.ejbs.dealWithUsers;
import goOffer.entities.Usertable;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author jiahao pan
 */
@WebService(serviceName = "LoginService", portName = "LoginServiceHttpPort", endpointInterface = "xfiredemo.liuxiang.com.helloservice.LoginServicePortType", targetNamespace = "http://com.liuxiang.xfireDemo/HelloService", wsdlLocation = "META-INF/wsdl/LoginService/loginRegister.wsdl")
@Stateless
public class LoginService {

    @EJB
    private dealWithUsers dealWithUsers;

    public java.lang.String register(java.lang.String name, java.lang.String password) {
        Usertable user = new Usertable(name, password);
        dealWithUsers.addNewUser(user);
        java.lang.String finish = "finish";
        return finish;
    }

    public java.lang.String login(java.lang.String name, java.lang.String password) {
       if( dealWithUsers.checkUser(name, password))
            return "exist";
        else
            return "not exist";
    }
    
}
