/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.db.postgresqlConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class PostgresConnectorImpl implements PostgresConnector {

    Connection connection;

    @Override
    public Connection connect() {
        try {
            if (connection == null || connection.isValid(0)) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurace", "postgres", "admin");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PostgresConnectorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostgresConnectorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void startTransaction() {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(PostgresConnectorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PostgresConnectorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
