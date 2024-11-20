package com.seminarioprogramaciontp3;

import com.seminarioprogramaciontp3.dao.TurnoSQL;
import com.seminarioprogramaciontp3.dto.MecanicoDTO;
import com.seminarioprogramaciontp3.dto.ServicioDTO;
import com.seminarioprogramaciontp3.dto.TitularDTO;
import com.seminarioprogramaciontp3.dto.TurnoDTO;
import com.seminarioprogramaciontp3.dto.VehiculoDTO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class JUnitTest {

    static TurnoSQL turnoDao;

    public JUnitTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        turnoDao = new TurnoSQL();
    }

    void AssertTurno(TurnoDTO turno) {
        MecanicoDTO mecanico = turno.getMecanico();
        ServicioDTO servicio = turno.getServicio();
        TitularDTO titular = turno.getTitular();
        VehiculoDTO vehiculo = turno.getVehiculo();

    }

    @Test
    public void TestNuevoTurno() {
        LocalDate dia_atencion = LocalDate.of(2024, 11, 8);
        LocalTime hora_atencion = LocalTime.of(14,00);
        Boolean asistencia = false;
        int id_vehiculo = 1;
        int id_servicio = 1;
        int id_mecanico = 1;
        boolean resultado = turnoDao.insertar(dia_atencion, hora_atencion,
                asistencia, id_vehiculo, id_servicio, id_mecanico);
        assertTrue(resultado);

        List<TurnoDTO> listTurnos = turnoDao.listar();
        TurnoDTO turno = listTurnos.get(listTurnos.size()-1);
        AssertTurno(turno);
    }

    @Test
    public void TestListarTurnos() {
        List<TurnoDTO> listTurnos = turnoDao.listar();
        assertTrue(!listTurnos.isEmpty());

        for (TurnoDTO turno : listTurnos) {
            System.out.println(turno);
            AssertTurno(turno);
        }
    }


    @Test
    public void TestTurnosPorFecha() {
        LocalDate fecha = LocalDate.of(2024, 07, 21);
        List<TurnoDTO> listTurnos = turnoDao.listar(fecha);
        assertTrue(!listTurnos.isEmpty());

        for (TurnoDTO turno : listTurnos) {
            System.out.println(turno);
            AssertTurno(turno);
        }
    }
    @Test
    public void TestEliminarTurno() {
        List<TurnoDTO> listTurnos = turnoDao.listar();
        assertTrue(!listTurnos.isEmpty(), "La lista de turnos no debería estar vacía");
        TurnoDTO turnoConIdMasAlto = listTurnos.stream()
                .max((turno1, turno2) -> Integer.compare(turno1.getId_turno(), turno2.getId_turno()))
                .orElse(null);
        assertTrue(turnoConIdMasAlto != null, "Debe haber al menos un turno en la lista");

        boolean resultado = turnoDao.borrar(turnoConIdMasAlto.getId_turno());
        assertTrue(resultado, "Eliminando el ultimo turno");

        List<TurnoDTO> listTurnosDespuesDeEliminar = turnoDao.listar();
        assertTrue(listTurnosDespuesDeEliminar.stream().noneMatch(turno -> turno.getId_turno() == turnoConIdMasAlto.getId_turno()),
                "El ULTIMO TURNO HA SIDO ELIMINADO");

        for (TurnoDTO turno : listTurnosDespuesDeEliminar) {
            System.out.println(turno);
        }
    }



}
