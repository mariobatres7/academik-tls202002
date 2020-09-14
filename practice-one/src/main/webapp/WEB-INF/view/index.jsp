<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Proveedores</title>
    </head>
    <body>
        <h1>Listado de Proveedores</h1>

        <a href="${pageContext.servletContext.contextPath}/proveedor">Crear Proveedores</a>


        <table>
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Nombre Comercial</th>
                    <th>Dirección</th>
                    <th colspan="2">Opciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${proveedorList}" var="proveedor">
                    <tr>
                        <td>${proveedor.codigo}</td> 
                        <td>${proveedor.nombreComercial}</td> 
                        <td>${proveedor.direccion}</td> 
                        <td><a href="proveedor/codigo/${proveedor.codigo}">Modificar</a></td> 
                        <td><a href="proveedor/eliminar/${proveedor.codigo}">Eliminar</a></td> 
                    </tr>

                </c:forEach>

            </tbody>
        </table>
    </body>
</html>
