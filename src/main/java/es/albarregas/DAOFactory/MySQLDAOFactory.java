package es.albarregas.DAOFactory;

import es.albarregas.DAO.UsuarioDAO;
import es.albarregas.DAO.IUsuarioDAO;

/**
 * Fábrica concreta para la fuente de datos MySQL
 * @author jesus
 */
public class MySQLDAOFactory extends DAOFactory{

    @Override
    public IUsuarioDAO getUsuarioDAO() {
        return new UsuarioDAO();
    }
   
}
