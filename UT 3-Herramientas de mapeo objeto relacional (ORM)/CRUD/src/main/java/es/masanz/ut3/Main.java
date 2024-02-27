package es.masanz.ut3;

import es.masanz.ut3.entity.ComprasEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Timestamp;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.masanz.ut3");
        try {
            EntityManager em = emf.createEntityManager();
            //crear(em);
            listar(em);
            //actualizar(em);
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            emf.close();
        }
    }

    public static void crear(EntityManager em){
        em.getTransaction().begin();
        // HACER EL NEW DE NUESTRO OBJETO
        // MyEntity miEntity = new MyEntity();
        ComprasEntity cm = new ComprasEntity();
        cm.setFechaCompra(new Timestamp(System.currentTimeMillis()));
        cm.setObjetoId(3);
        cm.setUsuarioC(20);
        cm.setUsuarioV(13);
        System.out.println(cm);
        em.persist(cm);
        System.out.println(cm);
        em.getTransaction().commit();
    }

    public static void actualizar(EntityManager em){
        em.getTransaction().begin();
        // BUSCAR NUESTRA ENTITY
        // MyEntity miEntity = em.find(MyEntity.class, id);
        ComprasEntity cm = em.find(ComprasEntity.class, 2);
        cm.setUsuarioV(22);
        cm.setFechaCompra(new Timestamp(System.currentTimeMillis()));
        cm.setObjetoId(44);
        cm.setUsuarioC(55);

        // HACER SETTERS PARA MODIFICAR Y DESPUES ACTUALIZAR
        //em.merge(miEntity);
        em.merge(cm);
        em.getTransaction().commit();
    }

    public static void borrar(EntityManager em){
        em.getTransaction().begin();
        // BUSCAR NUESTRO OBJETO
        // MyEntity miEntity = em.find(MyEntity.class, id);
        ComprasEntity cm = em.find(ComprasEntity.class, 2);
        //if (miEntity != null) {
        //    em.remove(miEntity);
        //}
        if (cm != null){
            em.remove(cm);
        }
        em.getTransaction().commit();
    }

    public static void listar(EntityManager em){
        //AHORA NO HACE FALTA DECIR QUE VAMOS A COMENZAR UNA TRANSACCION
        //em.getTransaction().begin();

        String jpql = "SELECT a FROM ComprasEntity a";
        List<ComprasEntity> myEntityList = em.createQuery(jpql, ComprasEntity.class).getResultList();
        for (ComprasEntity myEntity : myEntityList) {
            System.out.println(myEntity);
        }

        //DADO QUE NO HAY TRANSACCION, NO NECESITAMOS HACER COMMIT
        //em.getTransaction().commit();
    }

    public static void buscar(EntityManager em){
        //AHORA NO HACE FALTA DECIR QUE VAMOS A COMENZAR UNA TRANSACCION
        //em.getTransaction().begin();

        String jpql = "SELECT a FROM ComprasEntity a WHERE a.usuarioC = :busqueda";

        //List<MyEntity> myEntityList = em.createQuery(jpql, MyEntity.class)
        //        .setParameter("busqueda", 23)
        //        .getResultList();

        //for (MyEntity myEntity : myEntityList) {
        //    System.out.println(myEntity);
        //}

        List<ComprasEntity> myEntityList = em.createQuery(jpql, ComprasEntity.class)
                .setParameter("busqueda", 23)
                .getResultList();

        for (ComprasEntity cm : myEntityList) {
           System.out.println(cm);
        }

        //DADO QUE NO HAY TRANSACCION, NO NECESITAMOS HACER COMMIT
        //em.getTransaction().commit();
    }
}
