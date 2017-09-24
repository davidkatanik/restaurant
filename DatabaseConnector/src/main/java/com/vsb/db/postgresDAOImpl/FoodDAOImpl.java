/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.db.postgresDAOImpl;

import com.vsb.bl.domain.Employee;
import com.vsb.bl.domain.Food;
import com.vsb.bl.domain.Order;
import com.vsb.bl.domain.Unit;
import com.vsb.db.dao.FoodDAO;
import com.vsb.db.postgresqlConnector.PostgresConnector;
import com.vsb.db.postgresqlConnector.PostgresConnectorImpl;
import java.sql.Connection;
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
public class FoodDAOImpl implements FoodDAO {

    private static final String TABLE_NAME = "jidlo";

    private static final String INSERT = "INSERT INTO " + TABLE_NAME + "(nazev, hmotnost, jednotka, cena, poznamka) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE " + TABLE_NAME + " SET nazev=?, hmotnost=?, jednotka=?, cena=?, poznamka=? WHERE idj=?";
    private static final String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE idj=?";
    private static final String SELECT = "SELECT * FROM " + TABLE_NAME;
    private static final String SELECT_ID = "SELECT * FROM " + TABLE_NAME + " WHERE idj=?";
    //TODO: add paramaters to DB which can display food status.
    private static final String SELECT_IDO = "SELECT j.* FROM "+TABLE_NAME+" j, objednavka_jidlo obj_j WHERE j.idj=obj_j.idj AND obj_j.ido=?";
    private static final String SELECT_IDE = "SELECT j.* FROM "+TABLE_NAME+" j, zamestnanec_jidlo zam_j WHERE j.idj=zam_j.idz AND zam_j.idz=?";

    @Override
    public void addFood(Food food) {
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setString(1, food.getName());
            stm.setInt(2, food.getWeigth());
            stm.setInt(3, food.getUnit().ordinal());
            stm.setLong(4, food.getCost());
            stm.setString(5, food.getNote());
            stm.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        connector.close();
    }

    @Override
    public void updateFood(Food food) {
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(UPDATE);
            stm.setString(1, food.getName());
            stm.setInt(2, food.getWeigth());
            stm.setInt(3, food.getUnit().ordinal());
            stm.setLong(4, food.getCost());
            stm.setString(5, food.getNote());
            stm.setLong(6, food.getId());
            stm.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        connector.close();
    }

    @Override
    public void deleteFood(Food food) {
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(DELETE);
            stm.setLong(1, food.getId());
            stm.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        connector.close();
    }

    @Override
    public List<Food> getAllFoods() {
        List<Food> resultList = new LinkedList<>();
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Food food = new Food();
                food.setId(rs.getLong(1));
                food.setName(rs.getString(2));
                food.setWeigth(rs.getInt(3));
                food.setUnit(Unit.values()[rs.getInt(4)]);
                food.setCost(rs.getLong(5));
                food.setNote(rs.getString(6));
                resultList.add(food);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return resultList;
    }

    @Override
    public Food getFood(Integer idf) {
        Food result = null;
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT_ID);
            stm.setInt(1, idf);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                result = new Food();
                result.setId(rs.getLong(1));
                result.setName(rs.getString(2));
                result.setWeigth(rs.getInt(3));
                result.setUnit(Unit.values()[rs.getInt(4)]);
                result.setCost(rs.getLong(5));
                result.setNote(rs.getString(6));
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return result;
    }

    @Override
    public List<Food> getAllFoods(Order order) {
        List<Food> resultList = new LinkedList<>();
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT_IDO);
            stm.setLong(1, order.getId());
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Food food = new Food();
                food.setId(rs.getLong(1));
                food.setName(rs.getString(2));
                food.setWeigth(rs.getInt(3));
                food.setUnit(Unit.values()[rs.getInt(4)]);
                food.setCost(rs.getLong(5));
                food.setNote(rs.getString(6));
                resultList.add(food);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return resultList;
    }

    @Override
    public List<Food> getAllFoods(Employee employee) {
        List<Food> resultList = new LinkedList<>();
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT_IDE);
            stm.setLong(1, employee.getId());
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Food food = new Food();
                food.setId(rs.getLong(1));
                food.setName(rs.getString(2));
                food.setWeigth(rs.getInt(3));
                food.setUnit(Unit.values()[rs.getInt(4)]);
                food.setCost(rs.getLong(5));
                food.setNote(rs.getString(6));
                resultList.add(food);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return resultList;
    }

}
