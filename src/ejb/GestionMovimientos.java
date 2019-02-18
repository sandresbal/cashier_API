package ejb;

import entidades.Movimiento;
import java.sql.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class GestionMovimientos
 */
@Stateless
public class GestionMovimientos implements GestionMovimientosLocal {
    @PersistenceContext(unitName = "actividad_11_Silvia_Andres_Balsera")
    EntityManager em;
    /**
     * Default constructor.
     */
    
    /*@Resource
    UserTransaction ut;*/
    
    public GestionMovimientos() {
        // TODO Auto-generated constructor stub
    }

    /*
    Este método insertarMovimiento toma como argumentos el número de cuenta en el
    que se produce el movimiento, la cantidad relevante así como el tipo de movimiento.
    */
    
    @java.lang.Override
    public void insertarMovimiento(int numeroCuenta, double cantidad, String tipo) {
        
        /*
        Comenzamos creando un objeto movimiento, sobre el que vamos seteando los
        distintos atributos en función del valor de los argumentos, salvo en el caso
        de la fecha, que es la del instante actual.
        */
        
        Movimiento m = new Movimiento();
        Date fecha = new Date(new java.util.Date().getTime());
        m.setIdCuenta(numeroCuenta);
        m.setFecha(fecha);
        m.setCantidad(cantidad);
        m.setOperacion(tipo);
        /*
        Después, hacemos uso del método persist del EntityManager instanciado más
        arriba para persistir la entidad Movimiento creada y el método flush
        insertará la entrada relevante en la tabla "movimientos" con los datos de m.
        */
        em.persist(m);
        em.flush();

        System.out.print("la cuenta correspondiente al nuevo movimiento es "
                + m.getIdCuenta());

    }
    
    /*
    Este método devuelve un List de objetos de la clase Movimiento. Instanciamos en tq la
    interfaz TipedQuery con objetos de la clase Movimiento y le damos como valor
    el resultado de ejecutar el método createNamedQuery sobre el objeto EntityManager em, 
    tomando como argumentos el nombre de la NamedQuery de la entidad Movimiento dedicado
    a ejecutar un select sin condiciones sobre todos los registros de la tabla movimientos
    */
    
    @Override
    public List<Movimiento> recuperarMovimientos() {
        TypedQuery<entidades.Movimiento> tq = em.createNamedQuery("Movimiento.findAll", Movimiento.class);
        return tq.getResultList();
    }

}
