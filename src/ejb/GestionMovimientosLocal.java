package ejb;

import entidades.Movimiento;
import java.util.List;
import javax.ejb.Local;

/*
Esta interfaz contiene los métodos sobreescritos por GestionMovimientos
*/
@Local
public interface GestionMovimientosLocal {
    
    public void insertarMovimiento(int numCuenta, double cantidad, String tipo);
    public List<Movimiento> recuperarMovimientos();

}
