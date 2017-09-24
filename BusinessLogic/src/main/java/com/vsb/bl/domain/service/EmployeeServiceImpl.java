/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.bl.domain.service;

import com.vsb.bl.domain.Employee;
import com.vsb.bl.domain.Food;
import com.vsb.bl.domain.Order;
import com.vsb.bl.domain.OrderedFood;
import com.vsb.bl.domain.Payment;
import com.vsb.bl.domain.Ticket;
import com.vsb.db.dao.EmployeeDAO;
import com.vsb.db.postgresDAOImpl.EmployeeDAOImpl;
import com.vsb.db.dao.FoodDAO;
import com.vsb.db.postgresDAOImpl.FoodDAOImpl;
import com.vsb.db.dao.OrderDAO;
import com.vsb.db.postgresDAOImpl.OrderDAOImpl;
import com.vsb.db.dao.OrderedFoodDAO;
import com.vsb.db.postgresDAOImpl.OrderedFoodDAOImpl;
import com.vsb.db.dao.TicketDAO;
import com.vsb.db.postgresDAOImpl.TicketDAOImpl;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author David
 */
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    EmployeeDAO employeeDAOCSV = new com.vsb.db.csvDAOImpl.EmployeeDAOImpl();
    TicketDAO ticketDAO = new TicketDAOImpl();
    OrderDAO orderDAO = new OrderDAOImpl();
    FoodDAO foodDAO = new FoodDAOImpl();
    OrderedFoodDAO orderedFoodDAO = new OrderedFoodDAOImpl();

    @Override
    public boolean placeOrder(Order order) {
        if (order.getListOfFoods() == null && order.getListOfDrinks() == null) {
            return false;
        }
        if (order.getTicket().getId() == null) {
            ticketDAO.addTicket(order.getTicket());
            Ticket lastTicket = ticketDAO.getLastTicket();
            order.setTicket(lastTicket);
        }
        orderDAO.addOrder(order);
        Order lastOrder = orderDAO.getLastOrder();
        order.setId(lastOrder.getId());
        Map<Food, Integer> map = new LinkedHashMap<>();
        for (Food food : order.getListOfFoods()) {
            if (!map.containsKey(food)){
                map.put(food, 1);
            }
            else {
                Integer get = map.get(food);
                map.replace(food, get, get+1);
            }
        }
        for (Map.Entry<Food, Integer> entry : map.entrySet()) {
            Food food = entry.getKey();
            Integer num = entry.getValue();
            OrderedFood of = new OrderedFood(order, food, null, null, num, order.getEmployee());
            orderedFoodDAO.addOrderedFoodToOrder(of); 
        }
        return true;
    }

    @Override
    public boolean payTicket(Ticket ticket, Long amount, Payment payment) {
        List<Order> allOrders = orderDAO.getAllOrders(ticket.getId());
        Long cost = 0L;
        for (Order order : allOrders) {
            for (Food food : order.getListOfFoods()) {
                cost += food.getCost();
            }

        }
        if (amount < cost) {
            return false;
        }
        ticket.setCost(cost);
        if (ticket.getDiscount() == null) {
            ticket.setDiscount(0L);
        }
        ticket.setPayment(payment);
        ticketDAO.updateTicket(ticket);
        return true;

    }

    @Override
    public boolean startCookingFood(Order order, Food food) {
        OrderedFood orderedFood = orderedFoodDAO.getOrderedFood(order, food);
        orderedFood.setStart(new Date());
        orderedFoodDAO.updateOrderedFoodInOrder(orderedFood);
        return true;
    }

    @Override
    public boolean finishCookingFood(Order order, Food food) {
        OrderedFood orderedFood = orderedFoodDAO.getOrderedFood(order, food);
        orderedFood.setFinished(new Date());
        orderedFoodDAO.updateOrderedFoodInOrder(orderedFood);
        return true;
    }

    @Override
    public Employee logIn(String name, String password) {
        return employeeDAOCSV.getEmployee(name, password);
    }

    @Override
    public void insert(Employee employee) {
        employeeDAO.addEmployee(employee);
    }

    @Override
    public Employee getEmployee(Long id) {
        return employeeDAO.getEmployee(id);
    }

}
