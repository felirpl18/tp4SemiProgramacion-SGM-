
package com.seminarioprogramaciontp3.dto;

import java.time.LocalTime;


public class TurnoDTO {
    
    private int id_turno;
    private String dia_atencion;
    private LocalTime hora_atencion;
    private Boolean asistencia;
    private int id_vehiculo;
    private int id_servicio;
    private int id_mecanico;
    private VehiculoDTO vehiculo;
    private ServicioDTO servicio;
    private TitularDTO titular;
    private MecanicoDTO mecanico;

    public MecanicoDTO getMecanico() {
        return mecanico;
    }

    public void setMecanico(MecanicoDTO mecanico) {
        this.mecanico = mecanico;
    }

    public TurnoDTO(int id_turno, String dia_atencion, LocalTime hora_atencion, Boolean asistencia, int id_vehiculo, int id_servicio, int id_mecanico) {
        this.id_turno = id_turno;
        this.dia_atencion = dia_atencion;
        this.hora_atencion = hora_atencion;
        this.asistencia = asistencia;
        this.id_vehiculo = id_vehiculo;
        this.id_servicio = id_servicio;
        this.id_mecanico = id_mecanico;
    }

    public VehiculoDTO getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoDTO vehiculo) {
        this.vehiculo = vehiculo;
    }

    public ServicioDTO getServicio() {
        return servicio;
    }

    public void setServicio(ServicioDTO servicio) {
        this.servicio = servicio;
    }

    public TitularDTO getTitular() {
        return titular;
    }

    public void setTitular(TitularDTO titular) {
        this.titular = titular;
    }

    
    public int getId_turno() {
        return id_turno;
    }

    public void setId_turno(int id_turno) {
        this.id_turno = id_turno;
    }

    public String getDia_atencion() {
        return dia_atencion;
    }

    public void setDia_atencion(String dia_atencion) {
        this.dia_atencion = dia_atencion;
    }

    public LocalTime getHora_atencion() {
        return hora_atencion;
    }

    public void setHora_atencion(LocalTime hora_atencion) {
        this.hora_atencion = hora_atencion;
    }
    
    public Boolean getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Boolean asistencia) {
        this.asistencia = asistencia;
    }
    
    public int getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(int id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    
    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }
    
    public int getId_mecanico() {
        return id_mecanico;
    }

    public void setId_mecanico(int id_mecanico) {
        this.id_mecanico = id_mecanico;
    }

    @Override
    public String toString() {
        return dia_atencion + ", " + hora_atencion;
    }  
 
}

