<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        
        <jsp:include page="/INC/cabecera.jsp">
            <jsp:param name="titulo" value="Actualizar" />
            <jsp:param name="estilo" value="${estilo}" />
        </jsp:include>
    </head>
    <body>
        <div id="principal">
            <h2>Informaci&oacute;n de la actualizaci&oacute;n del usuario</h2>
            <c:set var="estilo" value="normal" />
            <c:set var="mensaje" 
                   value="Se actualizó la información del usuario ${requestScope.actualizado}" />
            <c:if test="${requestScope.sincambios == true}">
                <c:set var="estilo" value="aviso" />
                <c:set var="mensaje" value="No se realizaron cambios en la información del usuario" />
            </c:if>
            
            <h3 class="${estilo}"><c:out value="${mensaje}" /></h3>

            <br />
            <form action="FrontController" method="post">
                <div class="flex">
                    <input type="submit" name="boton" value="(I) Inicio" class="enlace">
                </div>
            </form>
        </div>
    </body>
</html>