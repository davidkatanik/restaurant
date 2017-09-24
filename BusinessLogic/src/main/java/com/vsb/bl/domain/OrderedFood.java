/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.bl.domain;

import java.util.Date;

/**
 *
 * @author David
 */
public class OrderedFood {

    Order order;
    Food food;
    Date start;
    Date finished;
    Integer count;
    Employee employee;

    public OrderedFood(Order order, Food food, Date start, Date finished, Integer count, Employee employee) {
        this.order = order;
        this.food = food;
        this.start = start;
        this.finished = finished;
        this.count = count;
        this.employee = employee;
    }

    

    public OrderedFood() {
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinished() {
        return finished;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
