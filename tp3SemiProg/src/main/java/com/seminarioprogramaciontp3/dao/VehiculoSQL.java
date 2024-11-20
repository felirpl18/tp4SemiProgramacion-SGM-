
package com.seminarioprogramaciontp3.dao;
import com.seminarioprogramaciontp3.dto.TitularDTO;
import com.seminarioprogramaciontp3.dto.VehiculoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class VehiculoSQL implements VehiculoDAO {

    private ConexionBD conexion = null;

    public VehiculoSQL() {
        conexion = ConexionBD.getInstancia();
    }

    
    
    @Override
    public VehiculoDTO buscar(String patente) {
        Connection con = null; 
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        VehiculoDTO vehiculo = null;

        try {
            con = conexion.getConnection(); 
            String sql = "select * "
                       + "from Vehiculo where patente = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, patente); 

            rs = sentencia.executeQuery(); 
            
            
            int id_vehiculo_db;
            int id_titular_db;
            int id_aseguradora_db;
            String patente_db;
            String marca_db;
            String modelo_db;
            String nro_poliza_db;
            
            while (rs.next()) {
                id_vehiculo_db = rs.getInt("id_vehiculo"); 
                id_titular_db = rs.getInt("id_titular");
                id_aseguradora_db = rs.getInt("id_aseguradora");
                patente_db = rs.getString("patente"); 
                marca_db = rs.getString("marca");   
                modelo_db = rs.getString("modelo");   
                nro_poliza_db = rs.getString("nro_poliza");   

                vehiculo = new VehiculoDTO(id_vehiculo_db, id_titular_db, id_aseguradora_db, patente_db, marca_db, modelo_db, nro_poliza_db);
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
        return vehiculo;
    }

    @Override
    public VehiculoDTO buscar(int id_vehiculo) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        VehiculoDTO vehiculo = null;

        try {
            con = conexion.getConnection();
            String sql = "select * "
                    + "from Vehiculo where id_vehiculo = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, id_vehiculo);

            rs = sentencia.executeQuery();

            int id_vehiculo_db;
            int id_titular_db;
            int id_aseguradora_db;
            String patente_db;
            String marca_db;
            String modelo_db;
            String nro_poliza_db;
            
            while (rs.next()) {
                id_vehiculo_db = rs.getInt("id_vehiculo"); 
                id_titular_db = rs.getInt("id_titular");
                id_aseguradora_db = rs.getInt("id_aseguradora");
                patente_db = rs.getString("patente"); 
                marca_db = rs.getString("marca");   
                modelo_db = rs.getString("modelo");   
                nro_poliza_db = rs.getString("nro_poliza");   

                vehiculo = new VehiculoDTO(id_vehiculo_db, id_titular_db, id_aseguradora_db, patente_db, marca_db, modelo_db, nro_poliza_db);
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
        return vehiculo;
    }

    @Override
    public List<VehiculoDTO> listar() {
        return _listar(0);
    }

    @Override
    public List<VehiculoDTO> listar(TitularDTO titular) {
        return _listar(titular.getId_titular());
    }

    private List<VehiculoDTO> _listar(int id_titular) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        List<VehiculoDTO> lista = new ArrayList<>();

        try {
            con = conexion.getConnection();
            String sql = "SELECT V.*, T.nombre || ' ' || T.apellido AS nombreTitular, A.nombre AS nombreAseguradora " +
                    "FROM Vehiculo V " +
                    "INNER JOIN Titular T ON V.id_titular = T.id_titular " +
                    "INNER JOIN Aseguradora A ON V.id_aseguradora = A.id_aseguradora";

            if (id_titular > 0) {
                sql += " WHERE V.id_titular = ?";
            }

            sql += " ORDER BY V.id_vehiculo DESC";

            sentencia = con.prepareStatement(sql);

            if (id_titular > 0) {
                sentencia.setInt(1, id_titular);
            }

            rs = sentencia.executeQuery();

            while (rs.next()) {
                VehiculoDTO vehiculo = new VehiculoDTO();
                vehiculo.setId_vehiculo(rs.getInt("id_vehiculo"));
                vehiculo.setId_titular(rs.getInt("id_titular"));
                vehiculo.setId_aseguradora(rs.getInt("id_aseguradora"));
                vehiculo.setPatente(rs.getString("patente"));
                vehiculo.setMarca(rs.getString("marca"));
                vehiculo.setModelo(rs.getString("modelo"));
                vehiculo.setPoliza(rs.getString("nro_poliza"));

                
                vehiculo.setNombreTitular(rs.getString("nombreTitular"));
                vehiculo.setNombreAseguradora(rs.getString("nombreAseguradora"));

                lista.add(vehiculo);
            }

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
        return lista;
    }

    @Override
    public boolean insertar(int id_titular, int id_aseguradora, String patente, String marca, String modelo, String nro_poliza) {
        Connection con = null;
        PreparedStatement sentencia = null;

        try {
            con = conexion.getConnection();
            String sql = "INSERT INTO Vehiculo (id_titular, id_aseguradora, patente, marca, modelo, nro_poliza) VALUES (?, ?, ?, ?, ?, ?)";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, id_titular);
            sentencia.setInt(2, id_aseguradora);
            sentencia.setString(3, patente);
            sentencia.setString(4, marca);
            sentencia.setString(5, modelo);
            sentencia.setString(6, nro_poliza);

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
    public boolean modificar(int id_vehiculo, int id_titular, int id_aseguradora, String patente, String marca, String modelo, String nro_poliza) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean borrar(int id_vehiculo) {
        Connection con = null;
        PreparedStatement sentencia = null;

        try {
            con = conexion.getConnection();
            String sql = "DELETE FROM Vehiculo WHERE id_vehiculo = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, id_vehiculo);

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
