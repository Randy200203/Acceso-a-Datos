package es.masanz.ut3_ut4;

import es.masanz.ut3_ut4.dao.CochesDao;
import es.masanz.ut3_ut4.dao.RopasDao;
import es.masanz.ut3_ut4.dao.SmartphonesDao;
import es.masanz.ut3_ut4.dao.ZapatosDao;
import es.masanz.ut3_ut4.dto.CochesDTO;
import es.masanz.ut3_ut4.dto.RopasDTO;
import es.masanz.ut3_ut4.dto.SmartphonesDTO;
import es.masanz.ut3_ut4.dto.ZapatosDTO;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPresistenceUnit");

        CochesDao cochesDao = new CochesDao(emf);
        RopasDao ropasDao = new RopasDao(emf);
        SmartphonesDao smartphonesDao = new SmartphonesDao(emf);
        ZapatosDao zapatosDao = new ZapatosDao(emf);

        Random random = new Random();

        // Generar 20 productos de cada tipo
        for (int i = 0; i < 20; i++) {
            cochesDao.crearCoche("Audi " + i, BigDecimal.valueOf(random.nextDouble() * 100000),
                    "A7 " + i, random.nextInt(100000), "Black " + i);
            ropasDao.crearRopa("NikeTech " + i, "Nike " + i, "XL " + i,
                    "Blue " + i, BigDecimal.valueOf(random.nextDouble() * 1000));
            smartphonesDao.crearSmartphone("Iphone " + i, "14proMax " + i, "IOS",
                    BigDecimal.valueOf(random.nextDouble() * 1000), random.nextInt(8) + 4);
            zapatosDao.crearZapato("Jordans " + i, BigDecimal.valueOf(random.nextDouble() * 200),
                    "Nike " + i, "Red " + i, "Leather " + i);
        }

        // Modificar 5 productos existentes
        for (int i = 0; i < 5; i++) {
            CochesDTO coche = cochesDao.buscarCochePorId(random.nextInt(20) + 1);
            if (coche != null) {
                coche.setModelo("RS6 " + i);
                cochesDao.actualizarCoche(coche);
            }

            RopasDTO ropa = ropasDao.buscarRopaPorId(random.nextInt(20) + 1);
            if (ropa != null) {
                ropa.setMarca("Adidas " + i);
                ropasDao.actualizarRopa(ropa);
            }

            SmartphonesDTO smartphone = smartphonesDao.buscarSmartphonePorId(random.nextInt(20) + 1);
            if (smartphone != null) {
                smartphone.setModelo("15proMax " + i);
                smartphonesDao.actualizarSmartphone(smartphone);
            }

            ZapatosDTO zapato = zapatosDao.buscarZapatoPorId(random.nextInt(20) + 1);
            if (zapato != null) {
                zapato.setColor("Green " + i);
                zapatosDao.actualizarZapato(zapato);
            }
        }

        // Eliminar 5 productos al azar
        for (int i = 0; i < 5; i++) {
            cochesDao.eliminarCoche(random.nextInt(20) + 1);
            ropasDao.eliminarRopa(random.nextInt(20) + 1);
            smartphonesDao.eliminarSmartphone(random.nextInt(20) + 1);
            zapatosDao.eliminarZapato(random.nextInt(20) + 1);
        }

        emf.close();
    }

}