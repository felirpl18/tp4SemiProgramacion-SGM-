
package com.seminarioprogramaciontp3.dao;

import com.seminarioprogramaciontp3.dto.EspecialidadDTO;
import java.util.List;

public interface EspecialidadDAO {
     
     EspecialidadDTO buscar(String nombre);
     
     EspecialidadDTO buscar(int id_especialidad);
     
     List<EspecialidadDTO> listar();
     
     boolean insertar(String nombre, String descripcion);
     
     boolean modificar(int id_especialidad, String nombre, String descripcion);
     
     boolean borrar(int id_especialidad);
     
     void cerrarConexion();
}
