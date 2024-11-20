
package com.seminarioprogramaciontp3.dao;

import com.seminarioprogramaciontp3.dto.EspecialidadDTO;
import com.seminarioprogramaciontp3.dto.ServicioDTO;

import java.util.List;


public interface ReparacionesDAO {
     
     ServicioDTO buscar(String nombre);
     
     ServicioDTO buscar(int id_servicio);
     
     List<ServicioDTO> listar(EspecialidadDTO especialidad);
     
     List<ServicioDTO> listar();
     
     boolean insertar(int id_especialidad, String nombre, String descripcion, int tiempo);
     
     boolean modificar(int id_servicio, int id_especialidad, String nombre, String descripcion, int tiempo);
     
     boolean borrar(int id_servicio);
     
     void cerrarConexion();
}
