package ejb;

import javax.ejb.Local;

import entidades.Cuenta;

/*
Esta interfaz contiene los métodos que implementará después GestionCuentas
*/
@Local
public interface GestionCuentasLocal {

	public Cuenta estaRegistrado(int num);
        
        public void actualizarSaldo(int numCuenta, double diferencia, String operacion);

}
