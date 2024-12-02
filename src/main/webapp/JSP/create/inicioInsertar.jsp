<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <h2>A&ntilde;adir un nuevo usuario</h2>

            <form action="Create" method="post">
                <div class="dato">
                    <label>Nombre</label>
                    <input type='text' name='nombre' size='20' maxlength='15' placeholder='Ej. Pedro' value='<c:out value="${param.nombre}" default="" />'>
                    <div class="limpiar"></div>
                </div>
                <div class="dato">
                    <label>Apellidos</label>
                    <input type='text' name='apellidos' size='20' maxlength="30" placeholder='Ej. Pérez' value='<c:out value="${param.apellidos}" default="" />'>
                    <div class="limpiar"></div>
                </div>
                <div class="dato">
                    <label>Fecha nacimiento</label>
                    <input type='date' name='fechaNacimiento' value='<c:out value="${param.fechaNacimiento}" default="" />'>
                    <div class="limpiar"></div>
                </div>
                <div class="dato">
                    <label>Género</label>
                    <select name="genero">
                        <c:forTokens var="elemento" items="Mujer,Hombre,Otro" delims=",">
                            <c:set var="seleccionado" value=""/>
                            <c:if test="${elemento == param.genero}">
                                <c:set var="seleccionado" value="selected"/>
                            </c:if>
                            <option value="${elemento}" ${seleccionado}>${elemento}</option>
                        </c:forTokens>
                    </select>
                    <div class="limpiar"></div>
                </div>
                <div class="dato">
                    <label>Usuario</label>
                    <input type='text' name='userName' size='20' maxlength="20" placeholder='Ej. pedro' value='<c:out value="${param.userName}" default="" />'>
                    <div class="limpiar"></div>
                </div>
                    <div class="dato">
                    <label>Contraseña</label>
                    <input type='password' name='password' size='20'>
                    <div class="limpiar"></div>
                </div>
                    <div class="dato">
                    <label>Repite contraseña</label>
                    <input type='password' name='password2' size='20'>
                    <div class="limpiar"></div>
                </div>
                <div class="dato">
                    <p class="error"><c:out value="${errorCreate}" default=""/></p>
                </div>
                <div class="flex">
                    <input type="submit" name="boton" value="Nuevo" class="enlace" />
                    <input type="submit" name="boton" value="Cancelar" class="enlace" />
                </div>


            </form>

        </div>
    </body>
</html>