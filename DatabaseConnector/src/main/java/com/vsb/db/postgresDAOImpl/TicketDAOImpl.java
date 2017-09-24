/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.db.postgresDAOImpl;

import com.vsb.bl.domain.Employee;
import com.vsb.bl.domain.Payment;
import com.vsb.bl.domain.Ticket;
import com.vsb.db.dao.TicketDAO;
import com.vsb.db.postgresqlConnector.PostgresConnector;
import com.vsb.db.postgresqlConnector.PostgresConnectorImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class TicketDAOImpl implements TicketDAO {

    private static final String TABLE_NAME = "ucet";

    private static final String INSERT = "INSERT INTO " + TABLE_NAME + "(vytvoren, idz) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE " + TABLE_NAME + " SET zpusob_platby=?, cena=?, dph=?, sleva=?, poznamka=? WHERE idu=?";
    private static final String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE idu=?";
    private static final String SELECT = "SELECT * FROM " + TABLE_NAME;
    private static final String SELECT_LAST = "SELECT * FROM " + TABLE_NAME + " ORDER BY idu DESC LIMIT 1";
    private static final String SELECT_ID = "SELECT * FROM " + TABLE_NAME + " WHERE idu=?";

    @Override
    public void addTicket(Ticket ticket) {
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setTimestamp(1, new Timestamp(new Date().getTime()));
            stm.setLong(2, ticket.getEmployee().getId());
            stm.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        connector.close();
    }

    @Override
    public void updateTicket(Ticket ticket) {
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(UPDATE);
            stm.setInt(1, ticket.getPayment().ordinal());
            stm.setLong(2, ticket.getCost());
            stm.setInt(3, ticket.getTax());
            stm.setLong(4, ticket.getDiscount());
            stm.setString(5, ticket.getNote());
            stm.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        connector.close();
    }

    @Override
    public void deleteTicket(Ticket ticket) {
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(DELETE);
            stm.setLong(1, ticket.getId());
            stm.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DrinkDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        connector.close();
    }

    @Override
    public List<Ticket> getAllTickets() {
        List<Ticket> resultList = new LinkedList<>();
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getLong(1));
                ticket.setCreated(rs.getDate(2));
                ticket.setPayment(Payment.values()[rs.getInt(3)]);
                ticket.setCost(rs.getLong(4));
                ticket.setTax(rs.getInt(5));
                ticket.setNote(rs.getString(6));
                ticket.setEmployee(new Employee(rs.getLong(7)));
                resultList.add(ticket);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return resultList;
    }

    @Override
    public Ticket getTicket(Integer idt) {
        Ticket result = null;
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT_ID);
            stm.setInt(1, idt);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                result = new Ticket();
                result.setId(rs.getLong(1));
                result.setCreated(rs.getDate(2));
                result.setPayment(Payment.values()[rs.getInt(3)]);
                result.setCost(rs.getLong(4));
                result.setTax(rs.getInt(5));
                result.setNote(rs.getString(6));
                result.setEmployee(new Employee(rs.getLong(7)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return result;
    }

    @Override
    public Ticket getLastTicket() {
        Ticket result = null;
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT_LAST);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                result = new Ticket();
                result.setId(rs.getLong(1));
                result.setCreated(rs.getDate(2));
                result.setPayment(Payment.values()[rs.getInt(3)]);
                result.setCost(rs.getLong(4));
                result.setTax(rs.getInt(5));
                result.setNote(rs.getString(6));
                result.setEmployee(new Employee(rs.getLong(7)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return result;
    }

}
