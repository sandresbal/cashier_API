<%@page import="ejb.GestionMovimientosLocal"%>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="entidades.Cuenta, entidades.Movimiento, ejb.GestionMovimientos,
        java.util.List, java.util.Iterator" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movimientos</title>
    </head>
    
    <!-- Dirigimos al usuario a index si llega a esta página sin estar autenticado -->

    <% if (session.getAttribute("cuenta") == null) {
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }%>
        
    <body>
        <!-- Creamos un objeto de la clase Cuenta para volcar sobre él el 
        atributo de sesión "cuenta", que es la cuenta con la que el usuario se ha
        logueado y en la que se realizarán las operaciones relevantes.
        Después, mostramos el número de cuenta de la misma y el saldo -->
        <% Cuenta c = (Cuenta) session.getAttribute("cuenta");%>
        <h1>Cuenta:  <%= c.getNumeroCuenta()%> </h1>
        <h1>Saldo:  <%= c.getSaldo()%> </h1>

        <table border="1">

            <tbody>

                <tr>
                    <th>Cantidad</th>
                    <th>Fecha</th>
                    <th>Tipo</th>

                </tr>
                <!-- Pintamos la tabla colocando en cada fila los datos de cada uno
                de los objetos de la clase Movimiento que nos traemos como atributo
                de petición desde el servlet Procesar.
                Con un objeto Iterator vamos recorriendo cada uno de los Movimientos
                de esa lista y sacando los datos para ponerlos en las colunmnas-->
                <% 
                    List<Movimiento> lista_movimientos = (List<Movimiento>) request.getAttribute("lista_movimientos");
                    Iterator<Movimiento> it = lista_movimientos.iterator();
                    while (it.hasNext()) {
                        Movimiento mov = it.next();
                %>                
                <tr>
                    <td><%=mov.getCantidad()%></td>
                    <td><%=mov.getFecha()%></td>
                    <td><%=mov.getOperacion()%></td>
                </tr>
                <%}%>

            </tbody>
        </table>
        <a href="opciones.jsp">Volver</a>
    </body>
</html>
