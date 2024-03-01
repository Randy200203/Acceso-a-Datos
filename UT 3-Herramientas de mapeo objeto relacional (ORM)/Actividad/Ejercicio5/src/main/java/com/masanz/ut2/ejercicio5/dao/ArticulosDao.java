package com.masanz.ut2.ejercicio5.dao;

import com.masanz.ut2.ejercicio5.database.DatabaseManager;
import com.masanz.ut2.ejercicio5.dto.ArticulosDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

public class ArticulosDao {
    private EntityManagerFactory emf;

    public ArticulosDao(EntityManagerFactory emf) {
        this.emf = emf;
    }


    public ArticulosDTO crearArticulo(String id, String nombre, int valor, int propietario){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        ArticulosDTO articulosDTO = new ArticulosDTO();
        articulosDTO.setNombre(nombre);
        articulosDTO.setValor(valor);
        articulosDTO.setIdPropietario(propietario);
        em.persist(articulosDTO);
        em.getTransaction().commit();
        em.close();
        return articulosDTO;
    }

//    public Articulo crearArticulo(int id, String nombre, int valor, int idPropietario){
//        Articulo articulo = new Articulo(id, nombre, valor, idPropietario);
//        String sql = "INSERT INTO articulos (id, nombre, valor, id_propietario) VALUES (?, ?, ?, ?)";
//        Object[] params = {id, nombre, valor, idPropietario};
//        int registrosIncluidos = DatabaseManager.ejecutarUpdateSQL(sql, params);
//        if(registrosIncluidos>0){
//            return articulo;
//        }
//        return null;
//    }

    public boolean borrarArticulo(ArticulosDTO articulo){

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            ArticulosDTO borrarUser = em.find(ArticulosDTO.class, articulo.getId());
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


        //        boolean borradoExitoso = false;
//        String sql = "DELETE FROM articulos WHERE id = ?";
//        Object[] params = { articulo.getId() };
//        int registrosEliminados = DatabaseManager.ejecutarUpdateSQL(sql, params);
//        if (registrosEliminados > 0) {
//            borradoExitoso = true;
//        }
//        return borradoExitoso;
//

    }

    public ArticulosDTO actualizarArticulo(ArticulosDTO articulo){
        return actualizarArticulo(articulo.getId(), articulo.getNombre(), articulo.getValor(), articulo.getIdPropietario());
    }

    private ArticulosDTO actualizarArticulo(int id, String nombre, int valor, int idPropietario) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        ArticulosDTO actualizarUser = em.find(ArticulosDTO.class, id);
        actualizarUser.setNombre(nombre);
        actualizarUser.setValor(valor);
        actualizarUser.setIdPropietario(idPropietario);
        em.merge(actualizarUser);
        System.out.println("CORRECTO");
        em.getTransaction().commit();
        em.close();
        return actualizarUser;

        //String sql = "UPDATE articulos SET nombre = ?, valor = ?, id_propietario = ? WHERE id = ?";
//        Object[] params = {nombre, valor, idPropietario, id};
//        int registrosActualizados = DatabaseManager.ejecutarUpdateSQL(sql, params);
//        if (registrosActualizados > 0) {
//            Articulo articulo = new Articulo(id, nombre, valor, idPropietario);
//            return articulo;
//        }
//        return null;

    }

    public List<ArticulosDTO> obtenerArticulos(){
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT u FROM ArticulosDTO u";
        List<ArticulosDTO> articulosDTOS = em.createQuery(jpql,ArticulosDTO.class).getResultList();
        em.close();
        return articulosDTOS;
    }

    public ArticulosDTO obtenerArticulo(int articuloId) {
//
//        String sql = "SELECT * FROM articulos WHERE id = ?";
//        Object[] params = {articuloId};
//        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
//        List<Articulo> articulos = procesarResultado(resultado);
//        if(articulos!=null && articulos.size()>0){
//            return articulos.get(0);
//        }
//        return null;
        EntityManager em = emf.createEntityManager();
        ArticulosDTO articulosDTO = null;
        try {
            articulosDTO = em.find(ArticulosDTO.class, articuloId);
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            em.close();
        }
        return articulosDTO;
    }

    public List<ArticulosDTO> obtenerArticulosPropietario(int idPropietario){
//        String sql = "SELECT * FROM articulos WHERE id_propietario = ?";
//        Object[] params = {idPropietario};
//        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
//        List<Articulo> articulos = procesarResultado(resultado);
//        return articulos;

        EntityManager em = emf.createEntityManager();
        List<ArticulosDTO> articulosDTO = null;
        try {
            String jpql = "SELECT a FROM ArticulosDTO a WHERE a.idPropietario = :idPropietario";
            articulosDTO = em.createQuery(jpql, ArticulosDTO.class)
                    .setParameter("idPropietario", idPropietario)
                    .getResultList();
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            em.close();
        }
        return articulosDTO;
    }


//    private List<Articulo> procesarResultado(Object[][] resultado){
//        List<Articulo> articulos = null;
//        if (resultado != null) {
//            articulos = new ArrayList<>();
//            for (Object[] fila : resultado) {
//                Articulo articulo = new Articulo();
//                articulo.setId((Integer) fila[0]);
//                articulo.setNombre((String) fila[1]);
//                articulo.setValor((Integer) fila[2]);
//                articulo.setIdPropietario((Integer) fila[3]);
//                articulos.add(articulo);
//            }
//        } else {
//            System.out.println("El resultado es nulo.");
//        }
//        return articulos;
//    }

    public ArticulosDTO obtenerUltimoArticulo(){
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT u FROM ArticulosDTO u";
        List<ArticulosDTO> articulos = em.createQuery(jpql,ArticulosDTO.class).getResultList();
        em.close();
//        return articulosDTOS;

        if(articulos!=null && articulos.size()>0){
            return articulos.get(articulos.size()-1);
        }
        return null;
    }

}