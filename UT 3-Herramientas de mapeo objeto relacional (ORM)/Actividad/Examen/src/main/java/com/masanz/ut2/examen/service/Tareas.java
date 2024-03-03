package com.masanz.ut2.examen.service;

import com.masanz.ut2.examen.dao.EmpresaDao;
import com.masanz.ut2.examen.dao.ProductoDao;
import com.masanz.ut2.examen.dao.StockDao;
import com.masanz.ut2.examen.database.ConectionManager;

import java.util.*;

public class Tareas {

    EmpresaDao empresaDao;
    ProductoDao productoDao;
    StockDao stockDao;
    //TODO: Se debera:
    // - Asignar la informacion necesaria a la clase ConectionManager
    // - Instanciar todos los DAO que sean necesarios
    public Tareas(){
        ConectionManager.conectar("ut2", "root", "root");
        empresaDao = new EmpresaDao();
        productoDao = new ProductoDao();
        stockDao = new StockDao();
    }

    //TODO: Se debera:
    // - Buscar una empresa que contenga el nombre y el sector facilitados
    // - En caso de no existir, devolver null
    public Empresa buscarEmpresa(String nombreEmpresa, String sector) {

        Empresa result = empresaDao.buscarEmpresa(nombreEmpresa, sector);

        return result;
    }

    //TODO: Se debera:
    // - Obtener todas las empresas de la BBDD y devolverlas
    // - Se devolvera un listado vacio en caso de no existir empresas
    public List<Empresa> obtenerEmpresas() {

        List<Empresa> result = empresaDao.obtenerEmpresas();
        return result;
    }

    //TODO: Se debera:
    // - Obtener la empresa solicitada por su id
    // - Se devolvera null en caso de no existir la empresa
    public Empresa obtenerEmpresa(int id) {
        Empresa result = empresaDao.obtenerEmpresa(id);
        return result;
    }

    //TODO: Se debera:
    // - Eliminar la empresa solicitada por su id
    public void eliminarEmpresa(int id) {
        empresaDao.eliminarEmpresa(id);
        ConectionManager.persistir();
    }

    //TODO: Se debera:
    // - Crear la empresa solicitada
    public void crearEmpresa(int id, String nombre, String sector, int capital, int numEmpleados) {
        empresaDao.crearEmpresa(id, nombre, sector, capital, numEmpleados);
        ConectionManager.persistir();
    }

    //TODO: Se debera:
    // - Editar la empresa solicitada
    // - El campo id sera la clave primaria, por lo que la empresa debera existir
    public void editarEmpresa(int id, String nombre, String sector, int capital, int numEmpleados) {
        empresaDao.editarEmpresa(id, nombre, sector, capital, numEmpleados);
        ConectionManager.persistir();
    }

    //TODO: Se debera:
    // - Obtener todos los productos de la BBDD y devolverlos
    // - Se devolvera un listado vacio en caso de no existir productos
    public List<Producto> obtenerProductos() {
        List<Producto> result = productoDao.obtenerProductos();
        return result;
    }

    //TODO: Se debera:
    // - Obtener el producto solicitado por su id
    // - Se devolvera null en caso de no existir el producto
    public Producto obtenerProducto(int id) {
        Producto result = productoDao.obtenerProducto(id);
        return result;
    }

    //TODO: Se debera:
    // - Eliminar el producto solicitada por su id
    // - Eliminar el stock de todas las empresas que contengan dicho producto
    public void eliminarProducto(int id) {
        productoDao.eliminarProducto(id);
        ConectionManager.persistir();
    }

    //TODO: Se debera:
    // - Crear el producto solicitado
    public void crearProducto(int id, String nombre, int valor) {
        productoDao.crearProducto(id, nombre, valor);
        ConectionManager.persistir();
    }

    //TODO: Se debera:
    // - Editar el producto solicitado
    // - El campo id sera la clave primaria, por lo que el producto debera existir
    public void editarProducto(int id, String nombre, int valor) {
        productoDao.editarProducto(id, nombre, valor);
        ConectionManager.persistir();
    }

    //TODO: Se debera:
    // - Obtener el listado de stock de la empresa indicada
    // - Se devolvera un listado vacio en caso de no existir stock para dicha empresa
    public List<Stock> obtenerStockEmpresa(Empresa empresaLogeada) {
        List<Stock> result = stockDao.obtenerStockEmpresa(empresaLogeada.getId());
        return result;
    }

