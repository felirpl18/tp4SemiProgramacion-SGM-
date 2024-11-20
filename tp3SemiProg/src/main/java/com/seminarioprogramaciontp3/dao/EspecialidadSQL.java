
package com.seminarioprogramaciontp3.dao;

import com.seminarioprogramaciontp3.dto.EspecialidadDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EspecialidadSQL implements EspecialidadDAO {

    private ConexionBD conexion = null;

    public EspecialidadSQL() {
        conexion = ConexionBD.getInstancia();
    }

    @Override
    public EspecialidadDTO buscar(String nombre) {
        Connection con = null; 
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        EspecialidadDTO especialidad = null;

        try {
            con = conexion.getConnection(); 
            String sql = "select * "
                       + "from Especialidad where nombre = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, nombre); 

            rs = sentencia.executeQuery(); 

            
            int id_especialidad_db;
            String nombre_db;
            String descripcion_db;

            while (rs.next()) {
                id_especialidad_db = rs.getInt("id_especialidad"); 
                nombre_db = rs.getString("nombre");
                descripcion_db = rs.getString("descripcion");                

                especialidad = new EspecialidadDTO(id_especialidad_db, nombre_db, descripcion_db);
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
        return especialidad;
    }

    @Override
    public EspecialidadDTO buscar(int id_especialidad) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        EspecialidadDTO especialidad = null;

        try {
            con = conexion.getConnection();
            String sql = "select * "
                    + "from Especialidad where id_especialidad = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, id_especialidad);

            rs = sentencia.executeQuery();

            int id_especialidad_db;
            String nombre_db;
            String descripcion_db;

            while (rs.next()) {
                id_especialidad_db = rs.getInt("id_especialidad");
                nombre_db = rs.getString("nombre");
                descripcion_db = rs.getString("descripcion");

                especialidad = new EspecialidadDTO(id_especialidad_db, nombre_db, descripcion_db);
                
                
                
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
        return especialidad;
    }

    
    @Override
    public List<EspecialidadDTO> listar() {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        EspecialidadDTO especialidad = null;
        List<EspecialidadDTO> lista = new ArrayList<>();
        
        try {
            con = conexion.getConnection();
            String sql = "select * "
                    + "from Especialidad order by id_especialidad DESC";
            sentencia = con.prepareStatement(sql);

            rs = sentencia.executeQuery();

            int id_especialidad_db;
            String nombre_db;
            String descripcion_db;

            while (rs.next()) {
                id_especialidad_db = rs.getInt("id_especialidad");
                nombre_db = rs.getString("nombre");
                descripcion_db = rs.getString("descripcion");

                especialidad = new EspecialidadDTO(id_especialidad_db, nombre_db, descripcion_db);
                lista.add(especialidad);
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
    public boolean insertar(String nombre, String descripcion) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean modificar(int id_especialidad, String nombre, String descripcion) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean borrar(int id_especialidad) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void cerrarConexion() {
        conexion.desconectar();
    }

}
