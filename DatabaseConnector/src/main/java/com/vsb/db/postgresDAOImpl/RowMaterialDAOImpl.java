/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.db.postgresDAOImpl;

import com.vsb.bl.domain.Food;
import com.vsb.bl.domain.RowMaterial;
import com.vsb.db.dao.RowMaterialDAO;
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
public class RowMaterialDAOImpl implements RowMaterialDAO {

    private static final String TABLE_NAME = "surovina";

    private static final String INSERT = "INSERT INTO " + TABLE_NAME + "(nazev, mnozstvi, poznamka) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE " + TABLE_NAME + " SET nazev=?, mnozstvi=?, poznamka=? WHERE ids=?";
    private static final String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE ids=?";
    private static final String SELECT = "SELECT * FROM " + TABLE_NAME;
    private static final String SELECT_ID = "SELECT * FROM " + TABLE_NAME + " WHERE ids=?";
    private static final String SELECT_FOOD = "SELECT s.* FROM " + TABLE_NAME + " s, jidlo_surovina x WHERE s.ids=x.ids AND x.idj=?";

    @Override
    public void addRowMaterial(RowMaterial rowMaterial) {
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setString(1, rowMaterial.getName());
            stm.setInt(2, rowMaterial.getCount());
            stm.setString(3, rowMaterial.getNote());
            stm.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        connector.close();
    }

    @Override
    public void updateRowMaterial(RowMaterial rowMaterial) {
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(UPDATE);
            stm.setString(1, rowMaterial.getName());
            stm.setInt(2, rowMaterial.getCount());
            stm.setString(3, rowMaterial.getNote());
            stm.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        connector.close();
    }

    @Override
    public void deleteRowMaterial(RowMaterial rowMaterial) {
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(DELETE);
            stm.setLong(1, rowMaterial.getId());
            stm.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DrinkDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        connector.close();
    }

    @Override
    public List<RowMaterial> getAllRowMaterials() {
        List<RowMaterial> resultList = new LinkedList<>();
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                RowMaterial row = new RowMaterial();
                row.setId(rs.getLong(1));
                row.setName(rs.getString(2));
                row.setCount(rs.getInt(3));
                row.setNote(rs.getString(4));
                resultList.add(row);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return resultList;
    }

    @Override
    public RowMaterial getRowMaterial(Integer idrm) {
        RowMaterial result = null;
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT_ID);
            stm.setInt(1, idrm);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                result = new RowMaterial();
                result.setId(rs.getLong(1));
                result.setName(rs.getString(2));
                result.setCount(rs.getInt(3));
                result.setNote(rs.getString(4));
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return result;
    }

    @Override
    public List<RowMaterial> getAllRowMaterials(Food food) {
        List<RowMaterial> resultList = new LinkedList<>();
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT_FOOD);
            stm.setLong(1, food.getId());
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                RowMaterial row = new RowMaterial();
                row.setId(rs.getLong(1));
                row.setName(rs.getString(2));
                row.setCount(rs.getInt(3));
                row.setNote(rs.getString(4));
                resultList.add(row);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return resultList;
    }

}
