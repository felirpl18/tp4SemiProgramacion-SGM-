
package com.seminarioprogramaciontp3.dao;
import com.seminarioprogramaciontp3.dto.TitularDTO;
import com.seminarioprogramaciontp3.dto.VehiculoDTO;
import java.util.List;


public interface VehiculoDAO {
   
     VehiculoDTO buscar(String patente);
     
     VehiculoDTO buscar(int id_vehiculo);
     
     List<VehiculoDTO> listar();
     
     List<VehiculoDTO> listar(TitularDTO titular);
     
     boolean insertar(int id_titular, int id_aseguradora, String patente, String marca, String modelo, String nro_poliza);
     
     boolean modificar(int id_vehiculo, int id_titular, int id_aseguradora, String patente, String marca, String modelo, String nro_poliza);
     
     boolean borrar(int id_vehiculo);
     
     void cerrarConexion();
}