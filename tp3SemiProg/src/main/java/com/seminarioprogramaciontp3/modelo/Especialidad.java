
package com.seminarioprogramaciontp3.modelo;

import com.seminarioprogramaciontp3.dao.FactoryDAO;
import com.seminarioprogramaciontp3.dao.EspecialidadDAO;
import com.seminarioprogramaciontp3.dao.ReparacionesDAO;
import com.seminarioprogramaciontp3.dto.EspecialidadDTO;
import java.util.List;

public class Especialidad extends Modelo{
     
    private final FactoryDAO factoryDao;
    private final EspecialidadDAO especialidadDao;
    private final ReparacionesDAO reparacionesDao;

    public Especialidad() {
        factoryDao = FactoryDAO.getFactory("Factory");
        especialidadDao = factoryDao.getEspecialidadDao();
        reparacionesDao = factoryDao.getServicioDao();
    }

    public EspecialidadDTO buscar(String nombre) {
        EspecialidadDTO especialidad = especialidadDao.buscar(nombre);
        return especialidad;
    }
    
    public EspecialidadDTO buscar(int id_especialidad) {
        EspecialidadDTO especialidad = especialidadDao.buscar(id_especialidad);
        
        especialidad.setServicios(reparacionesDao.listar(especialidad));
        return especialidad;
    }

    public List<EspecialidadDTO> listar() {
        List<EspecialidadDTO> listadoEspecialidades = especialidadDao.listar();
        return listadoEspecialidades;
    }

    public boolean insertar(String nombre, String descripcion) {
        return especialidadDao.insertar(nombre, descripcion);
    }

    public boolean modificar(int id_especialidad, String nombre, String descripcion) {
        return especialidadDao.modificar(id_especialidad, nombre, descripcion);
    }

    public boolean borrar(int id_especialidad) {
        return especialidadDao.borrar(id_especialidad);
    }
    
   
    
    
}

