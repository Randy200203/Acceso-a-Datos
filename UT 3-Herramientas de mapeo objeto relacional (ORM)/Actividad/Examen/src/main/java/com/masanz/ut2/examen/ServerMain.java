package com.masanz.ut2.examen;

import com.masanz.ut2.examen.dto.*;
import com.masanz.ut2.examen.service.Tareas;
import freemarker.template.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.*;

import static spark.Spark.*;

public class ServerMain {

    private static final Logger logger = LogManager.getLogger(ServerMain.class);

    private static Empresa empresaLogeada = null;

    public static void main(String[] args) {

        logger.info("ARRANCANDO APLICACION");

        staticFileLocation("/public");
        port(8080);

        FreeMarkerEngine freeMarker = new FreeMarkerEngine();
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        configuration.setClassForTemplateLoading(ServerMain.class, "/templates");
        freeMarker.setConfiguration(configuration);

        Tareas tareas = new Tareas();

        get("/visualizar-empresas", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("empresas", tareas.obtenerEmpresas());
            return new ModelAndView(model, "empresas.ftl");
        }, freeMarker);

        get("/formulario-alta-empresa", (request, response) -> {
            return new ModelAndView(null, "formulario-alta-empresa.ftl");
        }, freeMarker);

        get("/formulario-edicion-empresa/:id", (request, response) -> {
            int id = Integer.parseInt(request.params(":id"));
            Empresa empresaEditar = tareas.obtenerEmpresa(id);
            Map<String, Object> model = new HashMap<>();
            model.put("empresa", empresaEditar);
            return new ModelAndView(model, "formulario-edicion-empresa.ftl");
        }, freeMarker);

        get("/eliminar-empresa/:id", (request, response) -> {
            int id = Integer.parseInt(request.params(":id"));
            tareas.eliminarEmpresa(id);
            response.redirect("/visualizar-empresas");
            return null;
        }, freeMarker);

        post("/alta-empresa", (request, response) -> {
            try {
                int id = Integer.parseInt(request.queryParams("id"));
                String nombre = request.queryParams("nombre");
                String sector = request.queryParams("sector");
                int capital = Integer.parseInt(request.queryParams("capital"));
                int numEmpleados = Integer.parseInt(request.queryParams("numEmpleados"));

                if (nombre.isEmpty() || sector.isEmpty()) {
                    response.status(400); // Código de estado para solicitud incorrecta
                    return "Error: Los campos obligatorios no pueden estar vacíos.";
                }

                tareas.crearEmpresa(id, nombre, sector, capital, numEmpleados);

                response.redirect("/visualizar-empresas");

                return null;
            } catch (NumberFormatException e) {
                response.status(400);
                return "Error: Asegúrate de proporcionar valores numéricos válidos para ID, Capital y Número de Empleados.";
            }
        });

        post("/editar-empresa", (request, response) -> {
            try {
                int id = Integer.parseInt(request.queryParams("id"));
                String nombre = request.queryParams("nombre");
                String sector = request.queryParams("sector");
                int capital = Integer.parseInt(request.queryParams("capital"));
                int numEmpleados = Integer.parseInt(request.queryParams("numEmpleados"));

                // Asegurarse de que los campos obligatorios no estén vacíos
                if (nombre.isEmpty() || sector.isEmpty()) {
                    response.status(400);
                    return "Error: Los campos obligatorios no pueden estar vacíos.";
                }

                tareas.editarEmpresa(id, nombre, sector, capital, numEmpleados);

                response.redirect("/visualizar-empresas");

                return null;
            } catch (NumberFormatException e) {
                response.status(400);
                return "Error: Asegúrate de proporcionar valores numéricos válidos para ID, Capital y Número de Empleados.";
            }
        });

