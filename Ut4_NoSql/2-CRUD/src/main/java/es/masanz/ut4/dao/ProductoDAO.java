package es.masanz.ut4.dao;

import com.mongodb.client.*;

import es.masanz.ut4.dto.Producto;
import org.bson.Document;
import org.bson.types.ObjectId;

public class ProductoDAO {

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public ProductoDAO(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        this.database = mongoClient.getDatabase("acda");
        this.collection = database.getCollection("productos");
    }

    public void agregarProducto(Producto producto) {
        Document document = new Document("nombre", producto.getNombre())
                .append("precio", producto.getPrecio());
        collection.insertOne(document);
        ObjectId id = document.getObjectId("_id");
        producto.setId(id);
    }

    public Producto obtenerProducto(ObjectId id) {
        Document document = collection.find(new Document("_id", id)).first();
        if (document != null) {
            return new Producto((ObjectId) document.get("_id"), document.getString("nombre"), document.getInteger("precio"));
        }
        return null;
    }

    public void actualizarProducto(Producto producto) {
        Document filter = new Document("_id", producto.getId());
        Document update = new Document("$set", new Document("nombre", producto.getNombre())
                .append("precio", producto.getPrecio()));

        collection.updateOne(filter, update);
    }

    public void eliminarProducto(ObjectId id) {
        collection.deleteOne(new Document("_id", id));
    }
}