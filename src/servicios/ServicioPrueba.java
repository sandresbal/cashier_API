package servicios;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/saludo") //añade una url al servicio
public class ServicioPrueba {
    @GET //el método se ejecutará cuando la petición sea GET
    @Produces(MediaType.TEXT_PLAIN) //formato de respuesta
    public String getSaludo() {
        return "Bienvenido a mi servicio";
    }
    @Path("{nombre}") //recibe en la url el parámetro "nombre"
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getSaludoPersonalizado(@PathParam("nombre") String n) {
        return "Bienvenido "+n+" a mi servicio";
    }
    
    //http://localhost:8080/actividad_11_Silvia_Andres_Balsera/rest/saludo/silvia
}