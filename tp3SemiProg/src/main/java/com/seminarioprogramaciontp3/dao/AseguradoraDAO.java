package com.seminarioprogramaciontp3.dao;

import com.seminarioprogramaciontp3.dto.AseguradoraDTO;
import java.util.List;

public interface AseguradoraDAO {

    AseguradoraDTO buscar(int id_aseguradora);

    List<AseguradoraDTO> listar();

    boolean insertar(String nombre, String email, String cuit);

    boolean actualizar(int id_aseguradora, String nombre, String email, String cuit);

    boolean borrar(int id_aseguradora);

    void cerrarConexion();
}
