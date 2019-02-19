package adaptadores;

import entidades.Cuenta;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class AdaptadorPrueba {
	
	//creamos un objeto Client como atributo estático 
	static Client c = ClientBuilder.newClient();
	
	private final String uriBase="http://localhost:8080/actividad_11_Silvia_Andres_Balsera/rest/saludo/";
	private WebTarget wt;
	//este objeto wt apuntará al servicio a través de su url
	
	public AdaptadorPrueba() {
		wt=c.target(uriBase);
	}	
	public String obtenerSaludo() {
	        return wt.request(MediaType.TEXT_PLAIN).get(String.class);
	}
	

}
