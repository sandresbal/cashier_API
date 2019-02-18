<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Index</title>
    </head>
    <body>
    	
        <form action="Login" method="post">
            <label for="cuenta">Introduce cuenta:</label>
            <input type="number" name="numeroCuenta"/><br>
            <input type="submit" value="entrar">
        </form>
        <!-- Comenzamos con un formulario simple para loguearse que ejecuta el servlet Login
        tras enviar los datos.
        Reservamos, además, la posibilidad de mostrar un mensaje si lo emite el proceso de Login -->
        <% String mess = (String) request.getAttribute("mensaje");
    if (request.getAttribute("mensaje") != null) {%>
        <p><%= mess%></p>
        <%}%>
        </body>
        </html>