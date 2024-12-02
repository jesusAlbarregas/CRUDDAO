<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <h2>Elige uno o más usuarios para eliminar</h2>

            <form action="Delete" method="post">
                <table id="listado">

                    <thead>
                        <tr>
                            <th style="width: 5%;">Elige</th>
                            <th>Especie</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="usuario" items="${sessionScope.usuarios}">
                            <tr>

                                <td style="text-align: center;"><input type="checkbox" name="registro" value="${usuario.id}"/></td>

                                <td style="padding-left: 10px;"><c:out value="${usuario.apellidos}, ${usuario.nombre}"/></td>


                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="2"><p class="error"><c:out value="${requestScope.errorDelete}" default="" /></p></td>
                        </tr>
                        
                    </tfoot>
                </table>
                        <div class="flex">
                        <input type="submit" name="boton" value="Realizar" class="enlace">
                        <input type="submit" name="boton" value="Cancelar" class="enlace">
                    </div>

            </form>



        </div>
        <br />

    </body>
</html>