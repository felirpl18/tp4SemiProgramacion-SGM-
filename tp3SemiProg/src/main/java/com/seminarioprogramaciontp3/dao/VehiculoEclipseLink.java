package com.seminarioprogramaciontp3.dao;

import com.seminarioprogramaciontp3.dto.TitularDTO;
import com.seminarioprogramaciontp3.dto.VehiculoDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VehiculoEclipseLink implements VehiculoDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");

    @Override
    public VehiculoDTO buscar(String patente) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT v FROM VehiculoDTO v WHERE v.patente = :patente");
            query.setParameter("patente", patente);
            return (VehiculoDTO) query.getSingleResult();
        } catch (Exception e) {
            System.err.println(e);
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public VehiculoDTO buscar(int id_vehiculo) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(VehiculoDTO.class, id_vehiculo);
        } catch (Exception e) {
            System.err.println(e);
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<VehiculoDTO> listar() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT v FROM VehiculoDTO v ORDER BY v.idVehiculo DESC");
            return query.getResultList();
        } catch (Exception e) {
            System.err.println(e);
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public List<VehiculoDTO> listar(TitularDTO titular) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT v FROM VehiculoDTO v WHERE v.idTitular = :id_titular ORDER BY v.idVehiculo DESC");
            query.setParameter("id_titular", titular.getId_titular());
            return query.getResultList();
        } catch (Exception e) {
            System.err.println(e);
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    Random random = new Random();
    int idVehiculos = random.nextInt(100000) + 1;

    @Override
    public boolean insertar(int id_titular, int id_aseguradora, String patente, String marca, String modelo, String nro_poliza) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            VehiculoDTO vehiculo = new VehiculoDTO(idVehiculos,id_titular, id_aseguradora, patente, marca, modelo, nro_poliza);
            em.persist(vehiculo);
            em.flush();
            em.refresh(vehiculo);
            tx.commit();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }

    }

    @Override
    public boolean modificar(int id_vehiculo, int id_titular, int id_aseguradora, String patente, String marca, String modelo, String nro_poliza) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            VehiculoDTO vehiculo = em.find(VehiculoDTO.class, id_vehiculo);
            vehiculo.setId_titular(id_titular);
            vehiculo.setId_aseguradora(id_aseguradora);
            vehiculo.setPatente(patente);
            vehiculo.setMarca(marca);
            vehiculo.setModelo(modelo);
            vehiculo.setPoliza(nro_poliza);
            em.merge(vehiculo);
            tx.commit();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
    }

    @Override
    public boolean borrar(int id_vehiculo) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            VehiculoDTO vehiculo = em.find(VehiculoDTO.class, id_vehiculo);
            if (vehiculo != null) {
                em.remove(vehiculo);
                tx.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
    }

    @Override
    public void cerrarConexion() { }


}
