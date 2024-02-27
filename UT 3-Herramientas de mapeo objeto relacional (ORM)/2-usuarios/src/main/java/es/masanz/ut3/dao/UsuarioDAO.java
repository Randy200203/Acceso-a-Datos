package es.masanz.ut3.dao;

import es.masanz.ut3.Usuario;
import es.masanz.ut3.dto.UsuariosDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class UsuarioDAO {

    private EntityManagerFactory emf;

    public UsuarioDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public UsuariosDTO crearUsuario(String fullName, String user, String email, String password){
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        // HACER EL NEW DE NUESTRO OBJETO
        // MyEntity miEntity = new MyEntity();
        UsuariosDTO usuario = new UsuariosDTO();
        usuario.setFullName(fullName);
        usuario.setUser(user);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setCreationDate(new Timestamp(System.currentTimeMillis()));
        usuario.setModificationDate(new Timestamp(System.currentTimeMillis()));
        System.out.println(usuario);
        em.persist(usuario);
        System.out.println(usuario);
        em.getTransaction().commit();
        emf.close();
        return usuario;
    }



    public boolean borrarUsuario(UsuariosDTO usuario){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            UsuariosDTO borrarUser = em.find(UsuariosDTO.class, usuario.getId());
            em.remove(borrarUser);
        } catch (Exception e) {
            System.out.println("ERROR" + e);
            em.close();
            return false;
        }
        System.out.println("CORRECTO");
        em.close();
        em.getTransaction().commit();
       return true;
    }

    public UsuariosDTO actualizarUsuario(UsuariosDTO usuario, String fullName, String user, String email, String password){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

            UsuariosDTO actualizarUser = em.find(UsuariosDTO.class, usuario.getId());
            actualizarUser.setFullName(fullName);
            actualizarUser.setUser(user);
            actualizarUser.setEmail(email);
            actualizarUser.setPassword(password);
            em.merge(actualizarUser);
            System.out.println("CORRECTO");
            em.getTransaction().commit();
            em.close();
            return actualizarUser;
    }

    public List<UsuariosDTO> obtenerUsuarios(){
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT u FROM UsuariosDTO u";
        List<UsuariosDTO> usuariosDTOS = em.createQuery(jpql,UsuariosDTO.class).getResultList();
        em.close();
        return usuariosDTOS;
    }


}
