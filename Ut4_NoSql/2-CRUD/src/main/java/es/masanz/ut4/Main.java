package es.masanz.ut4;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import es.masanz.ut4.dao.ProductoDAO;
import es.masanz.ut4.dto.Producto;
import org.bson.types.ObjectId;

public class Main {

    public static void main(String[] args) {
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            ProductoDAO productoDAO = new ProductoDAO(mongoClient);
            Producto nuevoProducto = new Producto(null, "Producto de prueba", 20);
            productoDAO.agregarProducto(nuevoProducto);
            ObjectId nuevoProductoId = nuevoProducto.getId();
            Producto productoObtenido = productoDAO.obtenerProducto(nuevoProductoId);
            productoObtenido.setNombre("Producto actualizado");
            productoDAO.actualizarProducto(productoObtenido);
            productoDAO.eliminarProducto(nuevoProductoId);
        }
    }

}
