package es.masanz.ut3_ut4.mongo;

import com.mongodb.client.*;
import es.masanz.ut3_ut4.dto.CochesDTO;
import es.masanz.ut3_ut4.dto.RopasDTO;
import es.masanz.ut3_ut4.dto.SmartphonesDTO;
import es.masanz.ut3_ut4.dto.ZapatosDTO;
import org.bson.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MongoDao {

    private final MongoClient mongoClient;
    private final MongoCollection<Document> collection;

    public MongoDao(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        MongoDatabase database = mongoClient.getDatabase("actividad_Hiber_Mongo");
        this.collection = database.getCollection("Productos");
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

            collection.insertOne(documentoCoche);
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

            collection.insertOne(documentoRopa);
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

            collection.insertOne(documentoSmartphone);
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

            collection.insertOne(documentoZapato);
        }
    }

    // Métodos de búsqueda
    public CochesDTO buscarCochesPorId(int id) {
        Document query = new Document("id", id);
        FindIterable<Document> result = collection.find(query);
        Document document = result.first();
        if (document != null) {
            CochesDTO coches = new CochesDTO();
            coches.setId(document.getInteger("id"));
            coches.setNombre(document.getString("nombre"));
            coches.setPrecio(BigDecimal.valueOf(document.getDouble("precio")));
            coches.setModelo(document.getString("modelo"));
            coches.setKilometraje(document.getInteger("kilometraje"));
            coches.setColor(document.getString("color"));
            return coches;
        }
        return null;
    }

    public List<Document> buscarPrecio() {
        List<Document> result = new ArrayList<>();
        Document doc = new Document("precio", new Document("$gt", 10));

        // Buscar documentos que coincidan con el doc
        try (MongoCursor<Document> cursor = collection.find(doc).iterator()) {
            while (cursor.hasNext()) {
                result.add(cursor.next());
            }
        }

        return result;
    }

    public List<Document> buscarPorMarca(String marca) {
        List<Document> result = new ArrayList<>();
        Document doc = new Document("marca", new Document("==","Marca 1"));

        // Buscar documentos que coincidan con el doc
        try (MongoCursor<Document> cursor = collection.find(doc).iterator()) {
            while (cursor.hasNext()) {
                result.add(cursor.next());
            }
        }

        return result;
    }

    public List<Document> buscarPorNombreYPrecio(String nombre, double precio) {
        List<Document> result = new ArrayList<>();

        // Construir el doc para la búsqueda por nombre y precio
        Document doc = new Document();
        doc.append("nombre", nombre);
        doc.append("precio", new Document("$gt", precio)); // $eq significa "igual a"

        // Buscar documentos que coincidan con el doc
        try (MongoCursor<Document> cursor = collection.find(doc).iterator()) {
            while (cursor.hasNext()) {
                result.add(cursor.next());
            }
        }

        return result;
    }



}
