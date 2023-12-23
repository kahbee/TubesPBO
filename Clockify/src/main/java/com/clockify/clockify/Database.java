package com.clockify.clockify;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khusyasy
 */
public class Database {
    private Connection conn;
    private String DB_URL = "jdbc:mysql://localhost:3306/clockify-db";
    private String DB_USERNAME = "root";
    private String DB_PASSWORD = "";
    
    public Database() {
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        return conn.createStatement().executeQuery(query);
    }

    public ResultSet executeQuery(String query, Object... params) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
        return ps.executeQuery();
    }

    public int executeUpdate(String query) throws SQLException {
        return conn.createStatement().executeUpdate(query);
    }

    public int executeUpdate(String query, Object... params) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
        return ps.executeUpdate();
    }
}
