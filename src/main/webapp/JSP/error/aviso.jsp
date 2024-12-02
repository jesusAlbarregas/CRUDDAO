<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>

        <jsp:include page="/INC/cabecera.jsp">
            <jsp:param name="titulo" value="Notificación" />
            <jsp:param name="estilo" value="${estilo}" />
        </jsp:include>
    </head>
    <body>

        <div id="principal">
            <div class="aviso">
                <p><c:out value="${requestScope.aviso}" /></p>
            </div>
            <div class="limpiar"></div>
            <form action="FrontController" method="post">
                <div class="flex">
                    <input type="submit" name="boton" value="(I) Inicio" class="enlace">
                </div>
            </form>
        </div>
    </body>
</html>
