package es.masanz.ut3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {

    private Connection conexion;
    private String bd;
    private String usuario;
    private String contrasena;

    public GestorUsuarios(String bd, String usuario, String contrasena){
        this.bd = bd;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public boolean conectar(){
        boolean exito = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conexion = DriverManager.getConnection( "jdbc:mysql://localhost/" + bd, usuario, contrasena ) ;
            conexion.setAutoCommit(false);
            exito = true;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return exito;
    }

    public boolean desconectar(){
        boolean exito = false;
        try {
            if(this.conexion!=null && !this.conexion.isClosed()){
                conexion.close();
                exito = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exito;
    }

    public Usuario crearUsuario(String fullName, String user, String email, String password){
        PreparedStatement pst = null;
        try {
            String sql = "INSERT INTO usuarios (full_name, user, email, password, creation_date, modification_date) VALUES (?, ?, ?, ?, ?, ?)";
            pst = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
            Usuario usuario = new Usuario(fullName, user, email, password);
            pst.setString(1, usuario.getFullName());
            pst.setString(2, usuario.getUser());
            pst.setString(3, usuario.getEmail());
            pst.setString(4, usuario.getPassword());
            pst.setDate(5, new Date(usuario.getCreationDate().getTime()));
            pst.setDate(6, new Date(usuario.getModificationDate().getTime()));
            int registrosIncluidos = pst.executeUpdate();
            if (registrosIncluidos > 0) {
                ResultSet idsGenerados = pst.getGeneratedKeys();
                if (idsGenerados.next()) {
                    int idGenerado = idsGenerados.getInt(1);
                    usuario.setId(idGenerado);
                    System.out.println("ID generado:"+idGenerado);
                } else {
                    System.out.println("No se pudo obtener el ID generado.");
                }
            } else {
                System.out.println("La inserción no tuvo éxito.");
            }
            conexion.rollback();
            pst.close();
            return usuario;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean borrarUsuario(Usuario usuario){
        PreparedStatement pst = null;
        boolean borradoExitoso = false;
        try {
            String sql = "DELETE FROM usuarios WHERE user LIKE ?";
            pst = conexion.prepareStatement(sql);
            pst.setString(1, usuario.getUser());
            int registrosEliminados = pst.executeUpdate();
            if (registrosEliminados > 0) {
                borradoExitoso = true;
            }
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borradoExitoso;
    }

    public Usuario actualizarUsuario(Usuario usuario, String fullName, String user, String email, String password){
        PreparedStatement pst = null;
        try {
            String sql = "UPDATE usuarios SET full_name = ?, email = ?, password = ? , modification_date = ? WHERE user = ?";
            pst = conexion.prepareStatement(sql);
            //Usuario nuevoUsuario = new Usuario();
            //nuevoUsuario.setId(usuario.getId());
            //nuevoUsuario.setId(usuario.getId());
            //nuevoUsuario.setId(usuario.getId());
            //nuevoUsuario.setId(usuario.getId());
            //nuevoUsuario.setId(usuario.getId());
            //nuevoUsuario.setId(usuario.getId());
            usuario.setFullName(fullName);
            usuario.setEmail(email);
            usuario.setPassword(password);
            usuario.setModificationDate(new java.util.Date());
            pst.setString(1, usuario.getFullName());
            pst.setString(2, usuario.getEmail());
            pst.setString(3, usuario.getPassword());
            pst.setDate(4, (Date) new Date(usuario.getModificationDate().getTime()));
            pst.setString(5, usuario.getUser());
            int registrosActualizados = pst.executeUpdate();
            pst.close();
            if (registrosActualizados > 0) {
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Usuario> obtenerUsuarios(){
        PreparedStatement pst = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            String sql = "SELECT * FROM usuarios";
            pst = conexion.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String full_name = rs.getString(2);
                String user = rs.getString("user");
                String email = rs.getString("email");
                String password = rs.getString(5);
                Date creationDate = rs.getDate(6);
                Date modificationDate = rs.getDate("modification_date");
                Usuario usuario = new Usuario(full_name, user, email, password);
                usuario.setId(id);
                usuario.setCreationDate(creationDate);
                usuario.setModificationDate(modificationDate);
                usuarios.add(usuario);
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuarios;
    }

    public boolean autenticar(String user, String password){
        PreparedStatement pst = null;
        boolean exito = false;
        try {
            String sql = "SELECT * FROM usuarios WHERE user = ? AND password = ?";
            pst = conexion.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                exito = true;
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exito;
    }

}
