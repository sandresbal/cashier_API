package ejb;

import javax.ejb.Local;

import entidades.Cuenta;

/*
Esta interfaz contiene los métodos que implementará después GestionCuentas
*/
@Local
public interface GestionCuentasLocal {

	public Cuenta estaRegistrado(int num);
	
	
	public Cuenta buscarCuenta(int num);
	
	public void insertarCuenta(Cuenta c);

        
    public void actualizarSaldo(int numCuenta, double diferencia, String operacion);

}
