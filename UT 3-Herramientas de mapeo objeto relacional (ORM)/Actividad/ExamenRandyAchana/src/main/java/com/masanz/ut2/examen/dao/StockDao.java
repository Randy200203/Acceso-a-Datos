package com.masanz.ut2.examen.dao;

import com.masanz.ut2.examen.database.ConectionManager;
import com.masanz.ut2.examen.dto.EmpresasDTO;
import com.masanz.ut2.examen.dto.ProductosDTO;
import com.masanz.ut2.examen.dto.StockDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

public class StockDao {

    private EntityManagerFactory emf;

    public StockDao(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public List<StockDTO> obtenerStockEmpresa(int id) {
//        String sql = "SELECT * FROM stock WHERE idEmpresa = ?";
//        Object[] params = {id};
//        Object[][] resultado = ConectionManager.ejecutarSelectSQL(sql, params);
//        List<Stock> stock = procesarResultado(resultado);
//        return stock;

        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT u FROM ProductosDTO u WHERE u.id = :id";
        List<StockDTO> stockDTOS = em.createQuery(jpql,StockDTO.class)
                .setParameter("id", id)
                .getResultList();
        em.close();
        return stockDTOS;
    }

    public List<StockDTO> obtenerStockCompleto() {
//        String sql = "SELECT * FROM stock";
//        Object[] params = {};
//        Object[][] resultado = ConectionManager.ejecutarSelectSQL(sql, params);
//        List<Stock> stock = procesarResultado(resultado);
//        return stock;

        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT u FROM StockDTO u";
        List<StockDTO> stockDTOS = em.createQuery(jpql,StockDTO.class).getResultList();
        em.close();
        return stockDTOS;
    }

//    private List<Stock> procesarResultado(Object[][] resultado){
//        List<Stock> stock = null;
//        if (resultado != null) {
//            stock = new ArrayList<>();
//            for (Object[] fila : resultado) {
//                Stock almacen = new Stock();
//                almacen.setId((Integer) fila[0]);
//                almacen.setIdEmpresa((Integer) fila[1]);
//                almacen.setIdProducto((Integer) fila[2]);
//                almacen.setCantidad((Integer) fila[3]);
//                stock.add(almacen);
//            }
//        } else {
//            System.out.println("El resultado es nulo.");
//        }
//        return stock;
//    }


    public StockDTO buscarStock(int idEmpresa, int idProducto) {
//        String sql = "SELECT * FROM stock WHERE idEmpresa = ? AND idProducto = ?";
//        Object[] params = {idEmpresa, idProducto};
//        Object[][] resultado = ConectionManager.ejecutarSelectSQL(sql, params);
//        List<Stock> stock = procesarResultado(resultado);
//        if(stock!=null && stock.size()==1){
//            return stock.get(0);
//        }
//        return null;

        EntityManager em = emf.createEntityManager();
        StockDTO stockDTO = null;
        try {
            Query query = em.createQuery("SELECT u FROM StockDTO u WHERE u.idEmpresa = :idEmpresa AND u.idProducto = :idProducto");
            query.setParameter("idEmpresa", idEmpresa);
            query.setParameter("idProducto", idProducto);
            stockDTO = (StockDTO) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Stock no encontrada");
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        } finally {
            em.close();
        }
        return stockDTO;
    }

    public void actualarStock(int id, int cantidad) {
//        String sql = "UPDATE stock SET cantidad = ? WHERE id = ?";
//        Object[] params = {cantidad, id};
//        ConectionManager.ejecutarUpdateSQL(sql, params);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        StockDTO stockDTO = em.find(StockDTO.class, id);
        if (stockDTO != null) {
            stockDTO.setCantidad(cantidad);
            em.merge(stockDTO);
        } else {
            System.out.println("El stock con ID " + id + " no existe en la base de datos");
        }

        em.getTransaction().commit();
        em.close();
    }

    public void insertarStock(int idEmpresa, int idProducto, int cantidad) {
//        String sql = "INSERT INTO stock (id, idEmpresa, idProducto, cantidad) VALUES (?, ?, ?, ?)";
//        Object[] params = {id, idEmpresa, idProducto, cantidad};
//        ConectionManager.ejecutarUpdateSQL(sql, params);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        StockDTO stockDTO = new StockDTO();
        stockDTO.setIdEmpresa(idEmpresa);
        stockDTO.setIdProducto(idProducto);
        stockDTO.setCantidad(cantidad);

        em.persist(stockDTO);

        em.getTransaction().commit();
        em.close();
    }

    public void borrarStock(int id) {
//        String sql = "DELETE FROM stock WHERE id = ?";
//        Object[] params = {id};
//        ConectionManager.ejecutarUpdateSQL(sql, params);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            StockDTO borrarUser = em.find(StockDTO.class, id);
            em.remove(borrarUser);
        } catch (Exception e) {
            System.out.println("ERROR" + e);
            em.close();
        }
        System.out.println("CORRECTO");
        em.close();
        em.getTransaction().commit();

    }
}
