package es.albarregas.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* A este servlet llegan las peticiones realizas desde:
*   - /JSP/create/finInsertar.jsp       donde se han visualizado los datos añadidos a la base de datos
*   - /JSP/update/finActualizar.jsp     donde se ha informado de la actualización de los campos de la base datos
*   - /JSP/delete/finEliminar.jsp       donde se ha informado de los registros eliminados de la base de datos
*   - /Create.java, /Update.java
*       /Delete.java                    cuando hemos cancelado el proceso en el primer paso de añadir, actualizar o eliminar:
*       + /JSP/create/inicioInsertar.jsp    
*       + /JSP/update/leerActualizar.jsp    
*       + /JSP/delete/leerEliminar.jsp 
*   - /Create.java, /Update.java
*       /Delete.java                    cuando hemos cancelado el proceso en el último paso de actualizar o eliminar:
*       + /JSP/update/actualizar.jsp
*       + /JSP/delete/eliminar.jsp
*/
@WebServlet(name = "EndController", urlPatterns = {"/EndController"})
public class EndController extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Comprobamos que existe el atributo de sesión usuarios y en caso afirmativo se elimina
        if(request.getSession().getAttribute("usuarios") != null) {
            request.getSession().removeAttribute("usuarios");
        }

        // Comprobamos que existe el atributo de sesión listaDelete y en caso afirmativo se elimina
        if(request.getSession().getAttribute("listaDelete") != null) {
            request.getSession().removeAttribute("listaDelete");
        }
        
        // Comprobamos que existe el atributo de sesión usuario y en caso afirmativo se elimina
        if(request.getSession().getAttribute("usuario") != null) {
            request.getSession().removeAttribute("usuario");
        }
        
        // Dirigimos el flujo hacia el menú pricipal de la aplicación
        request.getRequestDispatcher(".").forward(request, response);
    }


}
