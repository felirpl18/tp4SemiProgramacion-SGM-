
package com.seminarioprogramaciontp3.dao;
import com.seminarioprogramaciontp3.dto.MecanicoDTO;
import com.seminarioprogramaciontp3.dto.ServicioDTO;
import com.seminarioprogramaciontp3.dto.TitularDTO;
import com.seminarioprogramaciontp3.dto.TurnoDTO;
import com.seminarioprogramaciontp3.dto.VehiculoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class TurnoSQL implements TurnoDAO {

    private ConexionBD conexion = null;

    public TurnoSQL() {
        conexion = ConexionBD.getInstancia();
    }    

    @Override
    public TurnoDTO buscar(int id_turno) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        TurnoDTO turno = null;

        try {
            con = conexion.getConnection(); 
            String sql = "select * "
                    + "from Turno where id_turno = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, id_turno); 

            rs = sentencia.executeQuery(); 
            
            
            int id_turno_db;
            String dia_atencion_db;
            LocalTime hora_atencion_db;
            Boolean asistencia_db;
            int id_vehiculo_db;
            int id_servicio_db;
            int id_mecanico_db;
            

            while (rs.next()) {
                id_turno_db = rs.getInt("id_turno"); 
                dia_atencion_db = rs.getString("id_turno");
                hora_atencion_db = LocalTime.parse(rs.getString("hora_atencion"));
                asistencia_db = rs.getBoolean("asistencia");
                id_vehiculo_db = rs.getInt("id_vehiculo");
                id_servicio_db = rs.getInt("id_servicio");
                id_mecanico_db = rs.getInt("id_mecanico");

                turno = new TurnoDTO(id_turno_db, dia_atencion_db, hora_atencion_db, asistencia_db, id_vehiculo_db, id_servicio_db, id_mecanico_db);
                                               
            }

        } catch (SQLException e){
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
        return turno;
    }

    
    @Override
    public List<TurnoDTO> listar() {
        return _listar(0,null);
    }

    @Override
    public List<TurnoDTO> listar(MecanicoDTO mecanico) {
        return _listar(mecanico.getIdMecanico(), null);
    }
    
    
    @Override
    public List<TurnoDTO> listar(LocalDate fecha) {
        return _listar(0, fecha);
    }

    @Override
    public List<TurnoDTO> listar(MecanicoDTO mecanico, LocalDate fecha) {
        return _listar(mecanico.getIdMecanico(), fecha);
    }
    
    
    private List<TurnoDTO> _listar(int id_mecanico, LocalDate fecha){
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        
        TurnoDTO turno = null;
        ServicioDTO servicio = null;
        VehiculoDTO vehiculo = null;
        MecanicoDTO mecanico = null;
        TitularDTO titular = null;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        List<TurnoDTO> lista = new ArrayList<>();
        
        try {
            con = conexion.getConnection(); 
            String sql = "SELECT "+
"Turnos.id_turno, Turnos.id_vehiculo, Turnos.id_mecanico, Turnos.id_servicio, Turnos.asistencia, Turnos.hora_atencion," +
"Turnos.dia_atencion,"+
"Servicio.id_especialidad, Servicio.tiempo AS servicio_tiempo, " +
"Servicio.nombre AS servicio_nombre, Servicio.descripcion AS servicio_descripcion," +
"Vehiculo.id_aseguradora, Vehiculo.id_titular, Vehiculo.marca AS vehiculo_marca, Vehiculo.modelo as vehiculo_modelo," +
"Vehiculo.nro_poliza AS vehiculo_nro_poliza, Vehiculo.patente AS vehiculo_patente," +
"Mecanico.apellido AS mecanico_apellido, Mecanico.nombre AS mecanico_nombre, Mecanico.carga_horaria AS mecanico_carga_horaria," +
"Mecanico.legajo AS mecanico_legajo, Mecanico.nro_doc AS mecanico_nro_doc," +
"Titular.apellido AS titular_apellido, Titular.nombre AS titular_nombre, Titular.id_titular, Titular.nro_doc as titular_nro_doc," +
"Titular.telefono AS titular_telefono " +
"FROM " +
"Turnos,Servicio,Vehiculo,Titular,Mecanico " +
"WHERE " +
"Turnos.id_servicio = Servicio.id_servicio AND  " +
"Turnos.id_mecanico = Mecanico.id_mecanico AND " +
"Turnos.id_vehiculo = Vehiculo.id_vehiculo AND " +
"Vehiculo.id_titular = Titular.id_titular";
            
            if(id_mecanico > 0){
                sql+= " AND Mecanico.id_mecanico = ? ";
            }
            if(fecha != null){
                sql+= " AND Turnos.dia_atencion = \"" + formatter.format(fecha) + "\" ";
            }
            
            sql+= " order by Turnos.id_turno DESC";
            
            sentencia = con.prepareStatement(sql);
            
            if(id_mecanico > 0){
                sentencia.setInt(1, id_mecanico);/*
                if(fecha != null){
                    String diat = fecha.toString();
                    diat = diat + "";
                    sentencia.setString(2, diat);
                }
            }else if(fecha != null){
                sentencia.setString(1, fecha.format(formatter));*/
            }
            
            rs = sentencia.executeQuery();  
                        
            
            int id_turno_db;
            String dia_atencion_db;
            LocalTime hora_atencion_db;
            Boolean asistencia_db;
            int id_vehiculo_db;
            int id_servicio_db;
            int id_mecanico_db;
            
            
            int id_especialidad_db;
            String servicio_nombre_db;
            String servicio_descripcion_db;
            int servicio_tiempo_db;
            
            
            int id_titular_db;
            int id_aseguradora_db;
            String vehiculo_patente_db;
            String vehiculo_marca_db;
            String vehiculo_modelo_db;
            String vehiculo_nro_poliza_db;
 
            
            String titular_nombre_db;
            String titular_apellido_db;
            String titular_telefono_db;
            String titular_nro_doc_db;
            
            

            while (rs.next()) {
                
                id_turno_db = rs.getInt("id_turno"); 
                
                dia_atencion_db = rs.getString("dia_atencion");
                hora_atencion_db = LocalTime.parse(rs.getString("hora_atencion"));
                asistencia_db = rs.getBoolean("asistencia");
                id_vehiculo_db = rs.getInt("id_vehiculo");
                id_servicio_db = rs.getInt("id_servicio");
                id_mecanico_db = rs.getInt("id_mecanico");
                turno = new TurnoDTO(id_turno_db, dia_atencion_db, hora_atencion_db, asistencia_db
                                   , id_vehiculo_db, id_servicio_db, id_mecanico_db);
                
                
                id_servicio_db = rs.getInt("id_servicio"); 
                id_especialidad_db = rs.getInt("id_especialidad");
                servicio_nombre_db = rs.getString("servicio_nombre");
                servicio_descripcion_db = rs.getString("servicio_descripcion"); 
                servicio_tiempo_db = rs.getInt("servicio_tiempo");

                servicio = new ServicioDTO(id_servicio_db, id_especialidad_db, servicio_nombre_db,
                        servicio_descripcion_db, servicio_tiempo_db);
                turno.setServicio(servicio);
                
                
                id_titular_db = rs.getInt("id_titular");
                id_aseguradora_db = rs.getInt("id_aseguradora");
                vehiculo_patente_db = rs.getString("vehiculo_patente"); 
                vehiculo_marca_db = rs.getString("vehiculo_marca");   
                vehiculo_modelo_db = rs.getString("vehiculo_modelo");   
                vehiculo_nro_poliza_db = rs.getString("vehiculo_nro_poliza");
                
                vehiculo = new VehiculoDTO(id_vehiculo_db, id_titular_db,
                        id_aseguradora_db,vehiculo_patente_db,vehiculo_marca_db,
                        vehiculo_modelo_db,vehiculo_nro_poliza_db);
                turno.setVehiculo(vehiculo);
                
                
                titular_nombre_db = rs.getString("titular_nombre");
                titular_apellido_db = rs.getString("titular_apellido");
                titular_telefono_db = rs.getString("titular_telefono");
                titular_nro_doc_db = rs.getString("titular_nro_doc");
                 
                titular = new TitularDTO(id_titular_db, titular_nombre_db,
                        titular_apellido_db,
                        titular_nro_doc_db,titular_telefono_db);
                turno.setTitular(titular);
                
                
                mecanico = new MecanicoDTO(
                        rs.getInt("id_mecanico"),
                        rs.getInt("id_especialidad"),
                        rs.getString("mecanico_legajo"),
                        rs.getString("mecanico_nombre"),
                        rs.getString("mecanico_apellido"),
                        rs.getInt("mecanico_nro_doc"),
                        rs.getDouble("mecanico_carga_horaria")
                );
                turno.setMecanico(mecanico);
                lista.add(turno);
            }

        } catch (SQLException e){
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
    public boolean insertar(LocalDate dia_atencion, LocalTime hora_atencion, Boolean asistencia, int id_vehiculo, int id_servicio, int id_mecanico) {
        
        Connection con = null;
        PreparedStatement sentencia = null;
        int resultado = 0;
        try {
            conexion.desconectar();
            conexion = ConexionBD.getInstancia();
            con = conexion.getConnection();
            String sql = "insert into turnos"
                    + " (dia_atencion, hora_atencion, asistencia, id_vehiculo, id_servicio, id_mecanico)"
                    + " values (?,?,?,?,?,?)";
            
            sentencia = con.prepareStatement(sql);
            
            sentencia.setString(1, dia_atencion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            sentencia.setString(2, hora_atencion.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
            sentencia.setInt(3, (asistencia ? 1 : 0));
            sentencia.setInt(4, id_vehiculo);
            sentencia.setInt(5, id_servicio);
            sentencia.setInt(6, id_mecanico);

            resultado = sentencia.executeUpdate();
            con.commit();
            conexion.desconectar();
            conexion = ConexionBD.getInstancia();
            
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
        
        return (resultado > 0);
    }

    @Override
    public boolean modificar(int id_turno, Date dia_atencion, LocalTime hora_atencion, Boolean asistencia, int id_vehiculo, int id_servicio, int id_mecanico) {
        
        Connection con = null;
        PreparedStatement sentencia = null;

        try {
            con = conexion.getConnection();
            String sql = "update turnos set dia_atencion=?,hora_atencion=?,asistencia=?,id_vehiculo=?,id_servicio=?,id_mecanico=? where id_turno=?";
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, new SimpleDateFormat("yyyy-MM-dd").format(dia_atencion));
            sentencia.setString(2, new SimpleDateFormat("hh:mm:ss").format(hora_atencion));
            sentencia.setInt(3, id_vehiculo);
            sentencia.setInt(4, id_servicio);
            sentencia.setInt(5, id_mecanico);
            sentencia.setInt(6, id_turno);

            int resultado = sentencia.executeUpdate();

            return (resultado > 0);
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
    }

    @Override
    public boolean modificarAsistencia(int id_turno, Boolean asistencia) {
        
        Connection con = null;
        PreparedStatement sentencia = null;

        try {
            con = conexion.getConnection();
            String sql = "update turnos set asistencia=? where id_turno=?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, (asistencia ? 1 : 0));
            sentencia.setInt(2, id_turno);

            int resultado = sentencia.executeUpdate();
            con.commit();
            conexion.desconectar();
            conexion = ConexionBD.getInstancia();
            
            return (resultado > 0);
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
    }

    
    @Override
    public boolean borrar(int id_turno) {
        
        Connection con = null;
        PreparedStatement sentencia = null;

        try {
            con = conexion.getConnection();
            String sql = "delete from turnos where id_turno = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, id_turno);

            int resultado = sentencia.executeUpdate();
            con.commit();
            conexion.desconectar();
            conexion = ConexionBD.getInstancia();
            
            return (resultado > 0);
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                sentencia.close();
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

