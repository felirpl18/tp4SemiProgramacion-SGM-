package com.seminarioprogramaciontp3.modelo;

import com.seminarioprogramaciontp3.dao.FactoryDAO;
import com.seminarioprogramaciontp3.dao.TitularDAO;
import com.seminarioprogramaciontp3.dto.TitularDTO;

import java.util.List;

public class Titular extends Modelo {

    private final FactoryDAO factoryDao;
    private final TitularDAO titularDao;

    public Titular() {
        factoryDao = FactoryDAO.getFactory("Factory");
        titularDao = factoryDao.getTitularDao();
    }

    public TitularDTO buscar(String nombre, String apellido) {
        return titularDao.buscar(nombre, apellido);
    }

    public TitularDTO buscar(int id_titular) {
        return titularDao.buscar(id_titular);
    }

    public List<TitularDTO> listar() {
        return titularDao.listar();
    }

    public boolean insertar(String nombre, String apellido, String nro_doc, String telefono) {
        return titularDao.insertar(nombre, apellido, nro_doc, telefono);
    }

    public boolean modificar(int id_titular, String nombre, String apellido, String nro_doc, String telefono) {
        return titularDao.modificar(id_titular, nombre, apellido, nro_doc, telefono);
    }

    public boolean borrar(int id_titular) {
        return titularDao.borrar(id_titular);
    }
}
