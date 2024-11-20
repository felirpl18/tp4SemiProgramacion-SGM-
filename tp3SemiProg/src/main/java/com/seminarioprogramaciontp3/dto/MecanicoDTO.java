package com.seminarioprogramaciontp3.dto;

import jakarta.persistence.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "Mecanico")
public class MecanicoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mecanico")
    private int idMecanico;



    @Column(name = "id_especialidad")
    private int idEspecialidad;

    @Column(name = "legajo")
    private String legajo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "nro_doc")
    private int nroDoc;

    @Column(name = "carga_horaria")
    private double cargaHoraria;



    public MecanicoDTO() {
    }



    public MecanicoDTO(int idMecanico, int idEspecialidad, String legajo, String nombre, String apellido, int nroDoc, double cargaHoraria) {
        this.idMecanico = idMecanico;
        this.idEspecialidad = idEspecialidad;
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nroDoc = nroDoc;
        this.cargaHoraria = cargaHoraria;
    }

    public int getIdMecanico() {
        return idMecanico;
    }

    public void setIdMecanico(int idMecanico) {
        this.idMecanico = idMecanico;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    private static int generateRandomId() {
        return ThreadLocalRandom.current().nextInt(1, 40001);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(int nroDoc) {
        this.nroDoc = nroDoc;
    }

    public double getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(double cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }


    @Override
    public String toString() {
        return this.nombre + " " + this.apellido;
    }
}
