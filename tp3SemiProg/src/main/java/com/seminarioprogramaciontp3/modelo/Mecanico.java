package com.seminarioprogramaciontp3.modelo;
import com.seminarioprogramaciontp3.dao.FactoryDAO;
import com.seminarioprogramaciontp3.dao.HorarioDAO;
import com.seminarioprogramaciontp3.dao.MecanicoDAO;
import com.seminarioprogramaciontp3.dto.EspecialidadDTO;
import com.seminarioprogramaciontp3.dto.MecanicoDTO;

import java.util.List;


public class Mecanico extends Modelo{
    
    private final FactoryDAO factoryDao;
    private final MecanicoDAO mecanicoDao;
    private final HorarioDAO horarioDao;

    public Mecanico() {
        factoryDao = FactoryDAO.getFactory("Factory");
        mecanicoDao = factoryDao.getMecanicoDao();
        horarioDao = factoryDao.getHorarioDao();
    }
    
    public MecanicoDTO Buscar(int idMecanico
                            , int idEspecialidad) {
        
        
        MecanicoDTO mecanico = mecanicoDao.Buscar(idMecanico, idEspecialidad);
        
        return mecanico;
    }

    public List<MecanicoDTO> listar() {
        return mecanicoDao.listar();
    }

    public List<MecanicoDTO> listar(EspecialidadDTO especialidad) {
        return mecanicoDao.listar(especialidad);
    }
    
    public boolean Insertar(int idEspecilidad, String legajo, String nombre
                    , String apellido, String nroDoc
                    , double cargaHoraria) {
        return mecanicoDao.Insertar(idEspecilidad, legajo, nombre
                                  , apellido, nroDoc
                                  , cargaHoraria);
    }

    public boolean Modificar(int idMecanico, int idEspecilidad, String legajo, String nombre
                           , String apellido, String nroDoc
                           , double cargaHoraria) {
        return mecanicoDao.Modificar(idMecanico, idEspecilidad, legajo, nombre
                                   , apellido, nroDoc
                                   , cargaHoraria);
    }

    public boolean Borrar(int idMecanico) {
        return mecanicoDao.Borrar(idMecanico);
    }
    
   

}
