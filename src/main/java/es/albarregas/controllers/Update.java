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

@WebServlet(name = "Update", urlPatterns = {"/Update"})
public class Update extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = null;
        Usuario usuario = null;
        DAOFactory daoF = DAOFactory.getDAOFactory();
        IUsuarioDAO uDAO = daoF.getUsuarioDAO();

        String opcion = request.getParameter("boton");

        switch (opcion) {
            case "Realizar":
                if (request.getParameter("registro") != null) {
                    /* 
                    * Hemos elegido actualizar algún registro para lo cual primero leemos el registro
                    * que queremos actualizar para mostrarselo al usuario
                     */
                    Short id = Short.parseShort(request.getParameter("registro"));
                    usuario = uDAO.getUsuarioById(id);
                    request.getSession().setAttribute("usuario", usuario);
                    url = "/JSP/update/actualizar.jsp";

                    
                } else {

                    // No hemos elegido ningún registro que actualizar y cargamos un atributo de petición con el error correspondiente
                    request.setAttribute("errorUpdate", "No se ha elegido ningún usuario");

                    url = "/JSP/update/leerActualizar.jsp";

                }
                break;

            case "Actualizar":
                Enumeration<String> parametros = request.getParameterNames();
                String error = Utilities.comprobarCampos(parametros, request);

                if (!error.equals("n")) {
                    /*
                     * En el caso de que exista error se realizan las siguientes funciones:
                     *   - Cargamos un atributo de petición explicando el error cometido
                     *   - Dirigimos el flujo hacia el formulario de entrada de datos para insertar
                     */
                    url = "/JSP/update/actualizar.jsp";
                    String aviso = "Las contraseñas no son iguales";
                    if (error.equals("v")) {
                        aviso = "Todos los campos obligatorios";
                    }
                    request.setAttribute("errorUpdate", aviso);
                    url = "/JSP/update/actualizar.jsp";
                } else {
                    // Todo correcto y cargamos el bean con los datos introducidos
                    DateConverter converter = new DateConverter();
                    converter.setPattern("yyyy-MM-dd");
                    ConvertUtils.register(converter, Date.class);
                    ConvertUtils.register(new EnumConverter(), Usuario.Genero.class);
                    try {
                        usuario = new Usuario();
                        BeanUtils.populate(usuario, request.getParameterMap());
                        // Convertimos la password introducida a MD5
                        usuario.setPassword(Utilities.md5(request.getParameter("password")));
                        // Obtenemos el objeto ave de sesión para compararlo con el actual
                        Usuario usuarioSesion = (Usuario) request.getSession().getAttribute("usuario");
                        
                        if (usuario.equals(usuarioSesion)) {
                            // No se han realizado cambios en ninguno de los campos y en la última página de actualizar informaremos de ello
                            request.setAttribute("sincambios", (Boolean) true);
                            url = "/JSP/update/finActualizar.jsp";
                        } else {

                            /* 
                            * Se han realizado cambios en el registro y por lo tanto actualizamos la base de datos y 
                            * en la página final de la actualización se informa que se ha cambiado el registro
                             */

                            if (uDAO.updateUsuario(usuario)) {
                                /* Si existe el registro quiere decir que se va a a intentar actualizarar un registro con username duplicado
                                *   - Cargamos un atributo de petición explicando lo acontecido
                                *   - Dirigimos el flujo hacia el formulario de entrada de datos para actualizar
                                */
                                request.setAttribute("errorUpdate", "El username ya existe en nuestra base de datos");
                                url = "/JSP/update/actualizar.jsp";
                            } else {
                                // Todo ha salido bien y nos dirigimos a la vista final de actualizar
                                request.setAttribute("actualizado", usuarioSesion.getId());
                                url = "/JSP/update/finActualizar.jsp";
                            }

                        }

                    } catch (IllegalAccessException | InvocationTargetException ex) {
                        Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

            case "Cancelar":
                url = "EndController";
                break;
        }

        // Hacemos efectivo el flujo de la aplicación
        request.getRequestDispatcher(url).forward(request, response);

    }
}
