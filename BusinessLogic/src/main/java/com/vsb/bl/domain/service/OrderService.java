/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.bl.domain.service;

import com.vsb.bl.domain.Order;
import com.vsb.bl.domain.Ticket;
import java.util.List;

/**
 *
 * @author David
 */
public interface OrderService {
    List<Order> getOrders();
    
    List<Order> getAllTicketOrders(Ticket ticket);
}
