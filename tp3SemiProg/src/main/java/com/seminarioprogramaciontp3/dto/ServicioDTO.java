
package com.seminarioprogramaciontp3.dto;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "Servicio")
public class ServicioDTO {

    @DatabaseField(id = true)
    private int id_servicio;

    @DatabaseField
    private int id_especialidad;    

    @DatabaseField
    private String nombre;

    @DatabaseField
    private String descripcion;

    @DatabaseField
    private int tiempo;

   @DatabaseField(foreign=true, foreignAutoCreate = true, 
      foreignAutoRefresh = true, columnName = "id_especialidad")
    EspecialidadDTO especialidad;

    public ServicioDTO() {
        
    }

    public ServicioDTO(int id_servicio, String nombre, String descripcion, int tiempo, EspecialidadDTO especialidad) {
        this.id_servicio = id_servicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tiempo = tiempo;
        this.especialidad = especialidad;
    }

    public ServicioDTO(int id_servicio, int id_especialidad, String nombre, String descripcion, int tiempo) {
        this.id_servicio = id_servicio;
        this.id_especialidad = id_especialidad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tiempo = tiempo;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public int getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(int id_especialidad) {
        this.id_especialidad = id_especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public EspecialidadDTO getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(EspecialidadDTO especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
