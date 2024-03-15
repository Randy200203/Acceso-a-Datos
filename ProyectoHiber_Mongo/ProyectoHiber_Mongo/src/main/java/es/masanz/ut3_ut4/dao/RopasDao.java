package es.masanz.ut3_ut4.dao;

import es.masanz.ut3_ut4.dto.RopasDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.math.BigDecimal;
import java.util.List;

public class RopasDao {
    private EntityManagerFactory emf;

    public RopasDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public RopasDTO crearRopa(String nombre, String marca, String talla, String color, BigDecimal precio) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        RopasDTO ropa = new RopasDTO();
        ropa.setNombre(nombre);
        ropa.setMarca(marca);
        ropa.setTalla(talla);
        ropa.setColor(color);
        ropa.setPrecio(precio);
        try {
            em.persist(ropa);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return ropa;
}

    public RopasDTO buscarRopaPorId(int id) {
        EntityManager em = emf.createEntityManager();
        RopasDTO ropa = null;
        try {
            ropa = em.find(RopasDTO.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return ropa;
    }

    public void actualizarRopa(RopasDTO ropa) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.merge(ropa);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void eliminarRopa(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            RopasDTO ropa = em.find(RopasDTO.class, id);
            if (ropa != null) {
                em.remove(ropa);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public RopasDTO obtenerRopaPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(RopasDTO.class, id);
        } finally {
            em.close();
        }
    }


    public List<RopasDTO> getAllRopas() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT r FROM RopasDTO r", RopasDTO.class).getResultList();
        } finally {
            em.close();
        }
    }
}
