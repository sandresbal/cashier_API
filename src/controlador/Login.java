package controlador;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.GestionCuentas;
import ejb.GestionCuentasLocal;
import entidades.Cuenta;
import modelo.Conexiones;

import javax.ejb.EJB;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
      
        /*
        Hacemos uso de la anotación @EJB para instanciar la interfaz de negocio
        de nuestro bean dedicado a la gestión de cuentas.
        Sobre ese objeto ejecutaremos los métodos para verificar la validez 
        del número de cuenta.
        */
        @EJB
        GestionCuentasLocal gc;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
            /*
            Comenzamos declarando los datos objetos que serán necesarios para la validación: 
            el entero que recoge el número ce cuenta introducido en el formulario del index;
            un objeto de la clase Cuenta, un RequestDispatcher para dirigir al usuario a una página u otra
            según sea el resultado de la operación.
            También creamos un objeto HttSession para almacenar como atributo el número
            de cuenta que se ha logueado exitosamente. Nos hará falta en el resto de la 
            aplicación.
            */
            
                int num = Integer.parseInt(request.getParameter("numeroCuenta"));
                Cuenta c = null;
		RequestDispatcher rd;
                HttpSession sesion = request.getSession();
                
                /*
                Según vimos, el objeto gc almacena la instancia de la interfaz de negocio del bean dedicado
                a la gestión de cuentas en el objeto gc. Ejecutamos el método estaRegistrado que toma 
                como parámetro el número de cuenta introducido. Ese método devuelve un objeto de la
                clase Cuenta.
                */
                
		c = gc.estaRegistrado(num);
                
                /*
                Si el objeto c no es nulo, es decir, si el método estaRegistrado devuelve un objeto, 
                es decir, si el select sobre la tabla cuentas no es nulo, admitimos al usuario y lo dirigimos
                a la pantalla de opciones, guardándonos, eso sí, ese objeto de la clase Cuenta que es c
                como atributo de sesión.
                Si c es nulo, emitimos el mensaje de que la cuenta no existe y volvemos a cargar
                el index.
                */
		if (c != null) {
                        sesion.setAttribute("cuenta", c);
			rd = request.getRequestDispatcher("opciones.jsp");
			rd.forward(request, response);
		} else {
	    	Connection cn = Conexiones.establecerConexion();

			request.setAttribute("mensaje", "cuenta no reconocida");
                        	rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}

}
