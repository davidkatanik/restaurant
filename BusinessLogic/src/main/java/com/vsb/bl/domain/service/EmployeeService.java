/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.bl.domain.service;

import com.vsb.bl.domain.Employee;
import com.vsb.bl.domain.Food;
import com.vsb.bl.domain.Order;
import com.vsb.bl.domain.Payment;
import com.vsb.bl.domain.Ticket;

/**
 *
 * @author David
 */
public interface EmployeeService {
    boolean placeOrder(Order order);
    
    boolean payTicket(Ticket ticket, Long amount, Payment payment);
    
    boolean startCookingFood(Order order, Food food);
    
    boolean finishCookingFood(Order order, Food food);

    public Employee logIn(String name, String password);
    
    public Employee getEmployee(Long id);

    public void insert(Employee employee);
    
    
}
