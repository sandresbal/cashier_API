package entidades;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/*
Estas anotaciones establecen que la clase Movimientos debe entenderse como una 
entidad, que la tabla con la que se relaciona es "movimientos". Después, declaramos
una query para hacer uso de ellas a partir de su nombre ("Movimiento.findAll") 
en el método recuperarMovimientos de GestionMovimientos
*/

@Entity
@Table(name="movimientos")
@NamedQueries({
	@NamedQuery(name="Movimiento.findAll", query="SELECT m FROM Movimiento m"),

})

public class Movimiento implements Serializable {
	private static final long serialVersionUID = 1L;
        
        /*
        Los atributos de esta clase se acompañan de anotaciones que los ponen
        en relación con las columnas de la tabla "movimientos" y la clase de
        valores que admiten
        */

	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idMovimiento", nullable = false)
	private int idMovimiento;
        @Column(name = "idCuenta", nullable = false)
	private int idCuenta;
        @Column(name = "fecha", nullable = false)
	private Date fecha;
        @Column(name = "cantidad", nullable = false)
	private double cantidad;
        @Column(name = "operacion", nullable = false)
	private String operacion;
        
        //Constructores, setters y getters
        
        public Movimiento(){
        }
        
        public Movimiento(int idMovimiento, int idCuenta, Date fecha, double cantidad, String operacion){
            super();
            this.idMovimiento = idMovimiento;
            this.idCuenta = idCuenta;
            this.fecha = fecha;
            this.cantidad = cantidad;
            this.operacion = operacion;
        }
        
	public int getIdMovimiento() {
		return idMovimiento;
	}
	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}
	public int getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}