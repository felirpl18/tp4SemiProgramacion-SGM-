package com.seminarioprogramaciontp3.dao;
import com.seminarioprogramaciontp3.dto.EspecialidadDTO;
import com.seminarioprogramaciontp3.dto.MecanicoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MecanicoSQL implements MecanicoDAO {

    ConexionBD conexion = null;

    public MecanicoSQL() {
        conexion = ConexionBD.getInstancia();
    }

    @Override
    public MecanicoDTO Buscar(int idMecanico, int idEspecialidad) {
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        MecanicoDTO mecanico = null;
        String sql = "select * from Mecanico";
        try {
            Connection con = conexion.getConnection();
            if (idEspecialidad > 0) {
                sql = sql + " where id_mecanico = ? && id_especialidad = ?";
                sentencia = con.prepareStatement(sql);
                sentencia.setInt(1, idMecanico);
                sentencia.setInt(2, idEspecialidad);
            } else {
                sql = sql + " where id_mecanico = ?";
                sentencia = con.prepareStatement(sql);
                sentencia.setInt(1, idMecanico);
            }
            rs = sentencia.executeQuery();

            while (rs.next()) {
                mecanico = new MecanicoDTO(rs.getInt("id_mecanico"),
                        rs.getInt("id_especialidad"),
                        rs.getString("legajo"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("nro_Doc"),
                        rs.getDouble("carga_horaria"));
            }

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                rs.close();
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            } catch (NullPointerException ex) {
                System.err.println(ex);
            }
        }
        return mecanico;
    }

    @Override
    public List<MecanicoDTO> listar(EspecialidadDTO especialidad) {
        return _listar(especialidad.getId_especialidad());
    }

    @Override
    public List<MecanicoDTO> listar() {
        return _listar(0);
    }

    private List<MecanicoDTO> _listar(int id_especialidad) {
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        List<MecanicoDTO> lista = new ArrayList<>();

        try {
            Connection con = conexion.getConnection();

            String sql = "select * from Mecanico";
            if (id_especialidad > 0) {
                sql += " WHERE id_especialidad = ? ";
            }
            sql += " order by id_mecanico DESC";

            sentencia = con.prepareStatement(sql);

            if (id_especialidad > 0) {
                sentencia.setInt(1, id_especialidad);
            }

            rs = sentencia.executeQuery();

            while (rs.next()) {
                lista.add(new MecanicoDTO(rs.getInt("id_mecanico"),
                        rs.getInt("id_especialidad"),
                        rs.getString("legajo"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("nro_Doc"),
                        rs.getDouble("carga_horaria")));
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                rs.close();
                sentencia.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            } catch (NullPointerException ex) {
                System.err.println(ex);
            }
        }
        return lista;
    }

    @Override
    public boolean Insertar(int idEspecialidad, String legajo, String nombre
            , String apellido, String nroDoc
            , double cargaHoraria) {
        PreparedStatement sentencia = null;
        Connection con = null;

        try {
            con = conexion.getConnection();
            String sql = "INSERT INTO Mecanico (id_especialidad,legajo, nombre, apellido, nro_Doc, carga_horaria) VALUES (?, ?, ?, ?, ?, ?)";
            sentencia = con.prepareStatement(sql);
            sentencia.setString(1, legajo);
            sentencia.setString(3, nombre);
            sentencia.setString(4, apellido);
            sentencia.setString(2, nroDoc);
            sentencia.setDouble(5, cargaHoraria);

            int filasAfectadas = sentencia.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {

        }
    }

    @Override
    public boolean Modificar(int idMecanico, int idEspecilidad, String legajo, String nombre, String apellido, String nroDoc, double cargaHoraria) {
        PreparedStatement sentencia = null;
        Connection con = null;

        try {
            con = conexion.getConnection();
            String sql = "UPDATE Mecanico SET id_especialidad=?, legajo=?, nombre=?, apellido=?, nro_Doc=?, carga_horaria=? WHERE id_mecanico=?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, idEspecilidad);
            sentencia.setString(2, legajo);
            sentencia.setString(3, nombre);
            sentencia.setString(4, apellido);
            sentencia.setString(5, nroDoc);
            sentencia.setDouble(6, cargaHoraria);
            sentencia.setInt(7, idMecanico);

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
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
    }

    @Override
    public boolean Borrar(int id_mecanico) {
        Connection con = null;
        PreparedStatement sentencia = null;

        try {
            con = conexion.getConnection();
            String sql = "DELETE FROM Mecanico WHERE id_mecanico = ?";
            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, id_mecanico);

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

