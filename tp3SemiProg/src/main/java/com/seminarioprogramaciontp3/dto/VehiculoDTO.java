package com.seminarioprogramaciontp3.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "Vehiculo")
public class VehiculoDTO {

    private String nombreTitular;
    private String nombreAseguradora;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private int idVehiculo;

    @Column(name = "id_titular")
    private int idTitular;

    @Column(name = "id_aseguradora")
    private int idAseguradora;

    @Column(name = "patente")
    private String patente;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "nro_poliza")
    private String poliza;

    public VehiculoDTO() {
    }

    public VehiculoDTO(int idVehiculo, int idTitular, int idAseguradora, String patente, String marca, String modelo, String poliza) {
        this.idVehiculo = idVehiculo;
        this.idTitular = idTitular;
        this.idAseguradora = idAseguradora;
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.poliza = poliza;
    }

    public VehiculoDTO(int idTitular, int idAseguradora, String patente, String marca, String modelo, String poliza) {
        this.idTitular = idTitular;
        this.idAseguradora = idAseguradora;
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.poliza = poliza;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getNombreAseguradora() {
        return nombreAseguradora;
    }

    public void setNombreAseguradora(String nombreAseguradora) {
        this.nombreAseguradora = nombreAseguradora;
    }

    public int getId_vehiculo() {
        return idVehiculo;
    }

    public void setId_vehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getId_titular() {
        return idTitular;
    }

    public void setId_titular(int idTitular) {
        this.idTitular = idTitular;
    }

    public int getId_aseguradora() {
        return idAseguradora;
    }

    public void setId_aseguradora(int idAseguradora) {
        this.idAseguradora = idAseguradora;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPoliza() {
        return poliza;
    }

    public void setPoliza(String poliza) {
        this.poliza = poliza;
    }

    @Override
    public String toString() {
        return patente + " (" + marca + " " + modelo + ")";
    }
}
