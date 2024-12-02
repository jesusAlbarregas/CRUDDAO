<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        
        <jsp:include page="/INC/cabecera.jsp">
            <jsp:param name="titulo" value="Acualizar usuario" />
            <jsp:param name="estilo" value="${estilo}" />
        </jsp:include>
    </head>
    <body>
        <div id="principal">
            <h2>Elige un usuario para cambiar sus datos</h2>

            <form action="Update" method="post">
                <table id="listado">

                    <thead>
                        <tr>
                            <th style="width: 5%;">Elige</th>
                            <th>Apellidos, nombre</th>

                        </tr>
                    </thead>
                    <tbody class="total">
                        <c:forEach var="usuario" items="${sessionScope.usuarios}">
                            <tr>

                                <td><input type="radio" name="registro" value="${usuario.id}"/></td>

                                <td style="padding-left: 10px;"><c:out value="${usuario.apellidos}, ${usuario.nombre}"/></td>


                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="2"><p class="error"><c:out value="${requestScope.errorUpdate}" default="" /></p></td>
                        </tr>
                        
                    </tfoot>
                </table>
                    <div class="flex">
                        <input type="submit" name="boton" value="Realizar" class="enlace">
                        <input type="submit" name="boton" value="Cancelar" class="enlace">
                    </div>
            </form>

        </div>  




    </body>
</html>