
package com.seminarioprogramaciontp3.modelo;

import com.seminarioprogramaciontp3.dao.FactoryDAO;
import com.seminarioprogramaciontp3.dao.TurnoDAO;
import com.seminarioprogramaciontp3.dto.MecanicoDTO;
import com.seminarioprogramaciontp3.dto.TurnoDTO;
import java.time.LocalDate;
import java.util.Date;
import java.time.LocalTime;
import java.util.List;


public class Turno extends Modelo{
     
    private final FactoryDAO factoryDao;
    private final TurnoDAO turnoDao;

    public Turno() {
        factoryDao = FactoryDAO.getFactory("Factory");
        turnoDao = factoryDao.getTurnoDao();
    }    
    
    public TurnoDTO buscar(int id_turno) {
        TurnoDTO turno = turnoDao.buscar(id_turno);        
        return turno;
    }

    public List<TurnoDTO> listar() {
        List<TurnoDTO> listadoTurnos = turnoDao.listar();
        return listadoTurnos;
    }
    
    public List<TurnoDTO> listar(MecanicoDTO mecanico) {
        List<TurnoDTO> listadoTurnos = turnoDao.listar(mecanico);
        return listadoTurnos;
    }
    
    public List<TurnoDTO> listar(LocalDate fecha) {
        List<TurnoDTO> listadoTurnos = turnoDao.listar(fecha);
        return listadoTurnos;
    }
    
    public List<TurnoDTO> listar(MecanicoDTO mecanico, LocalDate fecha) {
        List<TurnoDTO> listadoTurnos = turnoDao.listar(mecanico,fecha);
        return listadoTurnos;
    }

    public boolean insertar(LocalDate dia_atencion, LocalTime hora_atencion, Boolean asistencia, int id_vehiculo, int id_servicio, int id_mecanico) {
        return turnoDao.insertar(dia_atencion, hora_atencion, asistencia, id_vehiculo, id_servicio, id_mecanico);
    }

    public boolean modificar(int id_turno, Date dia_atencion, LocalTime hora_atencion, Boolean asistencia, int id_vehiculo, int id_servicio, int id_mecanico) {
        return turnoDao.modificar(id_turno, dia_atencion, hora_atencion, asistencia, id_vehiculo, id_servicio, id_mecanico);
    }

    public boolean modificarAsistencia(int id_turno, Boolean asistencia) {
        return turnoDao.modificarAsistencia(id_turno, asistencia);
    }
    
    public boolean borrar(int id_turno) {
        return turnoDao.borrar(id_turno);
    }
   
    
}

