package com.seminarioprogramaciontp3.modelo;

import com.seminarioprogramaciontp3.dao.AseguradoraDAO;
import com.seminarioprogramaciontp3.dao.FactoryDAO;
import com.seminarioprogramaciontp3.dto.AseguradoraDTO;
import java.util.List;

public class Aseguradora extends Modelo {

    private final FactoryDAO factoryDao;
    private final AseguradoraDAO aseguradoraDao;

    public Aseguradora() {
        factoryDao = FactoryDAO.getFactory("Factory");
        aseguradoraDao = factoryDao.getAseguradoraDao();
    }

    public AseguradoraDTO buscar(int id_aseguradora) {
        return aseguradoraDao.buscar(id_aseguradora);
    }

    public List<AseguradoraDTO> listar() {
        return aseguradoraDao.listar();
    }

    public boolean insertar(String nombre, String email, String cuit) {
        return aseguradoraDao.insertar(nombre, email, cuit);
    }

    public boolean actualizar(int id_aseguradora, String nombre, String email, String cuit) {
        return aseguradoraDao.actualizar(id_aseguradora, nombre, email, cuit);
    }

    public boolean borrar(int id_aseguradora) {
        return aseguradoraDao.borrar(id_aseguradora);
    }

    public void cerrarConexion() {
        aseguradoraDao.cerrarConexion();
    }
}
