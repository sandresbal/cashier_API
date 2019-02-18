<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Transferencia</title>
    </head>
    <!-- Dirigimos al usuario a index si llega a esta página sin estar autenticado -->
    <% if (session.getAttribute("cuenta") == null) {
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }%>
    <body>
        <form action="Procesar?opcion=transferencia" method="post">

            <label for="cantidad">Cantidad</label>
            <input name="cantidad" type="number"><br>
            <label for="destino">Cuenta destino:</label>
            <input name="destino" type="number"><br>
            <input type="submit" value="enviar">
        </form>

    </body>
</html>