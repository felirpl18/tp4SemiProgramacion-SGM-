package com.seminarioprogramaciontp3.dao;

import com.seminarioprogramaciontp3.dto.EspecialidadDTO;
import com.seminarioprogramaciontp3.dto.MecanicoDTO;

import java.util.List;

public interface MecanicoDAO {
      
     MecanicoDTO Buscar(int idMecanico, int idEspecialidad);
     
     List<MecanicoDTO> listar();
     
     public List<MecanicoDTO> listar(EspecialidadDTO Especialidad);
     
     boolean Insertar(int idEspecialidad, String legajo, String nombre
                    , String apellido, String nroDoc
                    , double cargaHoraria);
     
     boolean Modificar(int idMecanico, int idEspecialidad, String legajo
                    , String nombre, String apellido
                    , String nroDoc, double cargaHoraria);
     
     boolean Borrar(int idMecanico);

     public void cerrarConexion();


}
