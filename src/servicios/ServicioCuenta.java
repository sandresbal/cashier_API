package servicios;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ejb.GestionCuentasLocal;
import entidades.Cuenta;

@Path("/cuentas")
public class ServicioCuenta {
	
	@EJB
	GestionCuentasLocal local;
	
	@GET
	@Path("{idCuenta}")
	@Produces(MediaType.APPLICATION_JSON)
	public Cuenta buscarCuenta(@PathParam("idCuenta") int id) {
		return local.buscarCuenta(id);
	}

}
