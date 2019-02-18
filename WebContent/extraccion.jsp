<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Extracción</title>
    </head>
    <!-- Dirigimos al usuario a index si llega a esta página sin estar autenticado -->

    <% if (session.getAttribute("cuenta") == null) {
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }%>
    <body>
        <form action="Procesar?opcion=extraer" method="post">
            <label for="cantidad">Introduce cantidad</label>
            <input type="number" name="cantidad">
            <input type="submit" value="enviar">
        </form>
    </body>
</html>