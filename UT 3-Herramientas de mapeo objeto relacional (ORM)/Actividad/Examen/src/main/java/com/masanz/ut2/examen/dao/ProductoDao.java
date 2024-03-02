package com.masanz.ut2.examen.dao;

import com.masanz.ut2.examen.database.ConectionManager;

import java.util.ArrayList;
import java.util.List;

public class ProductoDao {
    public List<Producto> obtenerProductos() {

        String sql = "SELECT * FROM productos";
        Object[] params = {};
        Object[][] resultado = ConectionManager.ejecutarSelectSQL(sql, params);
        List<Producto> productos = procesarResultado(resultado);
        if(productos==null){
            productos = new ArrayList<>();
        }
        return productos;
    }

    private List<Producto> procesarResultado(Object[][] resultado){
        List<Producto> productos = null;
        if (resultado != null) {
            productos = new ArrayList<>();
            for (Object[] fila : resultado) {
                Producto producto = new Producto();
                producto.setId((Integer) fila[0]);
                producto.setNombre((String) fila[1]);
                producto.setValor((Integer) fila[2]);
                productos.add(producto);
            }
        } else {
            System.out.println("El resultado es nulo.");
        }
        return productos;
    }

    public Producto obtenerProducto(int id) {
        String sql = "SELECT * FROM productos WHERE id = ?";
        Object[] params = {id};
        Object[][] resultado = ConectionManager.ejecutarSelectSQL(sql, params);
        List<Producto> productos = procesarResultado(resultado);
        if(productos!=null && productos.size()==1){
            return productos.get(0);
        }
        return null;
    }

    public boolean eliminarProducto(int id) {
        boolean borradoExitoso = false;
        String sql = "DELETE FROM productos WHERE id = ?";
        Object[] params = {id};
        int productosEliminados = ConectionManager.ejecutarUpdateSQL(sql, params);
        if (productosEliminados > 0) {
            borradoExitoso = true;
        }
        return borradoExitoso;
    }

    public void crearProducto(int id, String nombre, int valor) {
        String sql = "INSERT INTO productos (id, nombre, valor) VALUES (?, ?, ?)";
        Object[] params = {id, nombre, valor};
        ConectionManager.ejecutarUpdateSQL(sql, params);
    }

    public void editarProducto(int id, String nombre, int valor) {
        String sql = "UPDATE productos SET nombre = ?, valor = ? WHERE id = ?";
        Object[] params = {nombre, valor, id};
        ConectionManager.ejecutarUpdateSQL(sql, params);
    }
}
