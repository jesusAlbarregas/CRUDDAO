package es.albarregas.DAO;

import es.albarregas.beans.Usuario;

import java.util.List;

/**
 *
 * @author jesus
 */
public interface IUsuarioDAO {
    
    /**
     * Obtiene toda la información de todos los usuarios 
     * @return Lista con los usuarios
     */
    public List<Usuario> getUsuarios();
    
    /**
     * Obtiene id, nombre y apellidos de todos los usuarios
     * @return Lista con los usuarios
     */
    public List<Usuario> getUsuariosEscalar();
    
    /**
     * Añáde un nuevo registro a la tabla usuarios
     * @param usuario Objeto usuario que se pretende añadir
     */
    public Boolean addUsuario(Usuario usuario);
    
    /**
     * Obtiene un usuario a través de su identificativo
     * @param idEnt Identificativo del usuario que se pretende obtener
     * @return Objeto usuario obtenido
     */
    public Usuario getUsuarioById(Short idEnt);
    
    /**
     * Actualiza un registro de la tabla usuarios
     * @param usuario Objeto usuario que se pretende actualizar
     */
    public Boolean updateUsuario(Usuario usuario);
    
    /**
     * Obtiene una lista de usuarios a través de una condición
     * @param condicionEnt Cadena con la condición del filtrado
     * @return Lista de objetos usuarios
     */
    public List<Usuario> getUsuariosByCondicion(String condicionEnt);
    
    /**
     * Elimina un registro de la base de datos
     * @param idEnt Identificativo del usuario que se pretende eliminar
     */
    public void deleteUsuarioById(Short idEnt);
    
    /**
     * Abandona el hilo del pool de conexiones
     */
    public void closeConnection();
    
}
