/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.beans;

import com.vsb.bl.domain.service.EmployeeService;
import com.vsb.bl.domain.service.EmployeeServiceImpl;
import com.sun.faces.context.SessionMap;
import com.vsb.bl.domain.Employee;
import com.vsb.bl.domain.Role;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author David
 */
@ManagedBean
@RequestScoped
public class LoginBean {

    EmployeeService employeeService;
    String name;
    String password;
    SessionMap session;

    @PostConstruct
    void init(){
        employeeService = new EmployeeServiceImpl();
//        Employee e = new Employee();
//        e.setName("David");
//        e.setLastname("Katanik");
//        e.setLogin("dkat");
//        e.setPassword("123");
//        e.setRc("123456789");
//        e.setTelephone("123456789");
//        e.setEmail("daviddavid");
//        e.setSalary(20000L);
//        e.setRole(Role.WAITER);
//        employeeService.insert(e);
    }
    
    public String login() {
        Employee e = employeeService.logIn(name, password);
        if (e == null) {
            return "";
        }
        FacesContext context = FacesContext.getCurrentInstance();
        session = (SessionMap) context.getExternalContext().getSessionMap();
        session.put("empId", e.getId());
        session.put("empName",e.getName()+" "+e.getLastname());
        //context.getExternalContext().getSessionMap().put("client", c.getRc());
        return "/waiterPages/orders";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
