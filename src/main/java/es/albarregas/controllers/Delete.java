package es.albarregas.controllers;

import es.albarregas.DAO.IUsuarioDAO;
import es.albarregas.DAOFactory.DAOFactory;
import es.albarregas.beans.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Delete", urlPatterns = {"/Delete"})
public class Delete extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = null;
        StringBuilder clausulaWhere = null;
        List<Usuario> usuarios = null;
        List<Usuario> listaEliminar = new ArrayList<>();
        DAOFactory daoF = DAOFactory.getDAOFactory();
        IUsuarioDAO uDAO = daoF.getUsuarioDAO();

        String operacion = request.getParameter("boton");

        switch (operacion) {
            case "Realizar":
                String[] usuariosEliminar = request.getParameterValues("registro");
                if (usuariosEliminar != null && usuariosEliminar.length != 0) {
                    // Buscamos cada una de los usuarios que se van a eliminar 
                    // Formamos la cláusula WHERE del tipo in (1,2,3) 
                    clausulaWhere = new StringBuilder(" WHERE id IN (");
                    for (String usuariosEliminar1 : usuariosEliminar) {
                        clausulaWhere.append(usuariosEliminar1);
                        clausulaWhere.append(",");
                    }
                    clausulaWhere.replace(clausulaWhere.length() - 1, clausulaWhere.length(), ")");

                    usuarios = uDAO.getUsuariosByCondicion(clausulaWhere.toString());

                    // Guardamos los registros en un ArrayList y lo cargamos en un atributo de sesión                      
                    request.getSession().setAttribute("listaDelete", usuarios);

                    url = "/JSP/delete/eliminar.jsp";

                } else {
                    // No hemos elegido ningún registro que eliminar y cargamos un atributo de petición con el error correspondiente
                    request.setAttribute("errorDelete", "No se ha elegido ningún usuario");

                    url = "/JSP/delete/leerEliminar.jsp";
                }
                break;

            case "Eliminar":
                // El usuario ha confirmado la elimnación y procedemos a ello
                // Obtenemos de sesión la lista de avistamientos que se van a eliminar
                listaEliminar = (ArrayList<Usuario>) request.getSession().getAttribute("listaDelete");
                // Recorremos la lista para llamar al DAO e ir eliminando registro a registro
                Iterator<Usuario> itUsuario = listaEliminar.iterator();

                while (itUsuario.hasNext()) {
                    uDAO.deleteUsuarioById(itUsuario.next().getId());
                }
                // Pasamos por petición el número de registro eliminados
                request.setAttribute("numero", listaEliminar.size());
                // Y dirigimos el flujo hasta la última vista de eliminación
                url = "/JSP/delete/finEliminar.jsp";

                break;

            case "Cancelar":
                url = "EndController";
                break;
        }

        // Hacemos efectivo el flujo de la aplicación
        request.getRequestDispatcher(url).forward(request, response);

    }
}
