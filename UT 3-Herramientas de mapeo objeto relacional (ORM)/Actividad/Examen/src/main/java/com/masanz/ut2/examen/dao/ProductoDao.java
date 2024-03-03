package com.masanz.ut2.examen.dao;

import com.masanz.ut2.examen.database.ConectionManager;
import com.masanz.ut2.examen.dto.EmpresasDTO;
import com.masanz.ut2.examen.dto.ProductosDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductoDao {

    private EntityManagerFactory emf;

    public ProductoDao(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public List<ProductosDTO> obtenerProductos() {

//        String sql = "SELECT * FROM productos";
//        Object[] params = {};
//        Object[][] resultado = ConectionManager.ejecutarSelectSQL(sql, params);
//        List<Producto> productos = procesarResultado(resultado);
//        if(productos==null){
//            productos = new ArrayList<>();
//        }
//        return productos;


        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT u FROM ProductosDTO u";
        List<ProductosDTO> productosDTOS = em.createQuery(jpql,ProductosDTO.class).getResultList();
        em.close();
        return productosDTOS;
    }


//    private List<Producto> procesarResultado(Object[][] resultado){
//        List<Producto> productos = null;
//        if (resultado != null) {
//            productos = new ArrayList<>();
//            for (Object[] fila : resultado) {
//                Producto producto = new Producto();
//                producto.setId((Integer) fila[0]);
//                producto.setNombre((String) fila[1]);
//                producto.setValor((Integer) fila[2]);
//                productos.add(producto);
//            }
//        } else {
//            System.out.println("El resultado es nulo.");
//        }
//        return productos;
//    }

    public ProductosDTO obtenerProducto(int id) {
//        String sql = "SELECT * FROM productos WHERE id = ?";
//        Object[] params = {id};
//        Object[][] resultado = ConectionManager.ejecutarSelectSQL(sql, params);
//        List<Producto> productos = procesarResultado(resultado);
//        if(productos!=null && productos.size()==1){
//            return productos.get(0);
//        }
//        return null;

        EntityManager em = emf.createEntityManager();
        ProductosDTO productosDTO = null;
        try {
            productosDTO = em.find(ProductosDTO.class, id);
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        } finally {
            em.close();
        }
        return productosDTO;
    }

    public boolean eliminarProducto(int id) {
//        boolean borradoExitoso = false;
//        String sql = "DELETE FROM productos WHERE id = ?";
//        Object[] params = {id};
//        int productosEliminados = ConectionManager.ejecutarUpdateSQL(sql, params);
//        if (productosEliminados > 0) {
//            borradoExitoso = true;
//        }
//        return borradoExitoso;

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            ProductosDTO borrarUser = em.find(ProductosDTO.class, id);
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

    public void crearProducto(int id, String nombre, int valor) {
//        String sql = "INSERT INTO productos (id, nombre, valor) VALUES (?, ?, ?)";
//        Object[] params = {id, nombre, valor};
//        ConectionManager.ejecutarUpdateSQL(sql, params);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        ProductosDTO productosDTO = new ProductosDTO();
        productosDTO.setNombre(nombre);
        productosDTO.setValor(valor);
        em.persist(productosDTO);

        em.getTransaction().commit();
        em.close();
    }

    public void editarProducto(int id, String nombre, int valor) {
//        String sql = "UPDATE productos SET nombre = ?, valor = ? WHERE id = ?";
//        Object[] params = {nombre, valor, id};
//        ConectionManager.ejecutarUpdateSQL(sql, params);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        ProductosDTO actualizarUser = em.find(ProductosDTO.class, id);
        actualizarUser.setNombre(nombre);
        actualizarUser.setValor(valor);
        em.merge(actualizarUser);
        System.out.println("CORRECTO");
        em.getTransaction().commit();
        em.close();
    }
}
