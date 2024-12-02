package es.albarregas.controllers;

import es.albarregas.DAO.IUsuarioDAO;
import es.albarregas.DAOFactory.DAOFactory;
import es.albarregas.beans.Usuario;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(".").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = ".";
        List<Usuario> usuarios = null;
        DAOFactory daoF = DAOFactory.getDAOFactory();
        IUsuarioDAO uDAO = daoF.getUsuarioDAO();
        
        String operacion = "I";

        if (request.getParameter("boton") != null) {
            operacion = request.getParameter("boton").substring(1, request.getParameter("boton").indexOf(")"));
        }

        switch (operacion) {
            case "C":
                // Hemos elegido crear un nuevo registro y dirigimos el flujo a la primera página de añadir
                url = "JSP/create/inicioInsertar.jsp";
                break;

            case "R":
                // Accedemos al DAO para obtner la lista de todos los usuarios
                usuarios = uDAO.getUsuarios();
                if (!usuarios.isEmpty()) {
                    /*
                    * En el caso de que existan registros en la tabla 
                    *   - Creamos un atributo de petición con los datos de todas los registros
                    *     Se pone en un atributo de petición ya que el recorrido de la información termina en la vista
                     */
                    request.setAttribute("usuarios", usuarios);

                    url = "JSP/read/leer.jsp";
                } else {
                    /*
                    * Cuando no existen registros en la tabla usuarios
                    * Cargamos un atributo de petición con el aviso que se mostrará y dirigimos el flujo a la página de notificación
                    */
                    request.setAttribute("aviso", "No existen datos almacenados");

                    url = "JSP/error/aviso.jsp";
                }

                break;
            case "U":

            case "D":
                /* En caso contrario leemos id, nombre y apellidos de todos los registros de la tabla 
                *   para visualizarlos para las operaciones de update y delete
                */
                usuarios = uDAO.getUsuariosEscalar();

                if (!usuarios.isEmpty()) {
                    /*
                    * En el caso de que existan registros en la tabla 
                    *   - Creamos un atributo de petición con los datos de todas los registros
                    *   - Dirigimos el flujo  a donde corresponda dependiendo de la variable operacion
                     */
                    request.getSession().setAttribute("usuarios", usuarios);
                    switch (operacion) {

                        case "U":

                            url = "JSP/update/leerActualizar.jsp";
                            break;

                        case "D":

                            url = "JSP/delete/leerEliminar.jsp";
                            break;
                    }
                } else {
                    /*
                    * Cuando no existen registros en la tabla usuarios
                    * Cargamos un atributo de petición con el aviso producido y dirigimos el flujo a la página de notificación
                     */
                    request.setAttribute("aviso", "No existen datos almacenados");

                    url = "JSP/error/aviso.jsp";
                }
            
            break;

            case "I":

                // Dirigimos el flujo hacia el controlador final donde se eliminarán los diferentes atributos de sesión
                url = "EndController";

        }

        request.getRequestDispatcher(url).forward(request, response);

    }

}
