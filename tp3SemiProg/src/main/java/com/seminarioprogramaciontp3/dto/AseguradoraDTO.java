package com.seminarioprogramaciontp3.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "Aseguradora")
public class AseguradoraDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aseguradora")
    private int idAseguradora;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name = "cuit")
    private String cuit;

    public AseguradoraDTO() {
    }

    public AseguradoraDTO(int idAseguradora, String nombre, String email, String cuit) {
        this.idAseguradora = idAseguradora;
        this.nombre = nombre;
        this.email = email;
        this.cuit = cuit;
    }

    public int getId_aseguradora() {
        return idAseguradora;
    }

    public void setIdAseguradora(int idAseguradora) {
        this.idAseguradora = idAseguradora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    @Override
    public String toString() {
        return nombre + " - " + email + " - " + cuit;
    }
}
