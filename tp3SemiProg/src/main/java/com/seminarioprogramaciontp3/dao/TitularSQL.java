
package com.seminarioprogramaciontp3.dao;

import com.seminarioprogramaciontp3.dto.TitularDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class TitularSQL implements TitularDAO {

    private ConexionBD conexion = null;

    public TitularSQL() {
        conexion = ConexionBD.getInstancia();
    }

    @Override
    public TitularDTO buscar(String nombre, String apellido) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        TitularDTO titular = null;

        try {
            con = conexion.getConnection();
            String sql = "select * "
                    + "from Titular where nombre = ? and apellido = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, nombre);
            sentencia.setString(2, apellido);

            rs = sentencia.executeQuery();

            String nombre_db;
            String apellido_db;
            String telefono;
            int id_titular;
            String nro_doc;

            while (rs.next()) {
                nombre_db = rs.getString("nombre");
                apellido_db = rs.getString("apellido");
                telefono = rs.getString("telefono");
                id_titular = rs.getInt("id_titular");
                nro_doc = rs.getString("nro_doc");

                titular = new TitularDTO(id_titular, nombre_db,
                        apellido_db, nro_doc, telefono);
            }

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                rs.close();
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            } catch (NullPointerException ex){
                System.err.println(ex);
            }
        }
        return titular;
    }

    @Override
    public TitularDTO buscar(int id_titular) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        TitularDTO titular = null;

        try {
            con = conexion.getConnection();
            String sql = "select * "
                    + "from Titular where id_titular = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, id_titular);

            rs = sentencia.executeQuery();

            String nombre_db;
            String apellido_db;
            String telefono;
            int id_titular_db;
            String nro_doc;

            while (rs.next()) {
                nombre_db = rs.getString("nombre");
                apellido_db = rs.getString("apellido");
                telefono = rs.getString("telefono");
                id_titular_db = rs.getInt("id_titular");
                nro_doc = rs.getString("nro_doc");

                titular = new TitularDTO(id_titular_db, nombre_db, apellido_db, nro_doc, telefono);
            }

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                rs.close();
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            } catch (NullPointerException ex){
                System.err.println(ex);
            }
        }
        return titular;
    }

    @Override
    public List<TitularDTO> listar() {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        TitularDTO titular = null;
        List<TitularDTO> lista = new ArrayList<>();

        try {
            con = conexion.getConnection();
            String sql = "select * "
                    + "from Titular order by id_titular DESC";
            sentencia = con.prepareStatement(sql);

            rs = sentencia.executeQuery();

            String nombre;
            String apellido;
            String telefono;
            int id_titular;
            String nro_doc;

            while (rs.next()) {
                nombre = rs.getString("nombre");
                apellido = rs.getString("apellido");
                telefono = rs.getString("telefono");
                id_titular = rs.getInt("id_titular");
                nro_doc = rs.getString("nro_doc");

                titular = new TitularDTO(id_titular, nombre, apellido, nro_doc, telefono);
                lista.add(titular);
            }

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                rs.close();
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            } catch (NullPointerException ex){
                System.err.println(ex);
            }
        }
        return lista;
    }

    @Override
    
    public boolean insertar(String nombre, String apellido, String nro_doc, String telefono) {
        Connection con = null;
        PreparedStatement sentencia = null;
        boolean exito = false;

        try {
            con = conexion.getConnection();
            String sql = "INSERT INTO Titular (nombre, apellido, nro_doc, telefono) VALUES (?, ?, ?, ?)";
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, nombre);
            sentencia.setString(2, apellido);
            sentencia.setString(3, nro_doc);
            sentencia.setString(4, telefono);

            int filasAfectadas = sentencia.executeUpdate();
            if (filasAfectadas > 0) {
                exito = true;
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
        return exito;
    }


    @Override
    public boolean modificar(int id_titular, String nombre, String apellido, String nro_doc, String telefono) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean borrar(int id_titular) {
        Connection con = null;
        PreparedStatement sentencia = null;

        try {
            con = conexion.getConnection();
            String sql = "DELETE FROM Titular WHERE id_titular = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, id_titular);

            int filasAfectadas = sentencia.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
    }


    @Override
    public void cerrarConexion() {
        conexion.desconectar();
    }
}
