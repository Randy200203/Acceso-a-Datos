package com.masanz.ut2.examen.dao;

import com.masanz.ut2.examen.database.ConectionManager;

import java.util.ArrayList;
import java.util.List;

public class StockDao {
    public List<Stock> obtenerStockEmpresa(int id) {
        String sql = "SELECT * FROM stock WHERE idEmpresa = ?";
        Object[] params = {id};
        Object[][] resultado = ConectionManager.ejecutarSelectSQL(sql, params);
        List<Stock> stock = procesarResultado(resultado);
        return stock;
    }

    public List<Stock> obtenerStockCompleto() {
        String sql = "SELECT * FROM stock";
        Object[] params = {};
        Object[][] resultado = ConectionManager.ejecutarSelectSQL(sql, params);
        List<Stock> stock = procesarResultado(resultado);
        return stock;
    }

    private List<Stock> procesarResultado(Object[][] resultado){
        List<Stock> stock = null;
        if (resultado != null) {
            stock = new ArrayList<>();
            for (Object[] fila : resultado) {
                Stock almacen = new Stock();
                almacen.setId((Integer) fila[0]);
                almacen.setIdEmpresa((Integer) fila[1]);
                almacen.setIdProducto((Integer) fila[2]);
                almacen.setCantidad((Integer) fila[3]);
                stock.add(almacen);
            }
        } else {
            System.out.println("El resultado es nulo.");
        }
        return stock;
    }

    public Stock buscarStock(int idEmpresa, int idProducto) {
        String sql = "SELECT * FROM stock WHERE idEmpresa = ? AND idProducto = ?";
        Object[] params = {idEmpresa, idProducto};
        Object[][] resultado = ConectionManager.ejecutarSelectSQL(sql, params);
        List<Stock> stock = procesarResultado(resultado);
        if(stock!=null && stock.size()==1){
            return stock.get(0);
        }
        return null;
    }

    public void actualarStock(int id, int cantidad) {
        String sql = "UPDATE stock SET cantidad = ? WHERE id = ?";
        Object[] params = {cantidad, id};
        ConectionManager.ejecutarUpdateSQL(sql, params);
    }

    public void insertarStock(int id, int idEmpresa, int idProducto, int cantidad) {
        String sql = "INSERT INTO stock (id, idEmpresa, idProducto, cantidad) VALUES (?, ?, ?, ?)";
        Object[] params = {id, idEmpresa, idProducto, cantidad};
        ConectionManager.ejecutarUpdateSQL(sql, params);
    }

    public void borrarStock(int id) {
        String sql = "DELETE FROM stock WHERE id = ?";
        Object[] params = {id};
        ConectionManager.ejecutarUpdateSQL(sql, params);
    }
}
