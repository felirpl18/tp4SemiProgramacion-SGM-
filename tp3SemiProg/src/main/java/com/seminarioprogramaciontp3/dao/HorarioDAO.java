
package com.seminarioprogramaciontp3.dao;
import com.seminarioprogramaciontp3.dto.HorarioDTO;
import com.seminarioprogramaciontp3.dto.MecanicoDTO;

import java.time.LocalTime;
import java.util.List;

public interface HorarioDAO {
     
     HorarioDTO buscar(String dia);
     
     HorarioDTO buscar(int id_horario);
     
     List<HorarioDTO> listar();
     
     List<HorarioDTO> listar(MecanicoDTO mecanico);
     
     boolean insertar(int id_mecanico, String dia, LocalTime hora_inicio, LocalTime hora_fin);
     
     boolean modificar(int id_horario, int id_mecanico, String dia, LocalTime hora_inicio, LocalTime hora_fin);
     
     boolean borrar(int id_horario);
     
     void cerrarConexion();
}

