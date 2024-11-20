
package com.seminarioprogramaciontp3.modelo;

import com.seminarioprogramaciontp3.dao.FactoryDAO;
import com.seminarioprogramaciontp3.dao.HorarioDAO;
import com.seminarioprogramaciontp3.dto.HorarioDTO;
import com.seminarioprogramaciontp3.dto.MecanicoDTO;
import java.time.LocalTime;
import java.util.List;


public class Horario extends Modelo{
     
    private final FactoryDAO factoryDao;
    private final HorarioDAO horarioDao;

    public Horario() {
        factoryDao = FactoryDAO.getFactory("Factory");
        horarioDao = factoryDao.getHorarioDao();
    }

    public HorarioDTO buscar(String dia) {
        HorarioDTO horario = horarioDao.buscar(dia);
        return horario;
    }
    
    public HorarioDTO buscar(int id_horario) {
        HorarioDTO horario = horarioDao.buscar(id_horario);
        return horario;
    }

    public List<HorarioDTO> listar() {
        List<HorarioDTO> listadoHorarioes = horarioDao.listar();
        return listadoHorarioes;
    }
    
    public List<HorarioDTO> listar(MecanicoDTO mecanico) {
        List<HorarioDTO> listadoHorarioes = horarioDao.listar(mecanico);
        return listadoHorarioes;
    }

    public boolean insertar(int id_mecanico, String dia, LocalTime hora_inicio, LocalTime hora_fin) {
        return horarioDao.insertar(id_mecanico, dia, hora_inicio, hora_fin);
    }

    public boolean modificar(int id_horario, int id_mecanico, String dia, LocalTime hora_inicio, LocalTime hora_fin){
        return horarioDao.modificar(id_horario, id_mecanico, dia, hora_inicio, hora_fin);
    }

    public boolean borrar(int id_horario) {
        return horarioDao.borrar(id_horario);
    }
    
   
    
    
}

