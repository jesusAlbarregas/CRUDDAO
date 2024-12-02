package es.albarregas.DAO;

import es.albarregas.beans.Usuario;
import es.albarregas.models.Utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jesus
 */
public class UsuarioDAO implements IUsuarioDAO {

    @Override
    public List<Usuario> getUsuarios() {
        Usuario usuario = null;
        List<Usuario> usuarios = null;
        ResultSet resultado = null;
        Statement sentencia = null;
        String sql = "SELECT * FROM usuarios";

        try {
            Connection connection = ConnectionFactory.getConnection();
            sentencia = connection.createStatement();

            resultado = sentencia.executeQuery(sql);
            // Cargamos un ArrayList con todos los registros de la tabla usuarios
            usuarios = new ArrayList();
            while (resultado.next()) {
                usuario = new Usuario();
                usuario.setId(resultado.getShort(1));
                usuario.setNombre(resultado.getString(2));
                usuario.setApellidos(resultado.getString(3));
                usuario.setFechaNacimiento(resultado.getDate(4));
                usuario.setGenero(Utilities.string2Genero(resultado.getString(5)));
                usuario.setUserName(resultado.getString(6));

                usuarios.add(usuario);
            }

        } catch (SQLException e) {

            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
        return usuarios;
    }

    @Override
    public List<Usuario> getUsuariosEscalar() {
        Usuario usuario = null;
        List<Usuario> usuarios = null;

        ResultSet resultado = null;
        Statement sentencia = null;
        String sql = "SELECT id,nombre,apellidos FROM usuarios";

        try {
            Connection connection = ConnectionFactory.getConnection();
            sentencia = connection.createStatement();

            resultado = sentencia.executeQuery(sql);
            // Cargamos un ArrayList con todos los registros de la tabla usuarios
            usuarios = new ArrayList();
            while (resultado.next()) {
                usuario = new Usuario();
                usuario.setId(resultado.getShort(1));
                usuario.setNombre(resultado.getString(2));
                usuario.setApellidos(resultado.getString(3));

                usuarios.add(usuario);
            }

        } catch (SQLException e) {

            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
        return usuarios;
    }

    @Override
    public Boolean addUsuario(Usuario usuario) {
        Boolean userNameRepetido = Boolean.FALSE;
        Connection connection = null;
        PreparedStatement preparada = null;
        String sql = "INSERT INTO usuarios (nombre,apellidos,fechaNacimiento,genero,username,password) "
                + "VALUES (?,?,?,?,?,md5(?))";
        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            preparada = connection.prepareStatement(sql);
            preparada.setString(1, usuario.getNombre());
            preparada.setString(2, usuario.getApellidos());
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            preparada.setDate(3, java.sql.Date.valueOf(formato.format(usuario.getFechaNacimiento())));
            preparada.setString(4, Utilities.genero2String(usuario.getGenero()));
            preparada.setString(5, usuario.getUserName());
            preparada.setString(6, usuario.getPassword());

            preparada.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                /*
                * Si existe el registro quiere decir que se intenta almacenar un registro con username duplicado
                 */
                userNameRepetido = Boolean.TRUE;
            } else {

                // Existe un error al intentar insertar un registro. Escribimos el logger y se visualiza error500.jsp
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
            }
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            this.closeConnection();
        }
        return userNameRepetido;
    }

    @Override
    public Usuario getUsuarioById(Short idEnt) {
        Usuario usuario = null;
        Connection connection = null;
        ResultSet resultado = null;
        PreparedStatement preparada = null;
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try {
            connection = ConnectionFactory.getConnection();
            preparada = connection.prepareStatement(sql);
            preparada.setShort(1, idEnt);
            resultado = preparada.executeQuery();

            resultado.next();
            usuario = new Usuario();
            usuario.setId(resultado.getShort(1));
            usuario.setNombre(resultado.getString(2));
            usuario.setApellidos(resultado.getString(3));
            usuario.setFechaNacimiento(resultado.getDate(4));
            usuario.setGenero(Utilities.string2Genero(resultado.getString(5)));
            usuario.setUserName(resultado.getString(6));
            usuario.setPassword(resultado.getString(7));

        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
        return usuario;
    }

    @Override
    public Boolean updateUsuario(Usuario usuario) {
        Boolean userNameRepetido = Boolean.FALSE;
        Connection connection = null;
        PreparedStatement preparada = null;
        String sql = "UPDATE usuarios SET nombre=?,apellidos=?,fechaNacimiento=?,"
                + "genero=?,username=?,password=md5(?) WHERE id=?";
        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparada = connection.prepareStatement(sql);
            preparada.setString(1, usuario.getNombre());
            preparada.setString(2, usuario.getApellidos());
            preparada.setDate(3, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            preparada.setString(4, Utilities.genero2String(usuario.getGenero()));
            preparada.setString(5, usuario.getUserName());
            preparada.setString(6, usuario.getPassword());
            preparada.setShort(7, usuario.getId());
            preparada.executeUpdate();
            connection.commit();

        } catch (SQLException e) {

            if (e.getErrorCode() == 1062) {
                /*
                * Si existe el registro quiere decir que se intenta actualizar un registro con username duplicado
                 */
                userNameRepetido = Boolean.TRUE;
            } else {

                // Existe un error al intentar insertar un registro. Escribimos el logger y se visualiza error500.jsp
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
            }
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } finally {
            this.closeConnection();
        }
        return userNameRepetido;
    }

    @Override
    public List<Usuario> getUsuariosByCondicion(String condicionEnt) {
        Usuario usuario = null;
        List<Usuario> usuarios = null;
        Connection connection = null;
        ResultSet resultado = null;
        Statement sentencia = null;
        String sql = "SELECT * FROM usuarios" + condicionEnt;
        try {
            connection = ConnectionFactory.getConnection();
            sentencia = connection.createStatement();

            resultado = sentencia.executeQuery(sql);
            usuarios = new ArrayList();
            while (resultado.next()) {
                usuario = new Usuario();
                usuario.setId(resultado.getShort(1));
                usuario.setNombre(resultado.getString(2));
                usuario.setApellidos(resultado.getString(3));
                usuario.setFechaNacimiento(resultado.getDate(4));
                usuario.setGenero(Utilities.string2Genero(resultado.getString(5)));
                usuario.setUserName(resultado.getString(6));
                usuario.setPassword(resultado.getString(7));
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
                        
            // Existe un error al tratar el resultado devuelto. Escribimos el logger y se visualiza error500.jsp
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            this.closeConnection();
        }
        return usuarios;
    }

    @Override
    public void deleteUsuarioById(Short idEnt) {
        Connection connection = null;
        PreparedStatement preparada = null;
        String sql = "DELETE FROM usuarios WHERE id = ?";
        
        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparada = connection.prepareStatement(sql);
            preparada.setShort(1, idEnt);
            
            preparada.executeUpdate();
            connection.commit();

        } catch (SQLException e) {

            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConexion();
    }

}
