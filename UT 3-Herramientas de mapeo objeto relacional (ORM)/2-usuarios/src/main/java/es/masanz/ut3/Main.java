package es.masanz.ut3;

import es.masanz.ut3.dao.UsuarioDAO;
import es.masanz.ut3.dto.UsuariosDTO;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nombreUnidadPersistencia");

        UsuarioDAO usuarioDAO = new UsuarioDAO(emf);
        UsuariosDTO usuario = usuarioDAO.crearUsuario("Randy02", "cj", "rachana@gmail.com", "rachana");
        usuarioDAO.borrarUsuario(usuario);


    }

//
//        GestorUsuarios gestorUsuarios = new GestorUsuarios("acda", "root", "root");
//        gestorUsuarios.conectar();
//        Usuario usuarioCreado = gestorUsuarios.crearUsuario("11111", "11111", "1111", "1111");
//        System.out.println(usuarioCreado);
//        //boolean borrado = gestorUsuarios.borrarUsuario(usuarioCreado);
//        //System.out.println("¿Usuario borrado? "+borrado);
//        Usuario usuarioActualizado = gestorUsuarios.actualizarUsuario(usuarioCreado, "aaa", "bbb", "ccc", "ddd");
//        List<Usuario> usuarios = gestorUsuarios.obtenerUsuarios();
//        System.out.println("TOTAL USUARIOS: "+usuarios.size());
//        gestorUsuarios.desconectar();
    }

