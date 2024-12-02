<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="estilo" value="/CSS/estilo.css" scope="application" />
<c:set var="contexto" value="${pageContext.request.contextPath}" scope="application"/>
<!DOCTYPE html>
<html lang="es">
    <head> 
        <jsp:include page="/INC/cabecera.jsp">
            <jsp:param name="titulo" value="CRUD" />
            <jsp:param name="estilo" value="${estilo}" />
        </jsp:include>
    </head>
    <body>
        <div id="principal">
            <h2>Operaciones básicas sobre una tabla de la base de datos</h2>
            <div id="secundario">
                <form action="FrontController" method="post">
                    
                    <p><input type="submit" name="boton" value="(C) A&ntilde;adir un nuevo usuario" class="enlace" /></p>
                    <p><input type="submit" name="boton" value="(R) Visualizar todos los usuarios" class="enlace" /></p>
                    <p><input type="submit" name="boton" value="(U) Modificar la información de un usuario" class="enlace" /></p>
                    <p><input type="submit" name="boton" value="(D) Eliminar uno o varios usuarios" class="enlace" /></p>

                </form>
            </div>
        </div>
    </body>
</html>
