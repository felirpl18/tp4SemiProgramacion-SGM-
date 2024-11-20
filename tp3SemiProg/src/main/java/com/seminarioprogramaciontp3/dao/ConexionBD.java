
package com.seminarioprogramaciontp3.dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionBD {
    private String URL = "jdbc:sqlite:";
    private Connection connection = null; 
    private static ConexionBD instancia = null;
    
    
    private ConexionBD() {
        if (connection == null) {
            try {
                
                
                String url = getClass().getResource("database.db").toURI().toString();
                
                connection = DriverManager.getConnection("jdbc:sqlite:"+url);
                connection.setAutoCommit(false);
                
                if (connection != null) {
                }
            } catch (SQLException e) {
            } catch (URISyntaxException e) {
            }
        }
    }
    
    public static ConexionBD getInstancia() {
        if(instancia == null) {
            instancia = new ConexionBD();
        }else{

        }
        return instancia;
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    protected void desconectar() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        instancia = null;
        connection = null;
    }
    
}
