/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.bl.domain.service;

import com.vsb.bl.domain.Ticket;
import com.vsb.db.dao.TicketDAO;
import com.vsb.db.postgresDAOImpl.TicketDAOImpl;
import java.util.List;

/**
 *
 * @author David
 */
public class TicketServiceImpl implements TicketService{

    TicketDAO ticketDAO = new TicketDAOImpl();
    
    @Override
    public List<Ticket> getAllTickets() {
        return ticketDAO.getAllTickets();
    }

    @Override
    public void updateTicket(Ticket ticket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
