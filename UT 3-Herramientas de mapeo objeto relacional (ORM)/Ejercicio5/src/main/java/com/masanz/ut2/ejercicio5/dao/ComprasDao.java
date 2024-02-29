package com.masanz.ut2.ejercicio5.dao;

import com.masanz.ut2.ejercicio5.database.DatabaseManager;
import com.masanz.ut2.ejercicio5.dto.ArticulosDTO;
import com.masanz.ut2.ejercicio5.dto.ComprasDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComprasDao {
    private EntityManagerFactory emf;

    public ComprasDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ComprasDTO crearCompras(ComprasDTO compras){
        return crearCompras(compras.getIdObjeto(), compras.getIdComprador(), compras.getIdVendedor(), compras.getFechaCompra());
    }

    public ComprasDTO crearCompras(int idArticulo, int idComprador, int idVendedor, Date fechaComprar){
//        Compras compras = new Compras(idArticulo, idComprador, idVendedor);
//        String sql = "INSERT INTO compras (id_objeto, id_comprador, id_vendedor,  fecha_compra) VALUES (?, ?, ?, ?)";
//        Object[] params = {idArticulo, idComprador, idVendedor, fechaComprar};
//        int registrosIncluidos = DatabaseManager.ejecutarUpdateSQL(sql, params);
//        if(registrosIncluidos>0){
//            return compras;
//        }
//        return null;

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        ComprasDTO comprasDTO = new ComprasDTO();
        comprasDTO.setIdObjeto(idArticulo);
        comprasDTO.setIdComprador(idComprador);
        comprasDTO.setIdVendedor(idVendedor);
        comprasDTO.setFechaCompra( new java.sql.Date(fechaComprar.getTime()));
        em.persist(comprasDTO);
        em.getTransaction().commit();
        emf.close();
        return comprasDTO;
    }

    public List<ComprasDTO> obtenerCompras(){
//        String sql = "SELECT * FROM compras";
//        Object[] params = null;
//        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
//        List<Compras> compras = procesarResultado(resultado);
//        return compras;

        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT u FROM ComprasDTO u";
        List<ComprasDTO> comprasDTOS = em.createQuery(jpql,ComprasDTO.class).getResultList();
        em.close();
        return comprasDTOS;
    }

//    private List<ComprasDTO> procesarResultado(Object[][] resultado){
//        List<Compras> compras = null;
//        if (resultado != null) {
//            compras = new ArrayList<>();
//            for (Object[] fila : resultado) {
//                Compras compra = new Compras();
//                compra.setId((Integer) fila[0]);
//                compra.setIdArticulo((Integer) fila[1]);
//                compra.setIdComprador((Integer) fila[2]);
//                compra.setIdVendedor((Integer) fila[3]);
//                compra.setFechaComprar((Date) fila[4]);
//                compras.add(compra);
//            }
//        } else {
//            System.out.println("El resultado es nulo.");
//        }
//        return compras;
//    }

}
