/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.db.dao;

import com.vsb.bl.domain.Ticket;
import java.util.List;

/**
 *
 * @author David
 */
public interface TicketDAO {

    void addTicket(Ticket ticket);

    void updateTicket(Ticket ticket);

    void deleteTicket(Ticket ticket);

    List<Ticket> getAllTickets();

    Ticket getTicket(Integer idt);
    
    Ticket getLastTicket();
}
