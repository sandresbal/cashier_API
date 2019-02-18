package entidades;

import java.io.Serializable;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/*
Estas anotaciones de JPA establecen que la clase Cuenta debe entenderse como una 
entidad, que la tabla con la que se relaciona es "cuentas" y, después, declaramos
una serie de queries para hacer uso de ellas a partir de su name en los métodos de
GestionarCuentas
*/
@Entity
@Table(name="CUENTAS")
@NamedQueries({
	@NamedQuery(name="Cuenta.findAll", query="SELECT c FROM Cuenta c"),
	@NamedQuery(name="Cuenta.findByNum", query="SELECT c FROM Cuenta c Where c.numeroCuenta=:id"),
	@NamedQuery(name="Cuenta.Autenticar", query="SELECT c FROM Cuenta c WHERE c.numeroCuenta=?1")
})

public class Cuenta implements Serializable {
	private static final long serialVersionUID = 1L;

        /*
        En estas anotaciones indicamos la relación entre los atributos de esta clase
        y las columnas de la tabla cuentas.
        */
	@Id
        @Column(name = "numeroCuenta", nullable = false)
	private int numeroCuenta;
        @Column(name = "saldo", nullable = false)
	private double saldo;
        @Column(name = "tipocuenta", nullable = false)
	private String tipocuenta;
	
        
        /*
        Constructores y setters y getters
        */
	public Cuenta() {
	}

	public Cuenta(int numeroCuenta, Double saldo, String tipocuenta) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.saldo = saldo;
		this.tipocuenta = tipocuenta;
	}
	
	public int getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public String getTipocuenta() {
		return tipocuenta;
	}
	public void setTipocuenta(String tipocuenta) {
		this.tipocuenta = tipocuenta;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}