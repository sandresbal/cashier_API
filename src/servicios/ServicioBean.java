package servicios;

import entidades.Cuenta;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/clientes")
public class ServicioBean {
    @GET
    @Path("{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cuenta getCliente(@PathParam("num") int n) {
        return new Cuenta(n, 100.0,  "ahorros");
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void agregarCliente(Cuenta c) {
        System.out.println("Recibido: "+c.getNumeroCuenta());
    }
}
