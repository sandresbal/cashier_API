package ejb;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entidades.Cuenta;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

/**
 * Session Bean implementation class GestionCuentas
 */
@Stateless
public class GestionCuentas implements GestionCuentasLocal {

    /*
    Hacemos uso de esta anotación que hace referencia a la unidad de persistencia
    declarada en persistence.xml. Esto nos permite instanciar un objeto EntityManager
    que gestionará las operaciones CRUD realizadas en esta clase.
    */
    @PersistenceContext(unitName = "actividad_11_Silvia_Andres_Balsera")
    EntityManager em;

    /**
     * Default constructor.
     */
    public GestionCuentas() {
        // TODO Auto-generated constructor stub
    }
    
    /*
    Sobreescribimos este método de la interfaz para determinar si una cuenta tiene o no
    acceso a la aplicación.
    Este método toma como argumento el número de cuenta introducido por el usuario. En él
    se crea un objeto de la clase Cuenta y, después, implementamos la interfaz TypedQuery 
    con objetos de tipo Cuenta para que recoja el resultado de la namedQuery 
    "Cuenta.Autenticar", que ejecuta un select sobre la tabla cuentas para encontrar la cuenta
    que se corresponde con el id, es decir, con el número que el método toma como argumento
    */

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Cuenta estaRegistrado(int num) {
        Cuenta c = null;
        TypedQuery<Cuenta> tq = em.createNamedQuery("Cuenta.Autenticar", Cuenta.class);
        /*
        En este bloque controlamos la excepción qeu se produciría si la query, tras 
        setear el parámetro presente en ella con el argumento "num", no devolviera nada.
        */
        
        try {
            tq.setParameter(1, num);
            c = tq.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Resultado nulo");
        }
        return c;
    }
    
    /* 
     * ACTIVIDAD 11: MÉTODO NUEVO
     * Este método permite buscar una cuenta una vez proporcionado su id
     * que se toma como parámetro del método
     */
    
    @Override
    public Cuenta buscarCuenta(int numCuenta) {
    	Cuenta c = em.find(Cuenta.class, numCuenta);
    	return c;
    }
    
    /* 
     * ACTIVIDAD 11: MÉTODO NUEVO
     * Este método permite buscar una cuenta una vez proporcionado su id
     * que se toma como parámetro del método
     */
    
    @Override
    public void insertarCuenta(Cuenta cuen) {
    	Cuenta nueva_c = new Cuenta();
    	nueva_c.setNumeroCuenta(cuen.getNumeroCuenta());
    	nueva_c.setSaldo(cuen.getSaldo());
    	nueva_c.setTipocuenta(cuen.getTipocuenta());
    	em.persist(nueva_c);
    }
    
    
    /*
    Este método actualizarSaldo toma como parámetros el número de la cuenta cuyo
    saldo se actualizará, la diferencia que se sumará o restará a ese saldo, así
    como el tipo de operación que determinará si se suma o se resta.
    
    */
    
    @Override
    public void actualizarSaldo(int numCuenta, double diferencia, String operacion){
        /*
        Declaramos o inicializamos los objetos necesarios para la operación de
        actualizar.
        Comenzamos creando un objeto de la clase Cuenta con el resultado de ejecutar
        el método find sobre la instancia de EntityManager que hemos creado al inicio.
        Ese método encuentra un objeto cuenta a base de ejecutar un select sobre la
        tabla cuentas con la condición de que ésta tenga un determinado identificador.
        */
        Cuenta c = em.find(Cuenta.class, numCuenta);
        Double antiguo_saldo = c.getSaldo();
        Double nuevo_saldo;
        
        /*
        Sumamos o restamos la diferencia al saldo en función del tipo de operación
        pasado como parámetro.
        */
        
        if (operacion.equals("suma")){
        nuevo_saldo = antiguo_saldo + diferencia;
        } else {
          nuevo_saldo = antiguo_saldo - diferencia;
        }
        /*
        Seteamos el nuevo saldo sobre la cuenta seleccionada
        */
        c.setSaldo(nuevo_saldo);
        System.out.print("la cuenta correspondiente al nuevo movimiento es "
                + c.getNumeroCuenta() + " y el nuevo saldo sería " + nuevo_saldo);
    }

}
