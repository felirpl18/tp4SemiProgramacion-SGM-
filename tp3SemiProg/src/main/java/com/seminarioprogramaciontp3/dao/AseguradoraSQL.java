package com.seminarioprogramaciontp3.dao;

import com.seminarioprogramaciontp3.dto.AseguradoraDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AseguradoraSQL implements AseguradoraDAO {

    private ConexionBD conexion;

    public AseguradoraSQL() {
        conexion = ConexionBD.getInstancia();
    }

    @Override
    public AseguradoraDTO buscar(int id_aseguradora) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        AseguradoraDTO aseguradora = null;

        try {
            con = conexion.getConnection();
            String sql = "SELECT * FROM Aseguradora WHERE id_aseguradora = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, id_aseguradora);

            rs = sentencia.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String cuit = rs.getString("cuit");

                aseguradora = new AseguradoraDTO(id_aseguradora, nombre, email, cuit);
            }

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            cerrarRecursos(con, sentencia, rs);
        }
        return aseguradora;
    }

    @Override
    public List<AseguradoraDTO> listar() {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        List<AseguradoraDTO> lista = new ArrayList<>();

        try {
            con = conexion.getConnection();
            String sql = "SELECT * FROM Aseguradora";
            sentencia = con.prepareStatement(sql);

            rs = sentencia.executeQuery();

            while (rs.next()) {
                int id_aseguradora = rs.getInt("id_aseguradora");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String cuit = rs.getString("cuit");

                AseguradoraDTO aseguradora = new AseguradoraDTO(id_aseguradora, nombre, email, cuit);
                lista.add(aseguradora);
            }

        } catch (SQLException e) {
            System.err.println(e);

        }
        return lista;
    }

    @Override
    public boolean insertar(String nombre, String email, String cuit) {
        Connection con = null;
        PreparedStatement sentencia = null;
        boolean exito = false;

        try {
            con = conexion.getConnection();
            String sql = "INSERT INTO Aseguradora (nombre, email, cuit) VALUES (?, ?, ?)";
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, nombre);
            sentencia.setString(2, email);
            sentencia.setString(3, cuit);

            int filasAfectadas = sentencia.executeUpdate();
            exito = filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println(e);
        }
        return exito;
    }

    @Override
    public boolean actualizar(int id_aseguradora, String nombre, String email, String cuit) {
        Connection con = null;
        PreparedStatement sentencia = null;
        boolean exito = false;

        try {
            con = conexion.getConnection();
            String sql = "UPDATE Aseguradora SET nombre = ?, email = ?, cuit = ? WHERE id_aseguradora = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, nombre);
            sentencia.setString(2, email);
            sentencia.setString(3, cuit);
            sentencia.setInt(4, id_aseguradora);

            int filasAfectadas = sentencia.executeUpdate();
            exito = filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            cerrarRecursos(con, sentencia, null);
        }
        return exito;
    }

    @Override
    public boolean borrar(int id_aseguradora) {
        Connection con = null;
        PreparedStatement sentencia = null;

        try {
            con = conexion.getConnection();
            String sql = "DELETE FROM Aseguradora WHERE id_aseguradora = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, id_aseguradora);

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

    private void cerrarRecursos(Connection con, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
