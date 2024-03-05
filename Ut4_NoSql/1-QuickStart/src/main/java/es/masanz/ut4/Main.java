package es.masanz.ut4;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;

public class Main {

    public static void main( String[] args ) {
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("acda");
            MongoCollection<Document> collection = database.getCollection("elementos");
            Document doc = collection.find(eq("Calorias", 350)).first();
            if (doc != null) {
                System.out.println("\n\n"+doc.toJson()+"\n\n");
            } else {
                System.out.println("No se han encontrado documentos.");
            }
        }
    }
}
