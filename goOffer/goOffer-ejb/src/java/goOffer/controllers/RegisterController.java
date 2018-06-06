/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.controllers;

import goOffer.ejbs.reminderSessionBean;
import goOffer.ejbs.dealWithCompanies;
import goOffer.entities.Company;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.flow.FlowScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author max
 */
@ManagedBean(name = "register_company")
@SessionScoped
public class RegisterController implements Serializable{

    dealWithCompanies dealWithCompanies = lookupdealWithCompaniesBean();
    private String username;
    private String password;
 

    

    public String addCompany() {
        Company company = new Company(username, password);
        dealWithCompanies.persist(company);
        return "jsf_company_register_profile";
    }
       
    private dealWithCompanies lookupdealWithCompaniesBean() {
        try {
            Context c = new InitialContext();
            return (dealWithCompanies) c.lookup("java:global/goOffer/goOffer-ejb/dealWithCompanies!goOffer.ejbs.dealWithCompanies");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
//    private String companyName;
//    private String result;
//    private int population;
//    private String address;
//
//    public int getPopulation() {
//        return population;
//    }
//
//    public String getCompanyName() {
//        return companyName;
//    }
//
//    public void setCompanyName(String companyName) {
//        this.companyName = companyName;
//    }
//
//    public void setPopulation(int population) {
//        this.population = population;
//    }
//   
//
//    
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getResult() {
//        return result;
//    }
//
//    public void setResult(String result) {
//        this.result = result;
//    }
    

    
//    public String setAddressToDB() {
//        dealWithCompanies.setAddressByUserName(address, username);
//        return "jsf_company_register_profile2";
//    }
//    
//    public String setPopulationToDB() {
//        dealWithCompanies.setPopulationByUserName(population, username);
//        return "jsf_company_register_confirmation";
//    }
//    
//    public void setAddress(String address) {
//        this.address = address;
//    }
//    
//    public String getAddress() {
//        return address;
//    }
    
    

    
}
