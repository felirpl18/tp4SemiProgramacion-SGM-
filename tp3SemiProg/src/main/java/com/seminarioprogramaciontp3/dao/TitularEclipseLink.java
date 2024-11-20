package com.seminarioprogramaciontp3.dao;

import com.seminarioprogramaciontp3.dto.TitularDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Random;

public class TitularEclipseLink implements TitularDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");

    @Override
    public TitularDTO buscar(String nombre, String apellido) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<TitularDTO> query = em.createQuery("SELECT t FROM TitularDTO t WHERE t.nombre = :nombre AND t.apellido = :apellido", TitularDTO.class);
            query.setParameter("nombre", nombre);
            query.setParameter("apellido", apellido);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public TitularDTO buscar(int id_titular) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(TitularDTO.class, id_titular);
        } finally {
            em.close();
        }
    }

    @Override
    public List<TitularDTO> listar() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<TitularDTO> query = em.createQuery("SELECT t FROM TitularDTO t", TitularDTO.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    Random random = new Random();
    int idTitulares = random.nextInt(100000) + 1;

    @Override
    public boolean insertar(String nombre, String apellido, String nro_doc, String telefono) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TitularDTO titular = new TitularDTO(idTitulares,nombre, apellido, nro_doc, telefono);
            em.persist(titular);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean modificar(int id_titular, String nombre, String apellido, String nro_doc, String telefono) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TitularDTO titular = em.find(TitularDTO.class, id_titular);
            if (titular != null) {
                titular.setNombre(nombre);
                titular.setApellido(apellido);
                titular.setNro_doc(nro_doc);
                titular.setTelefono(telefono);
                em.merge(titular);
                em.getTransaction().commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean borrar(int id_titular) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TitularDTO titular = em.find(TitularDTO.class, id_titular);
            if (titular != null) {
                em.remove(titular);
                em.getTransaction().commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public void cerrarConexion() {
        if (emf != null) {
            emf.close();
        }
    }
}
