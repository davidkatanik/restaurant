/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.db.postgresDAOImpl;

import com.vsb.bl.domain.Drink;
import com.vsb.bl.domain.Employee;
import com.vsb.bl.domain.Order;
import com.vsb.bl.domain.Ticket;
import com.vsb.db.dao.OrderDAO;
import com.vsb.db.postgresqlConnector.PostgresConnector;
import com.vsb.db.postgresqlConnector.PostgresConnectorImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class OrderDAOImpl implements OrderDAO {

    private static final String TABLE_NAME = "objednavka";

    private static final String INSERT = "INSERT INTO " + TABLE_NAME + "(stul, objednano, poznamka, idu, idz) VALUES ( ?, ?, ?, ?, ?)";

    
    private static final String UPDATE = "UPDATE " + TABLE_NAME + " SET stul=?, objednano=?, vyhotoveno=?, poznamka=?, idu=?, idz=? WHERE ido=?";
    private static final String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE ido=?";
    private static final String SELECT = "SELECT * FROM " + TABLE_NAME;
    private static final String SELECT_ID = "SELECT * FROM " + TABLE_NAME + " WHERE ido=?";
    private static final String SELECT_IDT = "SELECT * FROM " + TABLE_NAME + " WHERE idu=?";
    private static final String SELECT_LAST = "SELECT * FROM " + TABLE_NAME + " ORDER BY ido DESC LIMIT 1";

    @Override
    public void addOrder(Order order) {
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setInt(1, order.getTable());
            stm.setTimestamp(2, new Timestamp(new Date().getTime()));
            stm.setString(3, order.getNote());
            stm.setLong(4, order.getTicket().getId());
            stm.setLong(5, order.getEmployee().getId());
            
            stm.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
    }

    @Override
    public void updateOrder(Order order) {
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(UPDATE);
            stm.setInt(1, order.getTable());
             if (order.getCompleted() != null) {
                stm.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(order.getCompleted().toString()));
            }
            stm.setString(4, order.getNote());
            stm.setLong(5, order.getEmployee().getId());
            stm.setLong(6, order.getTicket().getId());
            stm.setLong(7, order.getId());
            stm.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        connector.close();
    }

    @Override
    public void deleteOrder(Order order) {
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(DELETE);
            stm.setLong(1, order.getId());
            stm.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DrinkDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        connector.close();
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> resultList = new LinkedList<>();
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong(1));
                order.setOrdered(rs.getDate(2));
                order.setCompleted(rs.getDate(3));
                order.setNote(rs.getString(4));
                order.setEmployee(new Employee(rs.getLong(5)));
                order.setTicket(new Ticket(rs.getLong(6)));
                resultList.add(order);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return resultList;
    }

    @Override
    public Order getOrder(Long ido) {
        Order result = null;
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT_ID);
            stm.setLong(1, ido);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                result = new Order();
                result.setId(rs.getLong(1));
                result.setOrdered(rs.getDate(2));
                result.setCompleted(rs.getDate(3));
                result.setNote(rs.getString(4));
                result.setEmployee(new Employee(rs.getLong(5)));
                result.setTicket(new Ticket(rs.getLong(6)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return result;
    }

    @Override
    public List<Order> getAllOrders(Long idt) {
        List<Order> resultList = new LinkedList<>();
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT_IDT);
            stm.setLong(1, idt);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong(1));
                order.setTable(rs.getInt(2));
                order.setOrdered(rs.getDate(3));
                order.setCompleted(rs.getDate(4));
                order.setNote(rs.getString(5));
                order.setEmployee(new Employee(rs.getLong(6)));
                order.setTicket(new Ticket(rs.getLong(7)));
                resultList.add(order);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return resultList;
    }

    @Override
    public Order getLastOrder() {
        Order result = null;
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT_LAST);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                result = new Order();
                result.setId(rs.getLong(1));
                result.setOrdered(rs.getDate(2));
                result.setCompleted(rs.getDate(3));
                result.setNote(rs.getString(4));
                result.setEmployee(new Employee(rs.getLong(5)));
                result.setTicket(new Ticket(rs.getLong(6)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return result;
    }

}
