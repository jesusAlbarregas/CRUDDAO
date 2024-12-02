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
            <h2>Informaci&oacute;n sobre el borrado de registros</h2>
            
            <h3 class="normal"><c:out value="Se han eliminado ${requestScope.numero} registros" /></h3>
            
            <br />

            <form action="FrontController" method="post">
                <div class="flex">
                    <input type="submit" name="boton" value="(I) Inicio" class="enlace">
                </div>
            </form>
        </div>
    </body>
</html>