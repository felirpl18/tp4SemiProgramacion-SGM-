package com.seminarioprogramaciontp3.dao;

import com.seminarioprogramaciontp3.dto.EspecialidadDTO;
import com.seminarioprogramaciontp3.dto.MecanicoDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Random;


public class MecanicoEclipseLink implements MecanicoDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");

    @Override
    public MecanicoDTO Buscar(int idMecanico, int idEspecialidad) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        MecanicoDTO mecanico = em.find(MecanicoDTO.class, idMecanico);
        em.getTransaction().commit();
        em.close();
        return mecanico;
    }



    @Override
    public List<MecanicoDTO> listar(EspecialidadDTO especialidad) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT m FROM MecanicoDTO m WHERE m.idEspecialidad = :idEspecialidad");
        query.setParameter("idEspecialidad", especialidad.getId_especialidad());
        List<MecanicoDTO> lista = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return lista;
    }

    @Override
    public List<MecanicoDTO> listar() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT m FROM MecanicoDTO m");
        List<MecanicoDTO> lista = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return lista;
    }
    Random random = new Random();
    int idMecanicos = random.nextInt(100000) + 1;

    @Override
    public boolean Insertar(int idEspecialidad, String legajo, String nombre, String apellido, String nroDoc, double cargaHoraria) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        MecanicoDTO mecanico = new MecanicoDTO(idMecanicos, idEspecialidad, legajo, nombre, apellido, Integer.parseInt(nroDoc), cargaHoraria);
        em.persist(mecanico);
        em.getTransaction().commit();
        em.close();
        return true; // Suponiendo que la inserción siempre es exitosa
    }

    @Override
    public boolean Modificar(int idMecanico, int idEspecialidad, String legajo, String nombre, String apellido, String nroDoc, double cargaHoraria) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        MecanicoDTO mecanico = em.find(MecanicoDTO.class, idMecanico);
        mecanico.setIdEspecialidad(idEspecialidad);
        mecanico.setLegajo(legajo);
        mecanico.setNombre(nombre);
        mecanico.setApellido(apellido);
        mecanico.setNroDoc(Integer.parseInt(nroDoc));
        mecanico.setCargaHoraria(cargaHoraria);
        em.getTransaction().commit();
        em.close();
        return true; // Suponiendo que la modificación siempre es exitosa
    }

    @Override
    public boolean Borrar(int idMecanico) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        MecanicoDTO mecanico = em.find(MecanicoDTO.class, idMecanico);
        em.remove(mecanico);
        em.getTransaction().commit();
        em.close();
        return true; // Suponiendo que el borrado siempre es exitoso
    }

    @Override
    public void cerrarConexion() {
        emf.close();
    }
}
