package com.masanz.ut2.ejercicio5;

import com.masanz.ut2.ejercicio5.dao.ArticulosDao;
import com.masanz.ut2.ejercicio5.dao.ComprasDao;
import com.masanz.ut2.ejercicio5.dao.UsuariosDao;
import com.masanz.ut2.ejercicio5.database.DatabaseManager;
import com.masanz.ut2.ejercicio5.dto.ArticulosDTO;
import com.masanz.ut2.ejercicio5.dto.ComprasDTO;
import com.masanz.ut2.ejercicio5.dto.UsuariosDTO;
import freemarker.template.Configuration;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class ServerMain {

    private static final Logger logger = LogManager.getLogger(ServerMain.class);
    private static int loggedUserId = -1;
    private EntityManagerFactory emf;


    public static void main(String[] args) {

        // Tipos de logger: TRACE, DEBUG, INFO, WARN, ERROR
        logger.info("PODEIS EMPLEAR ESTE METODO PARA SACAR INFORMACION, COMO SI FUESE UN SOUT");
        logger.error("ESTE SIRVE PARA VOLCAR ERRORES, POR EJEMPLO, LAS EXCEPCIONES");

        staticFileLocation("/public");
        port(8080);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPersistenceUnit");



        FreeMarkerEngine freeMarker = new FreeMarkerEngine();
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        configuration.setClassForTemplateLoading(ServerMain.class, "/templates");
        freeMarker.setConfiguration(configuration);

//        DatabaseManager.conectar("acda", "root", "root");
        UsuariosDao usuariosDao = new UsuariosDao(emf);
        ArticulosDao articulosDao = new ArticulosDao(emf);
        ComprasDao comprasDao = new ComprasDao(emf);

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "login.ftl");
        }, freeMarker);

        get("/error", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("error", "¡Usuario o contraseña incorrectos!");
            return new ModelAndView(model, "login.ftl");
        }, freeMarker);

        post("/login", (request, response) -> {

            String username = request.queryParams("username");
            String password = request.queryParams("password");

            UsuariosDTO usuario = usuariosDao.autenticar(username, password);

            if (usuario!=null) {
                loggedUserId = usuario.getId();
                response.redirect("/home");
            } else {
                response.redirect("/error");
            }
            return null;
        });

        get("/home", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("userId", loggedUserId);
            return new ModelAndView(model, "home.ftl");
        }, freeMarker);

        get("/articulos", (request, response) -> {
            List<ArticulosDTO> articulos = articulosDao.obtenerArticulosPropietario(loggedUserId);
            Map<String, Object> model = new HashMap<>();
            model.put("mostrarIdExtra", false);
            model.put("articulos", articulos);
            return new ModelAndView(model, "articulos.ftl");
        }, freeMarker);

        get("/articulos/:id", (request, response) -> {
            int userId = Integer.parseInt(request.params(":id"));
            List<ArticulosDTO> articulos = articulosDao.obtenerArticulosPropietario(userId);
            Map<String, Object> model = new HashMap<>();
            model.put("mostrarIdExtra", true);
            model.put("articulos", articulos);
            return new ModelAndView(model, "articulos.ftl");
        }, freeMarker);

        get("/usuarios", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<UsuariosDTO> usuarios = usuariosDao.obtenerUsuarios();
            model.put("usuarios", usuarios);
            return new ModelAndView(model, "usuarios.ftl");
        }, freeMarker);

        get("/usuario/:id", (request, response) -> {
            int userId = Integer.parseInt(request.params(":id"));
            logger.info("BUSCANDO INFORMACION DEL USUARIO CON ID: "+userId);
            UsuariosDTO usuario = usuariosDao.obtenerUsuario(userId, null);
            Map<String, Object> model = new HashMap<>();
            model.put("usuario", usuario);
            return new ModelAndView(model, "usuario.ftl");
        }, freeMarker);

        get("/comprar/:id", (request, response) -> {
            int articuloId = Integer.parseInt(request.params(":id"));
            logger.info("COMPRANDO ARTICULO ID: "+articuloId);
            ArticulosDTO articulo = articulosDao.obtenerArticulo(articuloId);
            int valorArticulo = articulo.getValor();
            UsuariosDTO usuarioComprador = usuariosDao.obtenerUsuario(loggedUserId, null);
            UsuariosDTO usuarioVendedor = usuariosDao.obtenerUsuario(articulo.getIdPropietario(), null);
            if( usuarioComprador.getSaldo()>=valorArticulo && usuarioComprador.getId()!=usuarioVendedor.getId()){
                usuarioComprador.setSaldo(usuarioComprador.getSaldo() - valorArticulo);
                usuarioVendedor.setSaldo(usuarioVendedor.getSaldo() + valorArticulo);
                articulo.setIdPropietario(usuarioComprador.getId());
                usuarioComprador = usuariosDao.actualizarUsuario(usuarioComprador);
                usuarioVendedor = usuariosDao.actualizarUsuario(usuarioVendedor);
                articulo = articulosDao.actualizarArticulo(articulo);
                if(usuarioComprador!=null && usuarioVendedor!=null && articulo!=null){
                    comprasDao.crearCompras(articulo.getId(), usuarioComprador.getId(), usuarioVendedor.getId(),  new Date());
                    DatabaseManager.persistir();
                } else {
                    DatabaseManager.deshacer();
                }
                List<ComprasDTO> compras = comprasDao.obtenerCompras();
                for (ComprasDTO compra : compras) {
                    System.out.println(compra);
                }
            }
            response.redirect("/home");
            return null;
        }, freeMarker);

        init();
    }
}