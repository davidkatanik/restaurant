package com.vsb.beans;

import com.sun.faces.context.SessionMap;
import com.vsb.bl.domain.Employee;
import com.vsb.bl.domain.Food;
import com.vsb.bl.domain.Order;
import com.vsb.bl.domain.Ticket;
import com.vsb.bl.domain.service.EmployeeService;
import com.vsb.bl.domain.service.EmployeeServiceImpl;
import com.vsb.bl.domain.service.FoodService;
import com.vsb.bl.domain.service.FoodServiceImpl;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author David
 */
@ManagedBean
@SessionScoped
public class OrderBean {

    FoodService foodService = new FoodServiceImpl();
    EmployeeService employeeService = new EmployeeServiceImpl();
    
    Order order;

    Food food;

    Food orderedFood;

    List<Food> foods;

    @PostConstruct
    void init() {
        foods = foodService.getAllFoods();
        order = new Order();
        
    }

    public void orderFood(Food food) {
        order.getListOfFoods().add(food);
    }
    
    
    public void placeOrder(){
        Ticket ticket = new Ticket();
        ticket.setCreated(new Date());
        Employee employee = employeeService.getEmployee(getEmployeeId());
        ticket.setEmployee(employee);
        order.setTicket(ticket);
        order.setTable(1);
        order.setOrdered(new Date());
        order.setEmployee(employee);
        employeeService.placeOrder(order);
    }
    
    
    private Long getEmployeeId(){
        SessionMap map = (SessionMap) FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        return (Long) map.get("empId");
    }
    
    

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public Food getOrderedFood() {
        return orderedFood;
    }

    public void setOrderedFood(Food orderedFood) {
        this.orderedFood = orderedFood;
    }

}