    //TODO: Se debera:
    // - Ampliar el stock de los productos indicados en el mapa de productosParaComprar para la empresa indicada
    // - El mapa de productosParaComprar tendra como clave el id del producto y como valor la cantidad
    // - En caso de no existir stock para la empresa, incluirlo. En caso de existir, incrementarlo
    // - Se debera garantizar que la empresa dispone del capital necesario para la compra de tal manera que:
    //     - Se iran incluyendo o modificando los registros necesarios en la tabla stock de uno en uno
    //     - Se modificara el capital final de la empresa aunque el valor sea menor que cero
    //     - Si finalmente el valor registrado es menor a cero, se deberan deshacer los cambios
    public void comprarProductos(Empresa empresaLogeada, HashMap<Integer, Integer> productosParaComprar) {
        int idEmpresa = -1;
        int capitalInicial = empresaLogeada.getCapital();
        if(empresaLogeada!=null){
            idEmpresa = empresaLogeada.getId();
        } else {
            return;
        }

        if(productosParaComprar!=null && productosParaComprar.size()>0){


            Set<Map.Entry<Integer, Integer>> entrySet = productosParaComprar.entrySet();
            Iterator<Map.Entry<Integer, Integer>> iterator = entrySet.iterator();
            while(iterator.hasNext()){
                Map.Entry<Integer, Integer> entry = iterator.next();
                int idProducto = entry.getKey();
                int cantidad = entry.getValue();

                int capitalEmpresa = empresaLogeada.getCapital();
                Producto producto = productoDao.obtenerProducto(idProducto);
                int costeProductoUnidad = producto.getValor();

                Stock stock = stockDao.buscarStock(idEmpresa, idProducto);
                if (stock != null) {
                    stockDao.actualarStock(stock.getId(), stock.getCantidad() + cantidad);
                } else {
                    int id = stockDao.obtenerStockCompleto().size();
                    id++;
                    stockDao.insertarStock(id, idEmpresa, idProducto, cantidad);
                }

                capitalEmpresa = capitalEmpresa - (costeProductoUnidad*cantidad);

                empresaDao.editarEmpresa(empresaLogeada.getId(), empresaLogeada.getNombre(), empresaLogeada.getSector(), capitalEmpresa, empresaLogeada.getNumEmpleados());
                empresaLogeada.setCapital(capitalEmpresa);


            }

            if(empresaLogeada.getCapital()<0) {
                ConectionManager.deshacer();
                empresaLogeada.setCapital(capitalInicial);
            } else {
                ConectionManager.persistir();
            }
        }



    }

    //TODO: Se debera:
    // - Reducir el stock de los productos indicados en el mapa de productosParaVender para la empresa indicada
    // - El mapa de productosParaVender tendra como clave el id del producto y como valor la cantidad
    // - En caso de reducir el stock a 0, dicho registro debera ser borrado
    public void venderProductos(Empresa empresaLogeada, HashMap<Integer, Integer> productosParaVender) {

        int idEmpresa = -1;
        int capitalInicial = empresaLogeada.getCapital();
        if(empresaLogeada!=null){
            idEmpresa = empresaLogeada.getId();
        } else {
            return;
        }

        if(productosParaVender!=null && productosParaVender.size()>0) {

            boolean permitirOperacion = true;
            Set<Map.Entry<Integer, Integer>> entrySet = productosParaVender.entrySet();
            Iterator<Map.Entry<Integer, Integer>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Integer> entry = iterator.next();
                int idProducto = entry.getKey();
                int cantidad = entry.getValue();

                int capitalEmpresa = empresaLogeada.getCapital();
                Producto producto = productoDao.obtenerProducto(idProducto);
                int costeProductoUnidad = producto.getValor();

                Stock stock = stockDao.buscarStock(idEmpresa, idProducto);
                if(stock!=null){
                    int cantidadActual = stock.getCantidad();
                    if( (cantidadActual - cantidad) > 0){
                        stockDao.actualarStock(stock.getId(), stock.getCantidad() - cantidad);
                        capitalEmpresa = capitalEmpresa + (costeProductoUnidad*cantidad);
                        empresaLogeada.setCapital(capitalEmpresa);
                    } else if ((cantidadActual - cantidad) == 0) {
                        stockDao.borrarStock(stock.getId());
                        capitalEmpresa = capitalEmpresa + (costeProductoUnidad*cantidad);
                        empresaLogeada.setCapital(capitalEmpresa);
                    } else {
                        permitirOperacion = false;
                    }
                } else {
                    permitirOperacion = false;
                }

                empresaDao.editarEmpresa(empresaLogeada.getId(), empresaLogeada.getNombre(), empresaLogeada.getSector(), capitalEmpresa, empresaLogeada.getNumEmpleados());
                empresaLogeada.setCapital(capitalEmpresa);

            }

            if(permitirOperacion){

                ConectionManager.persistir();
            } else {
                empresaLogeada.setCapital(capitalInicial);
                ConectionManager.deshacer();
            }

        }

    }
}
