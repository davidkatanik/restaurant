/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.db.postgresDAOImpl;

import com.vsb.bl.domain.Employee;
import com.vsb.bl.domain.Role;
import com.vsb.db.dao.EmployeeDAO;
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
public class EmployeeDAOImpl implements EmployeeDAO {

    private static final String TABLE_NAME = "zamestnanec";

    private static final String INSERT = "INSERT INTO " + TABLE_NAME + "(jmeno, prijmeni, login, heslo, plat, rc, email, telefon, poznamka, role) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE " + TABLE_NAME + " SET jmeno=?, prijmeni=?, login=?, heslo=?, plat=?, rc=?, email=?, telefon=?, poznamka=?, role=? WHERE idz=?";
    private static final String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE idz=?";
    private static final String SELECT = "SELECT * FROM " + TABLE_NAME;
    private static final String SELECT_LOGIN = "SELECT * FROM " + TABLE_NAME + " WHERE login=? AND heslo=?";
    private static final String SELECT_ID = "SELECT * FROM " + TABLE_NAME + " WHERE idz=?";

    @Override
    public void addEmployee(Employee employee) {
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(INSERT);
            stm.setString(1, employee.getName());
            stm.setString(2, employee.getLastname());
            stm.setString(3, employee.getLogin());
            stm.setString(4, employee.getPassword());
            stm.setLong(5, employee.getSalary());
            stm.setString(6, employee.getRc());
            stm.setString(7, employee.getEmail());
            stm.setString(8, employee.getTelephone());
            stm.setString(9, employee.getNote());
            stm.setInt(10, employee.getRole().ordinal());
            stm.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(DrinkDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        connector.close();
    }

    @Override
    public void updateEmployee(Employee employee) {
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(UPDATE);
            stm.setString(1, employee.getName());
            stm.setString(2, employee.getLastname());
            stm.setString(3, employee.getLogin());
            stm.setString(4, employee.getPassword());
            stm.setLong(5, employee.getSalary());
            stm.setString(6, employee.getRc());
            stm.setString(7, employee.getEmail());
            stm.setString(8, employee.getTelephone());
            stm.setString(9, employee.getNote());
            stm.setInt(10, employee.getRole().ordinal());
            stm.setLong(11, employee.getId());
            stm.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(DrinkDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        connector.close();
    }

    @Override
    public void deleteEmployee(Employee employee) {
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(DELETE);
            stm.setLong(1, employee.getId());
            stm.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(DrinkDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        connector.close();
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> resultList = new LinkedList<>();
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getLong(1));
                employee.setName(rs.getString(2));
                employee.setLastname(rs.getString(3));
                employee.setLogin(rs.getString(4));
                employee.setPassword(rs.getString(5));
                employee.setSalary(rs.getLong(6));
                employee.setRc(rs.getString(7));
                employee.setEmail(rs.getString(8));
                employee.setTelephone(rs.getString(9));
                employee.setNote(rs.getString(10));
                employee.setRole(Role.values()[rs.getInt(11)]);
                resultList.add(employee);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DrinkDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return resultList;
    }

    @Override
    public Employee getEmployee(Long ide) {
        Employee result = null;
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT_ID);
            stm.setLong(1, ide);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                result = new Employee();
                result.setId(rs.getLong(1));
                result.setName(rs.getString(2));
                result.setLastname(rs.getString(3));
                result.setLogin(rs.getString(4));
                result.setPassword(rs.getString(5));
                result.setSalary(rs.getLong(6));
                result.setRc(rs.getString(7));
                result.setEmail(rs.getString(8));
                result.setTelephone(rs.getString(9));
                result.setNote(rs.getString(10));
                result.setRole(Role.values()[rs.getInt(11)]);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DrinkDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return result;
    }

    @Override
    public Employee getEmployee(String login, String password) {
        Employee result = null;
        PostgresConnector connector = new PostgresConnectorImpl();
        Connection conn = connector.connect();
        try {
            PreparedStatement stm = conn.prepareStatement(SELECT_LOGIN);
            stm.setString(1, login);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                result = new Employee();
                result.setId(rs.getLong(1));
                result.setName(rs.getString(2));
                result.setLastname(rs.getString(3));
                result.setLogin(rs.getString(4));
                result.setPassword(rs.getString(5));
                result.setSalary(rs.getLong(6));
                result.setRc(rs.getString(7));
                result.setEmail(rs.getString(8));
                result.setTelephone(rs.getString(9));
                result.setNote(rs.getString(10));
                result.setRole(Role.values()[rs.getInt(11)]);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DrinkDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        connector.close();
        return result;
    }
}
