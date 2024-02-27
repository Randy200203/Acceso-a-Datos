package com.masanz.ut2.ejercicio5.dao;

import com.masanz.ut2.ejercicio5.database.DatabaseManager;
import com.masanz.ut2.ejercicio5.dto.Articulo;

import java.util.ArrayList;
import java.util.List;

public class ArticulosDao {

    public ArticulosDao(){

    }

    public Articulo crearArticulo(Articulo articulo){
        return crearArticulo(articulo.getId(), articulo.getNombre(), articulo.getValor(), articulo.getIdPropietario());
    }

    public Articulo crearArticulo(int id, String nombre, int valor, int idPropietario){
        Articulo articulo = new Articulo(id, nombre, valor, idPropietario);
        String sql = "INSERT INTO articulos (id, nombre, valor, id_propietario) VALUES (?, ?, ?, ?)";
        Object[] params = {id, nombre, valor, idPropietario};
        int registrosIncluidos = DatabaseManager.ejecutarUpdateSQL(sql, params);
        if(registrosIncluidos>0){
            return articulo;
        }
        return null;
    }

    public boolean borrarArticulo(Articulo articulo){
        boolean borradoExitoso = false;
        String sql = "DELETE FROM articulos WHERE id = ?";
        Object[] params = { articulo.getId() };
        int registrosEliminados = DatabaseManager.ejecutarUpdateSQL(sql, params);
        if (registrosEliminados > 0) {
            borradoExitoso = true;
        }
        return borradoExitoso;
    }

    public Articulo actualizarArticulo(Articulo articulo){
        return actualizarArticulo(articulo.getId(), articulo.getNombre(), articulo.getValor(), articulo.getIdPropietario());
    }

    private Articulo actualizarArticulo(int id, String nombre, int valor, int idPropietario) {
        String sql = "UPDATE articulos SET nombre = ?, valor = ?, id_propietario = ? WHERE id = ?";
        Object[] params = {nombre, valor, idPropietario, id};
        int registrosActualizados = DatabaseManager.ejecutarUpdateSQL(sql, params);
        if (registrosActualizados > 0) {
            Articulo articulo = new Articulo(id, nombre, valor, idPropietario);
            return articulo;
        }
        return null;
    }

    public List<Articulo> obtenerArticulos(){
        String sql = "SELECT * FROM articulos";
        Object[] params = null;
        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
        List<Articulo> articulos = procesarResultado(resultado);
        return articulos;
    }

    public Articulo obtenerArticulo(int articuloId){
        String sql = "SELECT * FROM articulos WHERE id = ?";
        Object[] params = {articuloId};
        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
        List<Articulo> articulos = procesarResultado(resultado);
        if(articulos!=null && articulos.size()>0){
            return articulos.get(0);
        }
        return null;
    }

    public List<Articulo> obtenerArticulosPropietario(int idPropietario){
        String sql = "SELECT * FROM articulos WHERE id_propietario = ?";
        Object[] params = {idPropietario};
        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
        List<Articulo> articulos = procesarResultado(resultado);
        return articulos;
    }

    private List<Articulo> procesarResultado(Object[][] resultado){
        List<Articulo> articulos = null;
        if (resultado != null) {
            articulos = new ArrayList<>();
            for (Object[] fila : resultado) {
                Articulo articulo = new Articulo();
                articulo.setId((Integer) fila[0]);
                articulo.setNombre((String) fila[1]);
                articulo.setValor((Integer) fila[2]);
                articulo.setIdPropietario((Integer) fila[3]);
                articulos.add(articulo);
            }
        } else {
            System.out.println("El resultado es nulo.");
        }
        return articulos;
    }

    public Articulo obtenerUltimoArticulo(){
        String sql = "SELECT * FROM articulos ORDER BY id DESC LIMIT 1";
        Object[] params = null;
        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
        List<Articulo> articulos = procesarResultado(resultado);
        if(articulos!=null && articulos.size()>0){
            return articulos.get(0);
        }
        return null;
    }

}
