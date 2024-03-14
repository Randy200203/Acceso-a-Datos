package es.masanz.ut3_ut4.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import es.masanz.ut3_ut4.dto.CochesDTO;
import es.masanz.ut3_ut4.dto.RopasDTO;
import es.masanz.ut3_ut4.dto.SmartphonesDTO;
import es.masanz.ut3_ut4.dto.ZapatosDTO;
import org.bson.Document;

import java.util.List;

public class MongoDao {

    private final MongoClient mongoClient;
    private final MongoCollection<Document> cochesCollection;
    private final MongoCollection<Document> ropasCollection;
    private final MongoCollection<Document> smartphonesCollection;
    private final MongoCollection<Document> zapatosCollection;

    public MongoDao(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        MongoDatabase database = mongoClient.getDatabase("actividad_Hiber_Mongo");
        this.cochesCollection = database.getCollection("coches");
        this.ropasCollection = database.getCollection("ropas");
        this.smartphonesCollection = database.getCollection("smartphones");
        this.zapatosCollection = database.getCollection("zapatos");
    }

    public void agregarCoche(List<CochesDTO> coches) {
        for (CochesDTO coche : coches) {
            Document documentoCoche = new Document()
                    .append("id", coche.getId())
                    .append("nombre", coche.getNombre())
                    .append("precio", coche.getPrecio())
                    .append("modelo", coche.getModelo())
                    .append("kilometraje", coche.getKilometraje())
                    .append("color", coche.getColor());

            cochesCollection.insertOne(documentoCoche);
        }
    }

    public void agregarRopas(List<RopasDTO> ropasList) {
        for (RopasDTO ropa : ropasList) {
            Document documentoRopa = new Document()
                    .append("id", ropa.getId())
                    .append("nombre", ropa.getNombre())
                    .append("marca", ropa.getMarca())
                    .append("talla", ropa.getTalla())
                    .append("color", ropa.getColor())
                    .append("precio", ropa.getPrecio());

            ropasCollection.insertOne(documentoRopa);
        }
    }

    public void agregarSmartphone(List<SmartphonesDTO> smartphonesList) {
        for (SmartphonesDTO smartphone : smartphonesList) {
            Document documentoSmartphone = new Document()
                    .append("id", smartphone.getId())
                    .append("nombre", smartphone.getNombre())
                    .append("modelo", smartphone.getModelo())
                    .append("sistema_operativo", smartphone.getSistemaOperativo())
                    .append("precio", smartphone.getPrecio())
                    .append("memoria_ram", smartphone.getMemoriaRam());

            smartphonesCollection.insertOne(documentoSmartphone);
        }
    }

    public void agregarZapato(List<ZapatosDTO> zapatosList) {
        for (ZapatosDTO zapato : zapatosList) {
            Document documentoZapato = new Document()
                    .append("id", zapato.getId())
                    .append("nombre", zapato.getNombre())
                    .append("precio", zapato.getPrecio())
                    .append("marca", zapato.getMarca())
                    .append("color", zapato.getColor())
                    .append("material", zapato.getMaterial());

            zapatosCollection.insertOne(documentoZapato);
        }
    }

    // Método para buscar productos por nombre
//    public List<ProductoDTO> buscarProductosPorNombre(String nombre) {
//        List<ProductoDTO> productosEncontrados = new ArrayList<>();
//        Document query = new Document("nombre", nombre);
//
//        for (Document document : productosCollection.find(query)) {
//            productosEncontrados.add(documentToProductoDTO(document));
//        }
//
//        return productosEncontrados;
//    }
//
//    // Método para buscar productos por precio
//    public List<ProductoDTO> buscarProductosPorPrecio(BigDecimal precio) {
//        List<ProductoDTO> productosEncontrados = new ArrayList<>();
//        Document query = new Document("precio", precio.doubleValue());
//
//        for (Document document : productosCollection.find(query)) {
//            productosEncontrados.add(documentToProductoDTO(document));
//        }
//
//        return productosEncontrados;
//    }
}
