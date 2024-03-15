package es.masanz.ut3_ut4.dao;

import es.masanz.ut3_ut4.dto.CochesDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.math.BigDecimal;
import java.util.List;

public class CochesDao {
    private EntityManagerFactory emf;

    public CochesDao(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public CochesDTO crearCoche(String nombre, BigDecimal precio, String modelo, Integer kilometraje, String color) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        CochesDTO coche = new CochesDTO();
        coche.setNombre(nombre);
        coche.setPrecio(precio);
        coche.setModelo(modelo);
        coche.setKilometraje(kilometraje);
        coche.setColor(color);
        try {
            em.persist(coche);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return coche;
    }

    public CochesDTO buscarCochePorId(int id) {
        EntityManager em = emf.createEntityManager();
        CochesDTO coche = null;
        try {
            coche = em.find(CochesDTO.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return coche;
    }

    public void actualizarCoche(CochesDTO coche) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(coche);
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


    public void eliminarCoche(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            CochesDTO coche = em.find(CochesDTO.class, id);
            if (coche != null) {
                em.remove(coche);
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

    public CochesDTO obtenerCochePorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(CochesDTO.class, id);
        } finally {
            em.close();
        }
    }


    public List<CochesDTO> getAllCoches() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM CochesDTO c", CochesDTO.class).getResultList();
        } finally {
            em.close();
        }
    }

}
