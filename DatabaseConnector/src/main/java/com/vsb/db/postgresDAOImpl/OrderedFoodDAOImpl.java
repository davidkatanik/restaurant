/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.db.postgresDAOImpl;

import com.vsb.bl.domain.Employee;
import com.vsb.bl.domain.Food;
import com.vsb.bl.domain.Order;
import com.vsb.bl.domain.OrderedFood;
import com.vsb.db.dao.OrderedFoodDAO;
import com.vsb.db.postgresqlConnector.PostgresConnector;
import com.vsb.db.postgresqlConnector.PostgresConnectorImpl;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class OrderedFoodDAOImpl implements OrderedFoodDAO {

    private static final String TABLE_NAME = "objednavka_jidlo";
    private static final String INSERT = "INSERT INTO " + TABLE_NAME + " (ido, idj, pocet, idz) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE " + TABLE_NAME + " SET zacatek=?, konec=?, pocet=?,idz=? WHERE ido=? AND idj=?";
    private static final String SELECT = "SELECT * FROM " + TABLE_NAME;
    private static final String SELECT_ID = "SELECT * FROM " + TABLE_NAME + " WHERE ido=? AND idj=?";
    private static final String SELECT_IDO = "SELECT * FROM " + TABLE_NAME + " WHERE ido=?";
    private static final String SELECT_IDZ = "SELECT * FROM " + TABLE_NAME + " WHERE idz=?";

    @Override
    public void addOrderedFoodToOrder(OrderedFood orderedFood) {
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setLong(1, orderedFood.getOrder().getId());
            stm.setLong(2, orderedFood.getFood().getId());
            stm.setInt(3, orderedFood.getCount());
            stm.setLong(4, orderedFood.getEmployee().getId());
            stm.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
    }

    @Override
    public void updateOrderedFoodInOrder(OrderedFood orderedFood) {
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(UPDATE);
            stm.setDate(1, (Date) orderedFood.getStart());
            stm.setDate(2, (Date) orderedFood.getFinished());
            stm.setInt(3, orderedFood.getCount());
            stm.setLong(4, orderedFood.getEmployee().getId());
            stm.setLong(5, orderedFood.getOrder().getId());
            stm.setLong(6, orderedFood.getFood().getId());
            stm.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
    }

    @Override
    public void deleteOrderedFoodFromOrder(OrderedFood orderedFood) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrderedFood> getAllOrderedFoods() {
        List<OrderedFood> resultList = new LinkedList<>();
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                OrderedFood orderedFood = new OrderedFood();
                orderedFood.setOrder(new Order(rs.getLong(1)));
                orderedFood.setFood(new Food(rs.getLong(2)));
                orderedFood.setStart(rs.getDate(3));
                orderedFood.setFinished(rs.getDate(4));
                orderedFood.setCount(rs.getInt(5));
                orderedFood.setEmployee(new Employee(rs.getLong(6)));
                resultList.add(orderedFood);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return resultList;
    }

    @Override
    public OrderedFood getOrderedFood(Order order, Food food) {
        OrderedFood result = null;
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT_ID);
            stm.setLong(1, order.getId());
            stm.setLong(2, food.getId());
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                result = new OrderedFood();
                result.setOrder(new Order(rs.getLong(1)));
                result.setFood(new Food(rs.getLong(2)));
                result.setStart(rs.getDate(3));
                result.setFinished(rs.getDate(4));
                result.setCount(rs.getInt(5));
                result.setEmployee(new Employee(rs.getLong(6)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return result;
    }

    @Override
    public List<OrderedFood> getAllOrderedFoods(Order order) {
        List<OrderedFood> resultList = new LinkedList<>();
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT_IDO);
            stm.setLong(1, order.getId());
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                OrderedFood orderedFood = new OrderedFood();
                orderedFood.setOrder(new Order(rs.getLong(1)));
                orderedFood.setFood(new Food(rs.getLong(2)));
                orderedFood.setStart(rs.getDate(3));
                orderedFood.setFinished(rs.getDate(4));
                orderedFood.setCount(rs.getInt(5));
                orderedFood.setEmployee(new Employee(rs.getLong(6)));
                resultList.add(orderedFood);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return resultList;
    }

    @Override
    public List<OrderedFood> getAllOrderedFoods(Employee employee) {
        List<OrderedFood> resultList = new LinkedList<>();
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT_IDZ);
            stm.setLong(1, employee.getId());
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                OrderedFood orderedFood = new OrderedFood();
                orderedFood.setOrder(new Order(rs.getLong(1)));
                orderedFood.setFood(new Food(rs.getLong(2)));
                orderedFood.setStart(rs.getDate(3));
                orderedFood.setFinished(rs.getDate(4));
                orderedFood.setCount(rs.getInt(5));
                orderedFood.setEmployee(new Employee(rs.getLong(6)));
                resultList.add(orderedFood);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return resultList;
    }

}
