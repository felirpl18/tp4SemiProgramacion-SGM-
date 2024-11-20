package com.seminarioprogramaciontp3.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "Titular")
public class TitularDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "titular_seq")
    @SequenceGenerator(name = "titular_seq", sequenceName = "SEQUENCE", allocationSize = 1)
    private int id_titular;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "nro_doc")
    private String nro_doc;

    @Column(name = "telefono")
    private String telefono;

    public TitularDTO() {
    }

    public TitularDTO(String nombre, String apellido, String nro_doc, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nro_doc = nro_doc;
        this.telefono = telefono;
    }

    public TitularDTO(int id_titular, String nombre, String apellido, String nro_doc, String telefono) {
        this.id_titular = id_titular;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nro_doc = nro_doc;
        this.telefono = telefono;
    }

    public int getId_titular() {
        return id_titular;
    }

    public void setId_titular(int id_titular) {
        this.id_titular = id_titular;
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

    public String getNro_doc() {
        return nro_doc;
    }

    public void setNro_doc(String nro_doc) {
        this.nro_doc = nro_doc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return this.nombre + " " + this.apellido + " - " + this.nro_doc;
    }
}
