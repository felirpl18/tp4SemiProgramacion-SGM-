
package com.seminarioprogramaciontp3.dao;
import com.seminarioprogramaciontp3.dto.MecanicoDTO;
import com.seminarioprogramaciontp3.dto.TurnoDTO;
import java.time.LocalDate;
import java.util.List;
import java.time.LocalTime;
import java.util.Date;

public interface TurnoDAO {
     
     TurnoDTO buscar(int id_turno);
     
     List<TurnoDTO> listar();
     
     List<TurnoDTO> listar(MecanicoDTO mecanico);
     
     List<TurnoDTO> listar(LocalDate fecha);
     
     List<TurnoDTO> listar(MecanicoDTO mecanico, LocalDate fecha);
     
     boolean insertar(LocalDate dia_atencion, LocalTime hora_atencion, Boolean asistencia, int id_vehiculo, int id_servicio, int id_mecanico);
     
     boolean modificar(int id_turno, Date dia_atencion, LocalTime hora_atencion, Boolean asistencia, int id_vehiculo, int id_servicio, int id_mecanico);
     
     boolean modificarAsistencia(int id_turno, Boolean asistencia);
     
     boolean borrar(int id_turno);
     
     void cerrarConexion();
}
