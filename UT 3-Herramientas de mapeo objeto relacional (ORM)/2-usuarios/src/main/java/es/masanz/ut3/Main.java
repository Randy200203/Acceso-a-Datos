package es.masanz.ut3;

import es.masanz.ut3.dao.UsuarioDAO;
import es.masanz.ut3.dto.UsuariosDTO;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nombreUnidadPersistencia");

        UsuarioDAO usuariosDAO = new UsuarioDAO(emf);
        UsuariosDTO usuario = usuariosDAO.crearUsuario("Randy02", "cj", "rachana@gmail.com", "rachana");
        usuariosDAO.actualizarUsuario(usuario, "Achana", "aaa", "aaa@gmail.com", "aaaa");
        List<UsuariosDTO> usuarios = usuariosDAO.obtenerUsuarios();
        for (UsuariosDTO usuariosDTO: usuarios){
            System.out.println(usuariosDTO);
        }


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

