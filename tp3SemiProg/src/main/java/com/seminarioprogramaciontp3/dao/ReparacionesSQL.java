
package com.seminarioprogramaciontp3.dao;

import com.seminarioprogramaciontp3.dto.EspecialidadDTO;
import com.seminarioprogramaciontp3.dto.ServicioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReparacionesSQL implements ReparacionesDAO {

    private ConexionBD conexion = null;

    public ReparacionesSQL() {
        conexion = ConexionBD.getInstancia();
    }

    @Override
    public ServicioDTO buscar(String nombre) {
        Connection con = null; 
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        ServicioDTO servicio = null;

        try {
            con = conexion.getConnection(); 
            String sql = "select * "
                       + "from Servicio where nombre = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, nombre); 

            rs = sentencia.executeQuery(); 

            
            int id_servicio_db;
            int id_especialidad_db;
            String nombre_db;
            String descripcion_db;
            int tiempo_db;

            while (rs.next()) {
                id_servicio_db = rs.getInt("id_servicio"); 
                id_especialidad_db = rs.getInt("id_especialidad");
                nombre_db = rs.getString("nombre");
                descripcion_db = rs.getString("descripcion"); 
                tiempo_db = rs.getInt("tiempo");

                servicio = new ServicioDTO(id_servicio_db, id_especialidad_db, nombre_db, descripcion_db, tiempo_db);
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
        return servicio;
    }

    @Override
    public ServicioDTO buscar(int id_servicio) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        ServicioDTO servicio = null;

        try {
            con = conexion.getConnection();
            String sql = "select * "
                    + "from Servicio where id_servicio = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, id_servicio);

            rs = sentencia.executeQuery();

            int id_servicio_db;
            int id_especialidad_db;
            String nombre_db;
            String descripcion_db;
            int tiempo_db;

            while (rs.next()) {
                id_servicio_db = rs.getInt("id_servicio");
                id_especialidad_db = rs.getInt("id_especialidad");
                nombre_db = rs.getString("nombre");
                descripcion_db = rs.getString("descripcion");
                tiempo_db = rs.getInt("id_tiempo");

                servicio = new ServicioDTO(id_servicio_db, id_especialidad_db, nombre_db, descripcion_db, tiempo_db);
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
        return servicio;
    }

    @Override
    public List<ServicioDTO> listar(EspecialidadDTO especialidad) {

        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        ServicioDTO servicio = null;

        List<ServicioDTO> lista = new ArrayList<>();
        
        try {
            con = conexion.getConnection();
            String sql = "select * "
                    + "from Servicio order by id_servicio DESC"
                    + " WHERE id_especialidad = ? ";

            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, especialidad.getId_especialidad());

            rs = sentencia.executeQuery();

        
            int id_servicio_db;
            String nombre_db;
            String descripcion_db;
            int tiempo_db;
            int id_especialidad_db;



            while (rs.next()) {
                id_servicio_db = rs.getInt("id_servicio");
                nombre_db = rs.getString("nombre");
                descripcion_db = rs.getString("descripcion");
                tiempo_db = rs.getInt("tiempo");
                id_especialidad_db = rs.getInt("id_especialidad");
                servicio = new ServicioDTO(id_servicio_db, id_especialidad_db, nombre_db, descripcion_db, tiempo_db);
                
                lista.add(servicio);
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
    public List<ServicioDTO> listar() {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        ServicioDTO servicio = null;
        EspecialidadDTO especialidad = null;
        List<ServicioDTO> lista = new ArrayList<>();
        
        try {
            con = conexion.getConnection();
           
            String sql = "SELECT Servicio.*,Especialidad.id_especialidad,Especialidad.nombre as nombre_especialidad,"
                    + " Especialidad.descripcion as descripcion_especialidad"
                    + " FROM Servicio, Especialidad"
                    + " WHERE Servicio.id_especialidad = Especialidad.id_especialidad";
            sentencia = con.prepareStatement(sql);

            rs = sentencia.executeQuery();

            
            int id_servicio_db;
            String nombre_db;
            String descripcion_db;
            int tiempo_db;
            
            
            int id_especialidad_db;
            String nombre_especialidad_db;
            String descripcion_especialidad_db;


            while (rs.next()) {
                id_servicio_db = rs.getInt("id_servicio");
                nombre_db = rs.getString("nombre");
                descripcion_db = rs.getString("descripcion");
                tiempo_db = rs.getInt("tiempo");
                
                id_especialidad_db = rs.getInt("id_especialidad");
                nombre_especialidad_db = rs.getString("nombre_especialidad");;
                descripcion_especialidad_db = rs.getString("descripcion_especialidad");;
                
                especialidad = new EspecialidadDTO(id_especialidad_db, nombre_especialidad_db, descripcion_especialidad_db);
                servicio = new ServicioDTO(id_servicio_db, nombre_db, descripcion_db, tiempo_db, especialidad);
                
                lista.add(servicio);
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
    public boolean insertar(int id_especialidad, String nombre, String descripcion, int tiempo) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean modificar(int id_servicio, int id_especialidad, String nombre, String descripcion, int tiempo) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean borrar(int id_servicio) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void cerrarConexion() {
        conexion.desconectar();
    }

}
