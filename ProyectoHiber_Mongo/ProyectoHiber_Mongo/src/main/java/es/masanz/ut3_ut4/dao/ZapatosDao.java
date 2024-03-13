package es.masanz.ut3_ut4.dao;

import es.masanz.ut3_ut4.dto.ZapatosDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.math.BigDecimal;

public class ZapatosDao {

    private EntityManagerFactory emf;

    public ZapatosDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ZapatosDTO crearZapato(String nombre, BigDecimal precio, String marca, String color, String material) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        ZapatosDTO zapato = new ZapatosDTO();
        zapato.setNombre(nombre);
        zapato.setPrecio(precio);
        zapato.setMarca(marca);
        zapato.setColor(color);
        zapato.setMaterial(material);
        try {
            em.persist(zapato);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return zapato;
    }

    public ZapatosDTO buscarZapatoPorId(int id) {
        EntityManager em = emf.createEntityManager();
        ZapatosDTO zapato = null;
        try {
            zapato = em.find(ZapatosDTO.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return zapato;
    }

    public void actualizarZapato(ZapatosDTO zapato) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.merge(zapato);
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

    public void eliminarZapato(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            ZapatosDTO zapato = em.find(ZapatosDTO.class, id);
            if (zapato != null) {
                em.remove(zapato);
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
}
