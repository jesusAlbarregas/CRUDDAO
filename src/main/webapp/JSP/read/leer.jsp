<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        
        <jsp:include page="/INC/cabecera.jsp">
            <jsp:param name="titulo" value="Listado de usuarios" />
            <jsp:param name="estilo" value="${estilo}" />
        </jsp:include>
    </head>
    <body>
        <div id="principal">
            <h2>Listado de todos los usuarios</h2>

            <table>
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th>Fecha de nacimiento</th>
                    <th>Género</th>
                    <th>Usuario</th>
                    
                </tr>
                <tbody class="total">
                    <c:forEach var="usuario" items="${requestScope.usuarios}">
                        <tr>
                            <td><c:out value="${usuario.id}" /></td>
                            <td><c:out value="${usuario.nombre}" /></td>

                            <td><c:out value="${usuario.apellidos}" /></td>
                            <td><fmt:formatDate type="date" dateStyle="long" value="${usuario.fechaNacimiento}"/></td> 
                            <td><c:out value="${usuario.genero}" /></td>
                            <td><c:out value="${usuario.userName}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>

                <tfoot>
                    <tr><td colspan="4">&nbsp;</td></tr>

                </tfoot>
            </table>
            <form action="FrontController" method="post">
                <div class="flex">
                    <input type="submit" name="boton" value="(I) Inicio" class="enlace">
                </div>
            </form>

        </div>

    </body>
</html>