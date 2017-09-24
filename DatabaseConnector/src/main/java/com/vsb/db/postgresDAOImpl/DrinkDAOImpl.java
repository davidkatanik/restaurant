/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.db.postgresDAOImpl;

import com.vsb.bl.domain.Drink;
import com.vsb.bl.domain.Order;
import com.vsb.bl.domain.Unit;
import com.vsb.db.dao.DrinkDAO;
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
public class DrinkDAOImpl implements DrinkDAO {

    private static final String TABLE_NAME = "piti";

    private static final String INSERT = "INSERT INTO " + TABLE_NAME + "(nazev, objem, jednotka, cena, mnozstvi, poznamka) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE " + TABLE_NAME + " SET nazev=?, objem=?, jednotka=?, cena=?, mnozstvi=?, poznamka=? WHERE idp=?";
    private static final String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE idp=?";
    private static final String SELECT_NAME = "SELECT * FROM " + TABLE_NAME + " WHERE nazev=?";
    private static final String SELECT = "SELECT * FROM " + TABLE_NAME;
    private static final String SELECT_ID = "SELECT * FROM " + TABLE_NAME + " WHERE idp=?";
    private static final String SELECT_IDO = "SELECT * FROM "+TABLE_NAME+" p, objednavka_piti obj_p WHERE p.idp=obj_p.idp AND obj_p.ido=ido=?";

    @Override
    public void addDrink(Drink drink) {
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setString(1, drink.getName());
            stm.setInt(2, drink.getBulk());
            stm.setInt(3, drink.getUnit().ordinal());
            stm.setLong(4, drink.getCost());
            stm.setInt(5, drink.getCount());
            stm.setString(6, drink.getNote());
            stm.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(DrinkDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        connector.close();
    }

    @Override
    public void updateDrink(Drink drink) {
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(UPDATE);
            stm.setString(1, drink.getName());
            stm.setInt(2, drink.getBulk());
            stm.setInt(3, drink.getUnit().ordinal());
            stm.setLong(4, drink.getCost());
            stm.setInt(5, drink.getCount());
            stm.setString(6, drink.getNote());
            stm.setLong(7, drink.getId());
            stm.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DrinkDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        connector.close();
    }

    @Override
    public void deleteDrink(Drink drink) {
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(DELETE);
            stm.setLong(1, drink.getId());
            stm.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DrinkDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        connector.close();
    }

    @Override
    public List<Drink> getAllDrinks() {
        List<Drink> resultList = new LinkedList<>();
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Drink d = new Drink();
                d.setId(rs.getLong(1));
                d.setName(rs.getString(2));
                d.setBulk(rs.getInt(3));
                d.setUnit(Unit.values()[rs.getInt(4)]);
                d.setCost(rs.getLong(5));
                d.setCount(rs.getInt(6));
                d.setNote(rs.getString(7));
                resultList.add(d);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DrinkDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return resultList;
    }

    @Override
    public Drink getDrink(int idd) {
        Drink result = null;
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT_ID);
            stm.setInt(1, idd);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                result = new Drink();
                result.setId(rs.getLong(1));
                result.setName(rs.getString(2));
                result.setBulk(rs.getInt(3));
                result.setUnit(Unit.values()[rs.getInt(4)]);
                result.setCost(rs.getLong(5));
                result.setCount(rs.getInt(6));
                result.setNote(rs.getString(7));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DrinkDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return result;
    }

    @Override
    public List<Drink> getAllDrinks(Order order) {
        List<Drink> resultList = new LinkedList<>();
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT_IDO);
            stm.setLong(1, order.getId());
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Drink result = new Drink();
                result.setId(rs.getLong(1));
                result.setName(rs.getString(2));
                result.setBulk(rs.getInt(3));
                result.setUnit(Unit.values()[rs.getInt(4)]);
                result.setCost(rs.getLong(5));
                result.setCount(rs.getInt(6));
                result.setNote(rs.getString(7));
                resultList.add(result);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DrinkDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return resultList;
    }

}
