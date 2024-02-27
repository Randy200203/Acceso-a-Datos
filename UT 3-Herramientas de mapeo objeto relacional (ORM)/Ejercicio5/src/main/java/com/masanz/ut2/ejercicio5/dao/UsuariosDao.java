package com.masanz.ut2.ejercicio5.dao;

import com.masanz.ut2.ejercicio5.database.DatabaseManager;
import com.masanz.ut2.ejercicio5.dto.Usuario;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDao {

    public UsuariosDao(){

    }

    public Usuario crearUsuario(Usuario usuario){
        return crearUsuario(usuario.getFullName(), usuario.getUser(), usuario.getEmail(), usuario.getPassword(), usuario.getSaldo());
    }

    public Usuario crearUsuario(String fullName, String user, String email, String password, int saldo){
            Usuario usuario = new Usuario(fullName, user, email, password, saldo);
            String sql = "INSERT INTO usuarios (full_name, user, email, password, creation_date, modification_date) VALUES (?, ?, ?, ?, ?, ?)";
            Object[] params = {fullName, user, email, password, usuario.getCreationDate(), usuario.getModificationDate()};
            int registrosIncluidos = DatabaseManager.ejecutarUpdateSQL(sql, params);
            if(registrosIncluidos>0){
                // TODO: Actualizar usuario
                return usuario;
            }
            return null;
    }

    public boolean borrarUsuario(Usuario usuario){
        boolean borradoExitoso = false;
        String sql = "DELETE FROM usuarios WHERE user LIKE ?";
        Object[] params = { usuario.getUser() };
        int registrosEliminados = DatabaseManager.ejecutarUpdateSQL(sql, params);
        if (registrosEliminados > 0) {
            borradoExitoso = true;
        }
        return borradoExitoso;
    }

    public Usuario actualizarUsuario(Usuario usuario){
        String sql = "UPDATE usuarios SET full_name = ?, email = ?, password = ? , modification_date = ? , saldo = ? WHERE user = ?";
        Object[] params = {usuario.getFullName(), usuario.getEmail(), usuario.getPassword(), new java.util.Date(), usuario.getSaldo(), usuario.getUser()};
        int registrosActualizados = DatabaseManager.ejecutarUpdateSQL(sql, params);
        if (registrosActualizados > 0) {
            return usuario;
        }
        return null;
    }

    public Usuario obtenerUsuario(int userId, String user){
        String sql = "SELECT * FROM usuarios WHERE id = ? OR user = ? LIMIT 1";
        Object[] params = {userId, user};
        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
        List<Usuario> usuarios = procesarResultado(resultado);
        if(usuarios!=null && usuarios.size()==1){
            return usuarios.get(0);
        }
        return null;
    }

    public List<Usuario> obtenerUsuarios(){
        String sql = "SELECT * FROM usuarios";
        Object[] params = null;
        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
        List<Usuario> usuarios = procesarResultado(resultado);
        return usuarios;
    }

    public Usuario obtenerUltimoUsuario(){
        String sql = "SELECT * FROM usuarios ORDER BY id DESC LIMIT 1";
        Object[] params = null;
        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
        List<Usuario> usuarios = procesarResultado(resultado);
        if(usuarios!=null && usuarios.size()>0){
            return usuarios.get(0);
        }
        return null;
    }

    private List<Usuario> procesarResultado(Object[][] resultado){
        List<Usuario> usuarios = null;
        if (resultado != null) {
            usuarios = new ArrayList<>();
            for (Object[] fila : resultado) {
                Usuario usuario = new Usuario();
                usuario.setId((Integer) fila[0]);
                usuario.setFullName((String) fila[1]);
                usuario.setUser((String) fila[2]);
                usuario.setEmail((String) fila[3]);
                usuario.setPassword((String) fila[4]);
                usuario.setCreationDate((Date) fila[5]);
                usuario.setModificationDate((Date) fila[6]);
                usuario.setSaldo((Integer) fila[7]);
                usuarios.add(usuario);
            }
        } else {
            System.out.println("El resultado es nulo.");
        }
        return usuarios;
    }

    public Usuario autenticar(String user, String password){
        String sql = "SELECT * FROM usuarios WHERE user = ? AND password = ?";
        Object[] params = {user, password};
        Object[][] resultado = DatabaseManager.ejecutarSelectSQL(sql, params);
        List<Usuario> usuarios = procesarResultado(resultado);
        if (usuarios!=null && usuarios.size()>0) {
            return usuarios.get(0);
        }
        return null;
    }

    public boolean borrarUsuariosAusentes(int cantidad){
        boolean borradoExitoso = false;
        String sql = "DELETE FROM usuarios ORDER BY modification_date ASC LIMIT ?";
        Object[] params = {cantidad};
        int usuariosEliminados = DatabaseManager.ejecutarUpdateSQL(sql, params);
        if (usuariosEliminados > 0) {
            borradoExitoso = true;
        }
        return borradoExitoso;
    }

}
