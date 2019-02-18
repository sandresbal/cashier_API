<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="entidades.Cuenta"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Opciones</title>
    </head>
    <!-- Dirigimos al usuario a index si llega a esta página sin estar autenticado -->
    <% if (session.getAttribute("cuenta") == null) {
            //RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            //rd.forward(request, response);
            Cuenta c = new Cuenta();
            c.setNumeroCuenta(100);
            c.setSaldo(34098);
            c.setTipocuenta("ahorros");
            session.setAttribute("cuenta", c);
        }%>
    <body>
        <a href="ingreso.jsp">Ingresar</a><br>
        <a href="extraccion.jsp">Extraer</a><br>
        <a href="Procesar?opcion=movimientos">Ver movimientos</a><br>
        <a href="transferencia.jsp">Transferencia</a><br>
    </body>
</html>