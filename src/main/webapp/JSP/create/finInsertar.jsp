<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        
        <jsp:include page="/INC/cabecera.jsp">
            <jsp:param name="titulo" value="Nuevo" />
            <jsp:param name="estilo" value="${estilo}" />
        </jsp:include>
    </head>
    <body>


        <div id="principal">
            <h2>Informaci&oacute;n de un nuevo usuario</h2>
            <h3>Los datos introducidos en la base de datos son</h3>

            <div class="dato">
                <p>Nombre: <strong><c:out value="${requestScope.usuario.nombre}" /></strong></p>
                <div class="limpiar"></div>
            </div>
            <div class="dato">
                <p>Apellidos: <strong><c:out value="${requestScope.usuario.apellidos}" /></strong></p>
                <div class="limpiar"></div>
            </div>
            <div class="dato">
                <p>Fecha de nacimiento: <strong><fmt:formatDate type="date" dateStyle="long" 
                                value="${requestScope.usuario.fechaNacimiento}"/></strong></p>
                <div class="limpiar"></div>
            </div>
            <div class="dato">
                <p>Género: <strong><c:out value="${requestScope.usuario.genero}" /></strong></p>
                <div class="limpiar"></div>
            </div>
            <div class="dato">
                <p>username: <strong><c:out value="${requestScope.usuario.userName}" /></strong></p>
                <div class="limpiar"></div>
            </div>
            <div class="dato">
                <p>Contraseña: <strong><c:out value="${requestScope.usuario.password}" /></strong></p>
                <div class="limpiar"></div>
            </div>
            <form action="FrontController" method="post">
                <div class="flex">
                    <input type="submit" name="boton" value="(I) Inicio" class="enlace">
                </div>
            </form>

        </div>    

    </body>
</html>