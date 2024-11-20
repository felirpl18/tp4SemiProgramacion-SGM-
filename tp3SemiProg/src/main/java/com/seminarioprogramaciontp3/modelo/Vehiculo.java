
package com.seminarioprogramaciontp3.modelo;

import com.seminarioprogramaciontp3.dao.FactoryDAO;
import com.seminarioprogramaciontp3.dao.VehiculoDAO;
import com.seminarioprogramaciontp3.dto.TitularDTO;
import com.seminarioprogramaciontp3.dto.VehiculoDTO;
import java.util.List;

public class Vehiculo extends Modelo{
     
    private final FactoryDAO factoryDao;
    private final VehiculoDAO vehiculoDao;

    public Vehiculo() {
        factoryDao = FactoryDAO.getFactory("Factory");
        vehiculoDao = factoryDao.getVehiculoDao();
    }

    public VehiculoDTO buscar(String nombre) {
        VehiculoDTO vehiculo = vehiculoDao.buscar(nombre);
        return vehiculo;
    }
    
    public VehiculoDTO buscar(int id_vehiculo) {
        VehiculoDTO vehiculo = vehiculoDao.buscar(id_vehiculo);
        return vehiculo;
    }

    public List<VehiculoDTO> listar() {
        List<VehiculoDTO> listadoVehiculoes = vehiculoDao.listar();
        return listadoVehiculoes;
    }

    public List<VehiculoDTO> listar(TitularDTO titular) {
        List<VehiculoDTO> listadoVehiculoes = vehiculoDao.listar(titular);
        return listadoVehiculoes;
    }
    
    public boolean insertar(int id_titular, int id_aseguradora, String patente, String marca, String modelo, String nro_poliza) {
        return vehiculoDao.insertar(id_titular, id_aseguradora, patente, marca, modelo, nro_poliza);
    }

    public boolean modificar(int id_vehiculo, int id_titular, int id_aseguradora, String patente, String marca, String modelo, String nro_poliza) {
        return vehiculoDao.modificar(id_vehiculo, id_titular, id_aseguradora, patente, marca, modelo, nro_poliza);
    }

    public boolean borrar(int id_vehiculo) {
        return vehiculoDao.borrar(id_vehiculo);
    }
    
   
    
    
}

