
package com.seminarioprogramaciontp3.modelo;

import com.seminarioprogramaciontp3.dao.FactoryDAO;
import com.seminarioprogramaciontp3.dao.ReparacionesDAO;
import com.seminarioprogramaciontp3.dto.ServicioDTO;
import java.util.List;

public class Servicio extends Modelo{
     
    private final FactoryDAO factoryDao;
    private final ReparacionesDAO reparacionesDao;

    public Servicio() {
        factoryDao = FactoryDAO.getFactory("Factory");
        reparacionesDao = factoryDao.getServicioDao();
    }

    public ServicioDTO buscar(String nombre) {
        ServicioDTO servicio = reparacionesDao.buscar(nombre);
        return servicio;
    }
    
    public ServicioDTO buscar(int id_servicio) {
        ServicioDTO servicio = reparacionesDao.buscar(id_servicio);
        return servicio;
    }

    public List<ServicioDTO> listar() {
        List<ServicioDTO> listadoServicios = reparacionesDao.listar();
        return listadoServicios;
    }

    public boolean insertar(int id_especialidad, String nombre, String descripcion, int tiempo) {
        return reparacionesDao.insertar(id_especialidad, nombre, descripcion, tiempo);
    }

    public boolean modificar(int id_servicio, int id_especialidad, String nombre, String descripcion, int tiempo) {
        return reparacionesDao.modificar(id_servicio, id_especialidad, nombre, descripcion, tiempo);
    }

    public boolean borrar(int id_servicio) {
        return reparacionesDao.borrar(id_servicio);
    }
    
   
    
    
}

