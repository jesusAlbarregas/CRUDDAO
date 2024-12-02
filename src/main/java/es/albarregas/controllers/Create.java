package es.albarregas.controllers;

import es.albarregas.DAO.IUsuarioDAO;
import es.albarregas.DAOFactory.DAOFactory;
import es.albarregas.beans.Usuario;
import es.albarregas.models.EnumConverter;
import es.albarregas.models.Utilities;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

@WebServlet(name = "Create", urlPatterns = {"/Create"})
public class Create extends HttpServlet {

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

        String url = null;
        Usuario usuario = null;
        DAOFactory daoF = DAOFactory.getDAOFactory();
        IUsuarioDAO uDAO = daoF.getUsuarioDAO();

        String operacion = request.getParameter("boton");

        switch (operacion) {

            case "Nuevo":
                // Primero comprobamos que todos los campos estén rellenos y que la contraseña sea la misma
                Enumeration<String> parametros = request.getParameterNames();
                String error = Utilities.comprobarCampos(parametros, request);

                if (!error.equals("n")) {
                    /*
                     * En el caso de que exista error se realizan las siguientes funciones:
                     *   - Cargamos un atributo de petición explicando lo acontecido
                     *   - Dirigimos el flujo hacia el formulario de entrada de datos para insertar
                     */
                    url = "/JSP/create/inicioInsertar.jsp";
                    String aviso = "Las contraseñas no son iguales";
                    if (error.equals("v")) {
                        aviso = "Todos los campos obligatorios";
                    }
                    request.setAttribute("errorCreate", aviso);

                } else {

                    DateConverter converter = new DateConverter();
                    converter.setPattern("yyyy-MM-dd");
                    ConvertUtils.register(converter, Date.class);
                    ConvertUtils.register(new EnumConverter(), Usuario.Genero.class);
                    try {
                        usuario = new Usuario();
                        BeanUtils.populate(usuario, request.getParameterMap());

                        if (uDAO.addUsuario(usuario)) {
                            request.setAttribute("errorCreate", "El username ya existe en nuestra base de datos");
                            url = "/JSP/create/inicioInsertar.jsp";
                        } else {
                            request.setAttribute("usuario", usuario);
                            url = "/JSP/create/finInsertar.jsp";
                        }

                    } catch (IllegalAccessException | InvocationTargetException e) {

                        // Existe un error al utilizar la clase BeanUtils. Escribimos el logger y se visualiza error500.jsp
                        Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, e);

                    }
                }
                break;

            case "Cancelar":
                url = "EndController";
                break;

        }

        /*
        * Hacemos efectivo el flujo de la aplicación
         */
        request.getRequestDispatcher(url).forward(request, response);

    }
}
