
package com.seminarioprogramaciontp3.dao;

import com.seminarioprogramaciontp3.dto.HorarioDTO;
import com.seminarioprogramaciontp3.dto.MecanicoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class HorarioSQL implements HorarioDAO {

    private ConexionBD conexion = null;

    public HorarioSQL() {
        conexion = ConexionBD.getInstancia();
    }

    @Override
    public HorarioDTO buscar(String dia) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        HorarioDTO horario = null;

        try {
            con = conexion.getConnection(); 
            String sql = "select * "
                    + "from Horario where dia = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, dia); 

            rs = sentencia.executeQuery(); 

            
            int id_horario_db;
            int id_mecanico_db;
            String dia_db;
            LocalTime hora_inicio_db;
            LocalTime hora_fin_db;

            while (rs.next()) {
                id_horario_db = rs.getInt("id_horario"); 
                id_mecanico_db = rs.getInt("id_mecanico"); 
                dia_db = rs.getString("dia");
                hora_inicio_db = LocalTime.parse(rs.getString("hora_inicio"));
                hora_fin_db = LocalTime.parse(rs.getString("hora_fin"));

                horario = new HorarioDTO(id_horario_db, id_mecanico_db, dia_db, hora_inicio_db, hora_fin_db);
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
        return horario;
    }

    @Override
    public HorarioDTO buscar(int id_horario) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        HorarioDTO horario = null;

        try {
            con = conexion.getConnection();
            String sql = "select * "
                    + "from Horario where id_horario = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, id_horario);

            rs = sentencia.executeQuery();

            
            int id_horario_db;
            int id_mecanico_db;
            String dia_db;
            LocalTime hora_inicio_db;
            LocalTime hora_fin_db;

            while (rs.next()) {
                id_horario_db = rs.getInt("id_horario"); 
                id_mecanico_db = rs.getInt("id_mecanico"); 
                dia_db = rs.getString("dia");
                hora_inicio_db = LocalTime.parse(rs.getString("hora_inicio"));
                hora_fin_db = LocalTime.parse(rs.getString("hora_fin"));

                horario = new HorarioDTO(id_horario_db, id_mecanico_db, dia_db, hora_inicio_db, hora_fin_db);

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
        return horario;
    }

    @Override
    public List<HorarioDTO> listar() {
        return this._listar(0);
    }

    @Override
    public List<HorarioDTO> listar(MecanicoDTO mecanico) {
        return this._listar(mecanico.getIdMecanico());
    }

    
    private List<HorarioDTO> _listar(int id_mecanico) {
        Connection con = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        HorarioDTO horario = null;
        List<HorarioDTO> lista = new ArrayList<>();

        try {
            con = conexion.getConnection();
            String sql = "select * from Horario";
            if (id_mecanico > 0) {
                sql += " WHERE id_mecanico = ? ";
            }
            sql += " order by id_horario DESC";
            sentencia = con.prepareStatement(sql);
            if (id_mecanico > 0) {
                sentencia.setInt(1, id_mecanico);
            }

            rs = sentencia.executeQuery();

            
            int id_horario_db;
            int id_mecanico_db;
            String dia_db;
            LocalTime hora_inicio_db;
            LocalTime hora_fin_db;

            while (rs.next()) {
                id_horario_db = rs.getInt("id_horario"); 
                id_mecanico_db = rs.getInt("id_mecanico"); 
                dia_db = rs.getString("dia");
                hora_inicio_db = LocalTime.parse(rs.getString("hora_inicio"));
                hora_fin_db = LocalTime.parse(rs.getString("hora_fin"));

                horario = new HorarioDTO(id_horario_db, id_mecanico_db, dia_db, hora_inicio_db, hora_fin_db);
                lista.add(horario);
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
    public boolean insertar(int id_mecanico, String dia, LocalTime hora_inicio, LocalTime hora_fin) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean modificar(int id_horario, int id_mecanico, String dia, LocalTime hora_inicio, LocalTime hora_fin) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean borrar(int id_horario) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void cerrarConexion() {
        conexion.desconectar();
    }

}
