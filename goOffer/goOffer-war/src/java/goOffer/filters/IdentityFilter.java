/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goOffer.filters;

import goOffer.controllers.LoginRegisterController;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jiahao pan
 */

public class IdentityFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException { 
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        LoginRegisterController lrc = (LoginRegisterController)request.getSession().getAttribute("login_overview");
        String loginURL = request.getContextPath() + "/jsf_user_login.xhtml";
//        String logoutURL = request.getContextPath() + "/jsf_logout.xhtml";
        boolean loginRequest = request.getRequestURI().equals(loginURL);
//        boolean logoutRequest = request.getRequestURI().equals(logoutURL);
        
        
        if (loginRequest) {
            chain.doFilter(request, response);
            return;
        }
        if (lrc != null) {
            if (lrc.isLoggedIn()) {
                chain.doFilter(request, response);
            }
            else {
                response.sendRedirect(loginURL);
            }
        }
        else {
            response.sendRedirect(loginURL);
        }
       
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }


    @Override
    public void destroy() {
        
    }
}
