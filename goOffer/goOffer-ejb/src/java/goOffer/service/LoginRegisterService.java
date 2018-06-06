/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.service;

import goOffer.ejbs.dealWithCompanies;
import goOffer.ejbs.dealWithUsers;
import goOffer.entities.Company;
import goOffer.entities.Usertable;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author jiahao pan
 */
@WebService(serviceName = "LoginRegisterService", portName = "LoginRegisterServiceHttpPort", endpointInterface = "xfiredemo.liuxiang.com.helloservice.LoginRegisterServicePortType", targetNamespace = "http://com.liuxiang.xfireDemo/HelloService", wsdlLocation = "META-INF/wsdl/LoginRegisterService/loginRegister.wsdl")
@Stateless
public class LoginRegisterService {

    @EJB
    private dealWithCompanies dealWithCompanies;

    @EJB
    private dealWithUsers dealWithUsers;

    public java.lang.String register(java.lang.String name, java.lang.String password) {

        if (dealWithUsers.checkUserName(name)) {
            return "user exist";
        } else {
            Usertable user = new Usertable(name, password);
            dealWithUsers.addNewUser(user);
            return "user created";
        }
    }

    public java.lang.String login(java.lang.String name, java.lang.String password) {

        if (dealWithUsers.checkUser(name, password)) {
            return "user exist";
        } else if (dealWithCompanies.checkCompany(name, password)) {
            return "company exist";
        } else {
            return "not exist";
        }
    }

    /**
     * Web 服务操作
     */
    public java.lang.String registerCompany(java.lang.String username, java.lang.String password) {

        if (dealWithCompanies.checkCompanyName(username)) {
            return "company exist";
        } else {
            Company company = new Company(username, password);
            dealWithCompanies.addNewCompany(company);
            return "company created";
        }
    }

}
