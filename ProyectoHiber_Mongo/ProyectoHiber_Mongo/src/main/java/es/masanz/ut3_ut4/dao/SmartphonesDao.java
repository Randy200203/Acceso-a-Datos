package es.masanz.ut3_ut4.dao;

import es.masanz.ut3_ut4.dto.SmartphonesDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.math.BigDecimal;

public class SmartphonesDao {
    private EntityManagerFactory emf;

    public SmartphonesDao(EntityManagerFactory emf) {
        this.emf = emf;
    }


    public SmartphonesDTO crearSmartphone(String nombre, String modelo, String sistemaOperativo, BigDecimal precio, Integer memoriaRam) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        SmartphonesDTO smartphone = new SmartphonesDTO();
        smartphone.setNombre(nombre);
        smartphone.setModelo(modelo);
        smartphone.setSistemaOperativo(sistemaOperativo);
        smartphone.setPrecio(precio);
        smartphone.setMemoriaRam(memoriaRam);
        try {
            em.persist(smartphone);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return smartphone;
    }

    public SmartphonesDTO buscarSmartphonePorId(int id) {
        EntityManager em = emf.createEntityManager();
        SmartphonesDTO smartphone = null;
        try {
            smartphone = em.find(SmartphonesDTO.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return smartphone;
    }

    public void actualizarSmartphone(SmartphonesDTO smartphone) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.merge(smartphone);
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

    public void eliminarSmartphone(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            SmartphonesDTO smartphone = em.find(SmartphonesDTO.class, id);
            if (smartphone != null) {
                em.remove(smartphone);
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
