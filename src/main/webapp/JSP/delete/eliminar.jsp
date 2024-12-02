<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="es">
    <head>

        <jsp:include page="/INC/cabecera.jsp">
            <jsp:param name="titulo" value="Eliminar" />
            <jsp:param name="estilo" value="${estilo}" />
        </jsp:include>
    </head>
    <body>
        <div id="principal">
            <h2>Usuarios que se van a eliminar</h2>
            <form action="Delete" method="post">
                <table>
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Apellidos</th>
                            <th>Fecha de nacimiento</th>
                            <th>Género</th>
                            <th>Usuario</th>
                        </tr>
                    </thead>

                    <tbody class="total">
                        <c:forEach var="usuario" items="${sessionScope.listaDelete}">
                            <tr>
                                <td><c:out value="${usuario.id}" /></td>

                                <td><c:out value="${usuario.nombre}" /></td>

                                <td><c:out value="${usuario.apellidos}" /></td>
                                <td><fmt:formatDate type="date" pattern="dd-MM-yyyy" value="${usuario.fechaNacimiento}"/></td>
                                <td><c:out value="${usuario.genero}" /></td>
                                <td><c:out value="${usuario.userName}" /></td>

                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr><td colspan="2">&nbsp;</td></tr>


                    </tfoot>

                </table>
                <div class="flex">
                    <input type="submit" name="boton" value="Eliminar" class="enlace">
                    <input type="submit" name="boton" value="Cancelar" class="enlace">
                </div>
            </form>
        </div>   

    </body>
</html>