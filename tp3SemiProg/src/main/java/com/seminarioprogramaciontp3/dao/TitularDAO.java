
package com.seminarioprogramaciontp3.dao;
import com.seminarioprogramaciontp3.dto.TitularDTO;

import java.util.List;

public interface TitularDAO {
     
     TitularDTO buscar(String nombre, String apellido);
     
     TitularDTO buscar(int legajo);
     
     List<TitularDTO> listar();
     
     boolean insertar(String nombre, String apellido, String nro_doc, String telefono);
     
     boolean modificar(int id_titular, String nombre, String apellido, String nro_doc, String telefono);
     
     boolean borrar(int id_titular);
     
     void cerrarConexion();
}
