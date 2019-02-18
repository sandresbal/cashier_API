/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import ejb.GestionCuentasLocal;
import ejb.GestionMovimientosLocal;
import entidades.Cuenta;
import entidades.Movimiento;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author silvia
 */
@Stateless
public class Procesar extends HttpServlet {

    /*
    Estas anotaciones nos permiten hacer uso de dos instancias de las interfaces
    GestionMovimientosLocal y GestionCuentasLocal
    */
    @EJB
    GestionMovimientosLocal gm;
    @EJB
    GestionCuentasLocal gc;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

        /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    /**
     * En el método procesamos las operaciones de ingreso, retirada y transferencia.
     * También contemplamos la opción de ver movimientos: aunque la tabla de los mismos
     * se pinte en movimientos.jsp, en este servlet seteamos un List de objetos
     * de la clase Movimiento con el resultado de ejecutar el método
     * recuperaMovimientos sobre la instancia de GestionarMovimientos que hemos
     * creado más arriba.
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*
        Declaramos e inicializamos objetos y datos necesarios para las operaciones,
        trayéndonos como atributo de sesión la cuenta que el usuario introdujo en el
        login y como atributo de petición la opcion que ha elegido el usuario y que
        determina qué operación realizará el servlet.
        */
        HttpSession ses = request.getSession();
        Cuenta c = (Cuenta) ses.getAttribute("cuenta");
        String opcion = request.getParameter("opcion");
        RequestDispatcher rd;

        switch (opcion) {
            
            /*
            Si la operación es ingresar y la cantidad no es nula, nos traemos
            el parámetro cantidad y, a continuación, ejecutamos un método
            sobre la interfaz GestionarMovimientos: insertamos el movimiento
            pasando como parámetros el número de cuenta, la cantidad y el tipo
            de operación. Después, ejecutamos un método sobre la instancia de
            GestionarCuentas: actualizamos el saldo de la cuenta en la que se ha
            ingresado dinero, pasando como parámetro el número de cuenta,
            la cantidad y estableciendo que esa actualización del saldo viene a 
            sumar esa cantidad sobre el saldo remanente.
            Después, cargamos la página de opciones de nuevo.
            */
            
            case "ingresar":
                if (request.getParameter("cantidad") != null){
                Double cant = Double.parseDouble(request.getParameter("cantidad"));
                gm.insertarMovimiento(c.getNumeroCuenta(), cant, "ingreso");
                gc.actualizarSaldo(c.getNumeroCuenta(), cant, "suma");
                rd = request.getRequestDispatcher("opciones.jsp");
                rd.forward(request, response);
                break;
                }
                
            /*
            Si la operación es ver los movimientos de la cuenta con la que se ha
            logueado el usuario, creamos un List con el resultado de ejecutar
            el método recupararMovimientos sobre la instancia de GestionarMovimientos
            y después enviamos esa List como atributo de petición a la página de
            movimientos que se encargará de pintar la tabla con los movimientos.
            */  
                
            case "movimientos":
                List<Movimiento> lista_movimientos = gm.recuperarMovimientos();
                request.setAttribute("lista_movimientos", lista_movimientos);
                System.out.println("seteamos la lista de movimientos como atributo");
                rd = request.getRequestDispatcher("movimientos.jsp");
                rd.forward(request, response);
                break;
                
            /*
            Si la operación es extraer hacemos algo casi idéntico a la primera opción
            ingresar. Sólo varía el tipo de operación pasado como argumento al método
            actualizarSaldo: restaremos la cantidad que se saca del cajero al
            saldo de la cuenta
            */
                
            case "extraer":
                if (request.getParameter("cantidad") != null){
                Double cant = Double.parseDouble(request.getParameter("cantidad"));
                gm.insertarMovimiento(c.getNumeroCuenta(), cant, "retirada");
                gc.actualizarSaldo(c.getNumeroCuenta(), cant, "resta");
                rd = request.getRequestDispatcher("opciones.jsp");
                rd.forward(request, response);
                break;
                } 
                
            /*
            Si la operación es una transferencia nos traemos como parámetros
            la cantidad elegida y la cuenta destino, para después ejecutar sobre
            la instancia de GestionarCuentas el método insertarMovimiento, para
            insertarlo en la tabla movimientos y después, actualizamos el saldo
            de la cuenta ordenante y de la cuenta destino a partir del método
            actualizarSaldo ejecutado sobre la instancia de GestionarCuentas.
            Después de esto, dirigmos al usuario a la página de opciones de nuevo.
            */
                
            case "transferencia":
                if (request.getParameter("cantidad") != null){
                Double cant = Double.parseDouble(request.getParameter("cantidad"));
                int destino = Integer.parseInt(request.getParameter("destino"));
                gm.insertarMovimiento(c.getNumeroCuenta(), cant, "transferencia");
                gc.actualizarSaldo(c.getNumeroCuenta(), cant, "resta");
                gc.actualizarSaldo(destino, cant, "suma");
                rd = request.getRequestDispatcher("opciones.jsp");
                rd.forward(request, response);
                break;
                }                
            }
        
    
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
        public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
