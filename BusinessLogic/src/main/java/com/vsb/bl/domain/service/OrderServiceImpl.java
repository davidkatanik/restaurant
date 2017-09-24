/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.bl.domain.service;

import com.vsb.bl.domain.Order;
import com.vsb.bl.domain.Ticket;
import com.vsb.db.dao.OrderDAO;
import com.vsb.db.postgresDAOImpl.OrderDAOImpl;
import java.util.List;

/**
 *
 * @author David
 */
public class OrderServiceImpl implements OrderService{

    OrderDAO orderDAO = new OrderDAOImpl();
    
    @Override
    public List<Order> getOrders() {
        return orderDAO.getAllOrders();
    }

    @Override
    public List<Order> getAllTicketOrders(Ticket ticket) {
        return orderDAO.getAllOrders(ticket.getId());
    }
    
}
