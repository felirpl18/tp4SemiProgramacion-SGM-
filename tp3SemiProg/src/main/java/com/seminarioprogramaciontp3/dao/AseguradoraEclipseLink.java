package com.seminarioprogramaciontp3.dao;

import com.seminarioprogramaciontp3.dto.AseguradoraDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Random;

public class AseguradoraEclipseLink implements AseguradoraDAO {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public AseguradoraEclipseLink() {
        entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public AseguradoraDTO buscar(int id_aseguradora) {
        return entityManager.find(AseguradoraDTO.class, id_aseguradora);
    }

    @Override
    public List<AseguradoraDTO> listar() {
        return entityManager.createQuery("SELECT a FROM AseguradoraDTO a", AseguradoraDTO.class).getResultList();
    }

    Random random = new Random();
    int idAseguradoras= random.nextInt(100000) + 1;

    @Override
    public boolean insertar(String nombre, String email, String cuit) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            AseguradoraDTO aseguradora = new AseguradoraDTO();
            aseguradora.setIdAseguradora(idAseguradoras);
            aseguradora.setNombre(nombre);
            aseguradora.setEmail(email);
            aseguradora.setCuit(cuit);
            entityManager.persist(aseguradora);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println(e);
            return false;
        }
    }

    @Override
    public boolean actualizar(int id_aseguradora, String nombre, String email, String cuit) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            AseguradoraDTO aseguradora = entityManager.find(AseguradoraDTO.class, id_aseguradora);
            if (aseguradora != null) {
                aseguradora.setNombre(nombre);
                aseguradora.setEmail(email);
                aseguradora.setCuit(cuit);
                entityManager.merge(aseguradora);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println(e);
            return false;
        }
    }

    @Override
    public boolean borrar(int id_aseguradora) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            AseguradoraDTO aseguradora = entityManager.find(AseguradoraDTO.class, id_aseguradora);
            if (aseguradora != null) {
                entityManager.remove(aseguradora);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println(e);
            return false;
        }
    }

    @Override
    public void cerrarConexion() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
