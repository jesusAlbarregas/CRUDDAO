<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        
        <jsp:include page="/INC/cabecera.jsp">
            <jsp:param name="titulo" value="Error" />
            <jsp:param name="estilo" value="${estilo}" />
        </jsp:include>
    </head>
    <body>
        
        <div class="error">
            <a href="${contexto}/EndController"><img src="${contexto}/image/error.jpg"/></a>
        </div>
    </body>
</html>
