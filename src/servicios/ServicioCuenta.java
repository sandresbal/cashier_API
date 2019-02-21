package servicios;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	public void agregarCuenta(Cuenta c) {
		local.insertarCuenta(c.getNumeroCuenta(), c.getSaldo(), c.getTipocuenta())
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void altaContacto(Contacto con){
		local.altaContacto(con.getNombre(), con.getEmail(), con.getLoquesea());
	}
	}

}
