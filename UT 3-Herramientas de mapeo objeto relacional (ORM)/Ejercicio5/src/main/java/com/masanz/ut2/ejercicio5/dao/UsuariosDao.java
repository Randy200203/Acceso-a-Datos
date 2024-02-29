package com.masanz.ut2.ejercicio5.dao;

import com.masanz.ut2.ejercicio5.database.DatabaseManager;
import com.masanz.ut2.ejercicio5.dto.ArticulosDTO;
import com.masanz.ut2.ejercicio5.dto.Usuario;
import com.masanz.ut2.ejercicio5.dto.UsuariosDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDao {
    private EntityManagerFactory emf;

    public UsuariosDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public UsuariosDTO crearUsuario(UsuariosDTO usuario){
        return crearUsuario(usuario.getFullName(), usuario.getUser(), usuario.getEmail(), usuario.getPassword(), usuario.getSaldo());
    }

    public UsuariosDTO crearUsuario(String fullName, String user, String email, String password, int saldo){
//            Usuario usuario = new Usuario(fullName, user, email, password, saldo);
//            String sql = "INSERT INTO usuarios (full_name, user, email, password, creation_date, modification_date) VALUES (?, ?, ?, ?, ?, ?)";
//            Object[] params = {fullName, user, email, password, usuario.getCreationDate(), usuario.getModificationDate()};
//            int registrosIncluidos = DatabaseManager.ejecutarUpdateSQL(sql, params);
//            if(registrosIncluidos>0){
//                // TODO: Actualizar usuario
//                return usuario;
//            }
//            return null;

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        UsuariosDTO usuariosDTO = new UsuariosDTO();
        usuariosDTO.setFullName(fullName);
        usuariosDTO.setUser(user);
        usuariosDTO.setEmail(email);
        usuariosDTO.setPassword(password);
        usuariosDTO.setSaldo(saldo);
        em.persist(usuariosDTO);
        em.getTransaction().commit();
        emf.close();
        return usuariosDTO;
    }

    public boolean borrarUsuario(UsuariosDTO usuario){
//        boolean borradoExitoso = false;
//        String sql = "DELETE FROM usuarios WHERE user LIKE ?";
//        Object[] params = { usuario.getUser() };
//        int registrosEliminados = DatabaseManager.ejecutarUpdateSQL(sql, params);
//        if (registrosEliminados > 0) {
//            borradoExitoso = true;
//        }
//        return borradoExitoso;

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

    public UsuariosDTO actualizarUsuario(UsuariosDTO usuario){
//        String sql = "UPDATE usuarios SET full_name = ?, email = ?, password = ? , modification_date = ? , saldo = ? WHERE user = ?";
//        Object[] params = {usuario.getFullName(), usuario.getEmail(), usuario.getPassword(), new java.util.Date(), usuario.getSaldo(), usuario.getUser()};
//        int registrosActualizados = DatabaseManager.ejecutarUpdateSQL(sql, params);
//        if (registrosActualizados > 0) {
//            return usuario;
//        }
//        return null;

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        UsuariosDTO actualizarUser = em.find(UsuariosDTO.class, usuario.getId());
        actualizarUser.setFullName(usuario.getFullName());
        actualizarUser.setUser(usuario.getUser());
        actualizarUser.setEmail(usuario.getEmail());
        actualizarUser.setPassword(usuario.getPassword());
        actualizarUser.setSaldo(usuario.getSaldo());
        em.merge(actualizarUser);
        System.out.println("CORRECTO");
        em.getTransaction().commit();
        em.close();
        return actualizarUser;
    }

    public UsuariosDTO obtenerUsuario(int userId, String user){
//        String sql = "SELECT * FROM usuarios WHERE id = ? OR user = ? LIMIT 1";
//        Object[] params = {userId, user};
//        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
//        List<Usuario> usuarios = procesarResultado(resultado);
//        if(usuarios!=null && usuarios.size()==1){
//            return usuarios.get(0);
//        }
//        return null;

        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT u FROM UsuariosDTO u WHERE u.id = :idUsuario and u.user = :usuario";
        List<UsuariosDTO> usuariosDTOS = em.createQuery(jpql,UsuariosDTO.class)
                .setParameter("idUsuario", userId)
                .setParameter("usuario",user)
                .getResultList();
        em.close();
        return usuariosDTOS.getFirst();
    }

    public List<UsuariosDTO> obtenerUsuarios(){
//        String sql = "SELECT * FROM usuarios";
//        Object[] params = null;
//        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
//        List<Usuario> usuarios = procesarResultado(resultado);
//        return usuarios;

        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT u FROM UsuariosDTO u";
        List<UsuariosDTO> usuariosDTOS = em.createQuery(jpql,UsuariosDTO.class).getResultList();
        em.close();
        return usuariosDTOS;
    }

    public UsuariosDTO obtenerUltimoUsuario(){
//        String sql = "SELECT * FROM usuarios ORDER BY id DESC LIMIT 1";
//        Object[] params = null;
//        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
//        List<Usuario> usuarios = procesarResultado(resultado);
//        if(usuarios!=null && usuarios.size()>0){
//            return usuarios.get(0);
//        }
//        return null;
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT u FROM UsuariosDTO u ORDER BY u.id DESC LIMIT 1 ";
        List<UsuariosDTO> usuarios = em.createQuery(jpql,UsuariosDTO.class).getResultList();
        em.close();
//        return articulosDTOS;
        if(usuarios!=null && usuarios.size()>0){
            return usuarios.get(0);
        }
        return null;
    }

//    private List<Usuario> procesarResultado(Object[][] resultado){
//        List<Usuario> usuarios = null;
//        if (resultado != null) {
//            usuarios = new ArrayList<>();
//            for (Object[] fila : resultado) {
//                Usuario usuario = new Usuario();
//                usuario.setId((Integer) fila[0]);
//                usuario.setFullName((String) fila[1]);
//                usuario.setUser((String) fila[2]);
//                usuario.setEmail((String) fila[3]);
//                usuario.setPassword((String) fila[4]);
//                usuario.setCreationDate((Date) fila[5]);
//                usuario.setModificationDate((Date) fila[6]);
//                usuario.setSaldo((Integer) fila[7]);
//                usuarios.add(usuario);
//            }
//        } else {
//            System.out.println("El resultado es nulo.");
//        }
//        return usuarios;
//    }

    public UsuariosDTO autenticar(String user, String password){
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT u FROM UsuariosDTO u where u.user = :usuario and u.password = :pass";
        List<UsuariosDTO> usuarios = em.createQuery(jpql,UsuariosDTO.class)
                .setParameter("usuario", user)
                .setParameter("pass", password)
                .getResultList();
        em.close();
        if (usuarios!=null && usuarios.size()>0) {
            return usuarios.get(0);
        }
        return null;

        //        String sql = "SELECT * FROM usuarios WHERE user = ? AND password = ?";
//        Object[] params = {user, password};
//        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
//        List<UsuariosDTO> usuarios = procesarResultado(resultado);
    }

    public boolean borrarUsuariosAusentes(int cantidad){
        boolean borradoExitoso = false;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        String jpql = "SELECT u FROM UsuariosDTO u order by u.modificationDate asc limit '" + cantidad  + "'";
        List<UsuariosDTO> usuarios = em.createQuery(jpql,UsuariosDTO.class).getResultList();
        for (UsuariosDTO usuario: usuarios){
            em.remove(usuario);
        }
        em.close();
        em.getTransaction().commit();
//        String sql = "DELETE FROM usuarios ORDER BY modification_date ASC LIMIT ?";
//        Object[] params = {cantidad};
//        int usuariosEliminados = DatabaseManager.ejecutarUpdateSQL(sql, params);
//        if (usuariosEliminados > 0) {
//            borradoExitoso = true;
//        }
        return borradoExitoso;
    }

}