        get("/visualizar-productos", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("productos", tareas.obtenerProductos());
            return new ModelAndView(model, "productos.ftl");
        }, freeMarker);

        get("/formulario-alta-producto", (request, response) -> {
            return new ModelAndView(null, "formulario-alta-producto.ftl");
        }, freeMarker);

        get("/formulario-edicion-producto/:id", (request, response) -> {
            int id = Integer.parseInt(request.params(":id"));
            Producto productoEditar = tareas.obtenerProducto(id);
            Map<String, Object> model = new HashMap<>();
            model.put("producto", productoEditar);
            return new ModelAndView(model, "formulario-edicion-producto.ftl");
        }, freeMarker);

        get("/eliminar-producto/:id", (request, response) -> {
            int id = Integer.parseInt(request.params(":id"));
            tareas.eliminarProducto(id);
            response.redirect("/visualizar-productos");
            return null;
        }, freeMarker);

        post("/alta-producto", (request, response) -> {
            try {

                int id = Integer.parseInt(request.queryParams("id"));
                String nombre = request.queryParams("nombre");
                int valor = Integer.parseInt(request.queryParams("valor"));

                tareas.crearProducto(id, nombre, valor);

                response.redirect("/visualizar-productos");

                return null;
            } catch (NumberFormatException e) {
                response.status(400);
                return "Error: Asegúrate de proporcionar valores numéricos válidos para ID, Capital y Número de Empleados.";
            }
        });

        post("/editar-producto", (request, response) -> {
            try {

                int id = Integer.parseInt(request.queryParams("id"));
                String nombre = request.queryParams("nombre");
                int valor = Integer.parseInt(request.queryParams("valor"));

                tareas.editarProducto(id, nombre, valor);

                response.redirect("/visualizar-productos");

                return null;
            } catch (NumberFormatException e) {
                response.status(400);
                return "Error: Asegúrate de proporcionar valores numéricos válidos para ID, Capital y Número de Empleados.";
            }
        });

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "login.ftl");
        }, freeMarker);

        get("/error", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("error", "¡Empresa o sector incorrectos!");
            return new ModelAndView(model, "login.ftl");
        }, freeMarker);

        post("/login", (request, response) -> {
            String nombreEmpresa = request.queryParams("nombreEmpresa");
            String sector = request.queryParams("sector");

            empresaLogeada = tareas.buscarEmpresa(nombreEmpresa, sector);

            if (empresaLogeada!=null) {
                response.redirect("/home");
            } else {
                response.redirect("/error");
            }

            return null;
        });

        get("/home", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("empresa", empresaLogeada);
            model.put("productos", tareas.obtenerProductos());
            model.put("listadoStock", tareas.obtenerStockEmpresa(empresaLogeada));
            return new ModelAndView(model, "home.ftl");
        }, freeMarker);

        get("/comprar", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("productos", tareas.obtenerProductos());
            return new ModelAndView(model, "productos-comprar.ftl");
        }, freeMarker);

        get("/vender", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("productos", tareas.obtenerProductos());
            return new ModelAndView(model, "productos-vender.ftl");
        }, freeMarker);

        post("/comprar-productos", (request, response) -> {

            Set<String> queryParams = request.queryParams();
            String[] productosSeleccionados = request.queryParamsValues("productosSeleccionados");
            if(productosSeleccionados==null){
                productosSeleccionados = new String[0];
            }
            List<String> productosSeleccionadosList = Arrays.asList(productosSeleccionados);

            HashMap<Integer, Integer> productosParaComprar = new HashMap<>();

            for (String paramName : queryParams) {
                if (paramName.startsWith("cantidad-")) {
                    String id = paramName.substring("cantidad-".length());
                    if(productosSeleccionadosList.contains(id)) {
                        String cantidad = request.queryParams(paramName);
                        productosParaComprar.put(Integer.parseInt(id), Integer.parseInt(cantidad));
                    }
                }
            }

            tareas.comprarProductos(empresaLogeada, productosParaComprar);

            response.redirect("/home");

            return null;
        });

        post("/vender-productos", (request, response) -> {

            Set<String> queryParams = request.queryParams();
            String[] productosSeleccionados = request.queryParamsValues("productosSeleccionados");
            if(productosSeleccionados==null){
                productosSeleccionados = new String[0];
            }
            List<String> productosSeleccionadosList = Arrays.asList(productosSeleccionados);

            HashMap<Integer, Integer> productosParaVender = new HashMap<>();

            for (String paramName : queryParams) {
                if (paramName.startsWith("cantidad-")) {
                    String id = paramName.substring("cantidad-".length());
                    if(productosSeleccionadosList.contains(id)) {
                        String cantidad = request.queryParams(paramName);
                        productosParaVender.put(Integer.parseInt(id), Integer.parseInt(cantidad));
                    }
                }
            }

            tareas.venderProductos(empresaLogeada, productosParaVender);

            response.redirect("/home");

            return null;
        });

        init();
    }


}