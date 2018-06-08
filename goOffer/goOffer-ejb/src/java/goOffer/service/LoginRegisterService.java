/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.service;

import goOffer.ejbs.dealWithCompanies;
import goOffer.ejbs.dealWithUsers;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author max
 */
@WebService(serviceName = "LoginRegisterService")
@Stateless()
public class LoginRegisterService {

    @EJB
    private dealWithCompanies dealWithCompanies;

    @EJB
    private dealWithUsers dealWithUsers;


    /**
     * Web 服务操作
     */
    @WebMethod(operationName = "loginUser")
    public boolean loginUser(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        return dealWithUsers.checkUser(username, password);
  
    }

    /**
     * Web 服务操作
     */
    @WebMethod(operationName = "loginCompany")
    public boolean loginCompany(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        return dealWithCompanies.checkCompany(username, password);
    }

    /**
     * Web 服务操作
     */
    @WebMethod(operationName = "registerUser")
    public String registerUser(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
              
        if (dealWithUsers.checkUserName(username)) {
            return "user exist";
        } else {
            
            dealWithUsers.addNewUser(username, password);
            return "user created";
        }
    }

    /**
     * Web 服务操作
     */
    @WebMethod(operationName = "registerCompany")
    public String registerCompany(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
              
        if (dealWithCompanies.checkCompanyName(username)) {
            return "company exist";
        } else {
            
            dealWithCompanies.addNewCompany(username, password);
            return "company created";
        }
    }
}
