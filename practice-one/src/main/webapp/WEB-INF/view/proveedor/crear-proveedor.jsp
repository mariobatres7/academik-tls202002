<%-- 
    Document   : crear-proveedor
    Created on : Aug 31, 2020, 7:36:03 PM
    Author     : Mario Batres
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Proveedor</title>
    </head>
    <body>
        <h1>Crear Proveedor</h1>

        <span>${mensaje}</span>


        <form:form method="post" 
                   action="proveedor/crear" 
                   modelAttribute="proveedor">

            <form:label path="codigo">Código:</form:label> 
            <form:input path="codigo"  />
            <br/>

            <form:label path="nombreComercial">Nombre Comercial:</form:label> 
            <form:input path="nombreComercial" />
            <br/>

            <form:label path="direccion">Dirección:</form:label> 
            <form:textarea path="direccion" />
            <br/>


            <form:label path="email">Correo Electrónico:</form:label> 
            <form:input path="email" />
            <br/>

            <form:button class="">Enviar</form:button>
        </form:form>
    </body>
</html>
