package es.masanz.ut3_ut4;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import es.masanz.ut3_ut4.dao.CochesDao;
import es.masanz.ut3_ut4.dao.RopasDao;
import es.masanz.ut3_ut4.dao.SmartphonesDao;
import es.masanz.ut3_ut4.dao.ZapatosDao;
import es.masanz.ut3_ut4.dto.CochesDTO;
import es.masanz.ut3_ut4.dto.RopasDTO;
import es.masanz.ut3_ut4.dto.SmartphonesDTO;
import es.masanz.ut3_ut4.dto.ZapatosDTO;
import es.masanz.ut3_ut4.mongo.MongoDao;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.bson.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPersistenceUnit");

        CochesDao cochesDao = new CochesDao(emf);
        RopasDao ropasDao = new RopasDao(emf);
        SmartphonesDao smartphonesDao = new SmartphonesDao(emf);
        ZapatosDao zapatosDao = new ZapatosDao(emf);

        Random random = new Random();

        generarProductos(cochesDao, ropasDao, smartphonesDao, zapatosDao, random);
        modificarProductos(cochesDao, ropasDao, smartphonesDao, zapatosDao, random);
        eliminarProductos(cochesDao, ropasDao, smartphonesDao, zapatosDao, random);

        emf.close();

        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDao mongoDAO = new MongoDao(mongoClient);
            obtenerDatos_EnviarAMongo(mongoDAO, emf); 
            mongoDAO.buscarPorMarca("Marca 1");

            //Para encontrar los datos fácilmente en la terminal buscar con CTRL+F la siguiente línea
            System.out.println("Datos de la consulta");
            List<Document> resultados = mongoDAO.buscarPorNombreYPrecio("Accesorio 1", 25.99);
            for (Document resultado : resultados) {
                System.out.println("");
                System.out.println(resultado);
                System.out.println("");
            }

        }
    }

    private static void generarProductos(CochesDao cochesDao, RopasDao ropasDao, SmartphonesDao smartphonesDao, ZapatosDao zapatosDao, Random random) {
        for (int i = 0; i < 20; i++) {
            try {
                cochesDao.crearCoche("Audi " + i, BigDecimal.valueOf(random.nextDouble() * 100000),
                        "A7 " + i, random.nextInt(100000), "Black " + i);
                System.out.println("Coche creado con éxito");
            } catch (Exception e) {
                System.out.println("Error al crear coche: " + e.getMessage());
            }
            try {
                ropasDao.crearRopa("NikeTech " + i, "Nike " + i, "XL " + i,
                        "Blue " + i, BigDecimal.valueOf(random.nextDouble() * 1000));
                System.out.println("Ropa creada con éxito");
            } catch (Exception e) {
                System.out.println("Error al crear ropa: " + e.getMessage());
            }
            try {
                smartphonesDao.crearSmartphone("Iphone " + i, "14proMax " + i, "IOS",
                        BigDecimal.valueOf(random.nextDouble() * 1000), random.nextInt(8) + 4);
                System.out.println("Smartphone creado con éxito");
            } catch (Exception e) {
                System.out.println("Error al crear smartphone: " + e.getMessage());
            }
            try {
                zapatosDao.crearZapato("Jordans " + i, BigDecimal.valueOf(random.nextDouble() * 200),
                        "Nike " + i, "Red " + i, "Leather " + i);
                System.out.println("Zapato creado con éxito");
            } catch (Exception e) {
                System.out.println("Error al crear zapato: " + e.getMessage());
            }
        }
    }

    private static void modificarProductos(CochesDao cochesDao, RopasDao ropasDao, SmartphonesDao smartphonesDao, ZapatosDao zapatosDao, Random random) {
        for (int i = 0; i < 5; i++) {
            try {
                CochesDTO coche = cochesDao.buscarCochePorId(random.nextInt(20) + 1);
                if (coche != null) {
                    coche.setModelo("RS6 " + i);
                    cochesDao.actualizarCoche(coche);
                    System.out.println("Coche actualizado con éxito");
                }
            } catch (Exception e) {
                System.out.println("Error al actualizar coche: " + e.getMessage());
            }

            try {
                RopasDTO ropa = ropasDao.buscarRopaPorId(random.nextInt(20) + 1);
                if (ropa != null) {
                    ropa.setMarca("Adidas " + i);
                    ropasDao.actualizarRopa(ropa);
                    System.out.println("Ropa actualizada con éxito");
                }
            } catch (Exception e) {
                System.out.println("Error al actualizar ropa: " + e.getMessage());
            }

            try {
                SmartphonesDTO smartphone = smartphonesDao.buscarSmartphonePorId(random.nextInt(20) + 1);
                if (smartphone != null) {
                    smartphone.setModelo("15proMax " + i);
                    smartphonesDao.actualizarSmartphone(smartphone);
                    System.out.println("Smartphone actualizado con éxito");
                }
            } catch (Exception e) {
                System.out.println("Error al actualizar smartphone: " + e.getMessage());
            }

            try {
                ZapatosDTO zapato = zapatosDao.buscarZapatoPorId(random.nextInt(20) + 1);
                if (zapato != null) {
                    zapato.setColor("Green " + i);
                    zapatosDao.actualizarZapato(zapato);
                    System.out.println("Zapato actualizado con éxito");
                }
            } catch (Exception e) {
                System.out.println("Error al actualizar zapato: " + e.getMessage());
            }
        }
    }

    private static void eliminarProductos(CochesDao cochesDao, RopasDao ropasDao, SmartphonesDao smartphonesDao, ZapatosDao zapatosDao, Random random) {
        for (int i = 0; i < 5; i++) {
            try {
                cochesDao.eliminarCoche(random.nextInt(20) + 1);
                System.out.println("Coche eliminado con éxito");
            } catch (Exception e) {
                System.out.println("Error al eliminar coche: " + e.getMessage());
            }

            try {
                ropasDao.eliminarRopa(random.nextInt(20) + 1);
                System.out.println("Ropa eliminada con éxito");
            } catch (Exception e) {
                System.out.println("Error al eliminar ropa: " + e.getMessage());
            }

            try {
                smartphonesDao.eliminarSmartphone(random.nextInt(20) + 1);
                System.out.println("Smartphone eliminado con éxito");
            } catch (Exception e) {
                System.out.println("Error al eliminar smartphone: " + e.getMessage());
            }

            try {
                zapatosDao.eliminarZapato(random.nextInt(20) + 1);
                System.out.println("Zapato eliminado con éxito");
            } catch (Exception e) {
                System.out.println("Error al eliminar zapato: " + e.getMessage());
            }
        }
    }

    public static void obtenerDatos_EnviarAMongo(MongoDao mongoDAO, EntityManagerFactory emf) {
        CochesDao cochesDao = new CochesDao(emf);
        RopasDao ropasDao = new RopasDao(emf);
        SmartphonesDao smartphonesDao = new SmartphonesDao(emf);
        ZapatosDao zapatosDao = new ZapatosDao(emf);

        List<CochesDTO> cochesList = cochesDao.getAllCoches();
        mongoDAO.agregarCoche(cochesList);

        List<RopasDTO> ropasList = ropasDao.getAllRopas();
        mongoDAO.agregarRopas(ropasList);

        List<SmartphonesDTO> smartphonesList = smartphonesDao.getAllSmartphones();
        mongoDAO.agregarSmartphone(smartphonesList);

        List<ZapatosDTO> zapatosList = zapatosDao.getAllZapatos();
        mongoDAO.agregarZapato(zapatosList);
    }
}
