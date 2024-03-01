package com.masanz.ut2.ejercicio5.dao;

import com.masanz.ut2.ejercicio5.database.DatabaseManager;
import com.masanz.ut2.ejercicio5.dto.Compras;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComprasDao {

    public ComprasDao(){

    }

    public Compras crearCompras(Compras compras){
        return crearCompras(compras.getIdArticulo(), compras.getIdComprador(), compras.getIdVendedor(), compras.getFechaComprar());
    }

    public Compras crearCompras(int idArticulo, int idComprador, int idVendedor, Date fechaComprar){
        Compras compras = new Compras(idArticulo, idComprador, idVendedor);
        String sql = "INSERT INTO compras (id_objeto, id_comprador, id_vendedor,  fecha_compra) VALUES (?, ?, ?, ?)";
        Object[] params = {idArticulo, idComprador, idVendedor, fechaComprar};
        int registrosIncluidos = DatabaseManager.ejecutarUpdateSQL(sql, params);
        if(registrosIncluidos>0){
            return compras;
        }
        return null;
    }

    public List<Compras> obtenerCompras(){
        String sql = "SELECT * FROM compras";
        Object[] params = null;
        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
        List<Compras> compras = procesarResultado(resultado);
        return compras;
    }

    private List<Compras> procesarResultado(Object[][] resultado){
        List<Compras> compras = null;
        if (resultado != null) {
            compras = new ArrayList<>();
            for (Object[] fila : resultado) {
                Compras compra = new Compras();
                compra.setId((Integer) fila[0]);
                compra.setIdArticulo((Integer) fila[1]);
                compra.setIdComprador((Integer) fila[2]);
                compra.setIdVendedor((Integer) fila[3]);
                compra.setFechaComprar((Date) fila[4]);
                compras.add(compra);
            }
        } else {
            System.out.println("El resultado es nulo.");
        }
        return compras;
    }

}
