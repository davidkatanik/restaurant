package com.vsb.filters;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author David
 */
@WebFilter(filterName = "EmployeeLoginFilter", urlPatterns = {"/waiterPages/*"})
public class EmployeeLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request2 = (HttpServletRequest) request;
        String employeeLogin = (String) request2.getSession().getAttribute("empLogin");
         if (employeeLogin == null) {
            request2.getRequestDispatcher("../login.xhtml").forward(request, response);
        }
          chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    } 
    
}
