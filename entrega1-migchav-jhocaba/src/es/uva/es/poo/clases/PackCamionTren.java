package es.uva.es.poo.clases;

import java.util.*;

import java.time.LocalDate;

import com.rits.cloning.Cloner;

/**
 * Clase que implementa la creación de un {@link Trayecto} {@link Combinado} que agrupa en 
 * él los trayectos {@link TCamion} y {@link TTren} y nos lo ofrece con un coste reducido.
 * En concreto los nuevos costes vienen dados por 10 euros por kilometros recorrido en el trayecto,
 * sin costes fijos. También es posible realizar un trayecto de {@link TBarco} 
 * pero este no goza de descuento alguno.
 * @see Trayecto
 * @see Combinado
 * @see TTren
 * @see TBarco
 * @see TCamion
 * @author migchav,jhocaba
 */

public class PackCamionTren extends Combinado{
	static final int PRECIO_KM =10;
	static final double MILLAS_MARINAS=1.852;
	static final int TAM_MAX=2;
	private List<Simple> trayectitos;
	boolean inicioPack;
	boolean finalPack;
	boolean simpleCamion;
	boolean simpleTren;
	
	/**
	 * Crea un Trayecto Combinado de tipo PackCamionBarco, es decir,puede utilizar cualquiera
	 * de los tres tipos de transporte simple: {@link TTren},{@link TCamion} o {@link TBarco},
	 * pero solo aquellos que son de tipo {@link TCamion} y {@link TTren} 
	 * su nuevo calculo del precio es de 10 euros por kilometros recorrido en el trayecto,sin costes fijos.
	 * @param muelleOrigen El muelle de origen
	 * @param puertoOrigen El puerto de origen
	 * @param fechaIni La fecha de inicio de trayecto (Formato: aaaa-mm-dd)
	 * @param muelleDestino El muelle de destino
	 * @param puertoDestino El puerto de destino
	 * @param fechaFinFecha La fecha de fin de trayecto (Formato: aaaa-mm-dd)	
	 * @throws IllegalArgumentException Si @param muelleOrigen==null
	 * @throws IllegalArgumentException Si @param muelleDestino==null
	 * @throws IllegalArgumentException Si @param puertoOrigen==null
	 * @throws IllegalArgumentException Si @param puertoDestino==null
	 * @throws IllegalArgumentException Si @param puertoOrigen no contiene @param muelleOrigen
	 * @throws IllegalArgumentException Si @param puertoDestino no contiene @param muelleDestino
	 * @see LocalDate
	 * @see LocalDate#parse(CharSequence)
	 * @see Trayecto
	 * @see Combinado
	 * @see TTren
	 * @see TBarco
	 * @see TCamion
	 */
	public PackCamionTren(Muelle muelleOrigen,Puerto puertoOrigen,String fechaIni,Muelle muelleDestino,Puerto puertoDestino,String fechaFin) {
		super(muelleOrigen, puertoOrigen, fechaIni, muelleDestino,puertoDestino,fechaFin);
		trayectitos=new ArrayList<>();
		inicioPack=false;
		finalPack=false;
		simpleCamion=false;
		simpleTren=false;
	}

	/**
	 * {@inheritDoc}
	 * En este Pack ({@link PackCamionBarco} no se admiten los trayectos en {@link TBarco}.
	 * @throws IllegalArgumentException Si @param trayecto==null
	 * @throws IllegalArgumentException Si {@link PackCamionBarco#containsTrayecto(Simple)} 
	 * con @param trayecto ==true
	 * @throws IllegalArgumentException Si {@link PackCamionBarco#trayectoRealiazado()} ==true
	 * @throws IllegalArgumentException Si se han añadido previamente ya dos trayectos
	 * @throws IllegalArgumentException Si se añade un trayecto {@link TBarco}
	 * @throws IllegalArgumentException Si ya se ha introducido un trayecto {@link TCamion} previamente
	 * @throws IllegalArgumentException Si ya se ha introducido un trayecto {@link TTren} previamente
	 * @see PackCamionTren#comprobacionesToAdd(Simple)
	 */
	@Override
	public void addTrayecto(Simple trayecto) {
		comprobacionesToAdd(trayecto);
		trayectitos.add(trayecto);
		if(trayecto.getPuertoOrigen().equals(this.getPuertoOrigen())) 
			inicioPack=true;
		if(trayecto.getPuertoDestino().equals(this.getPuertoDestino()))
			finalPack=true;
	}
	
	/**
	 * Comprobaciones de las reglas de {@link PackCamionTren#addTrayecto(Simple)}
	 * @param trayecto El trayecto a añadir
	 * @throws IllegalArgumentException Si @param trayecto==null
	 * @throws IllegalArgumentException Si {@link PackCamionBarco#containsTrayecto(Simple)} 
	 * con @param trayecto ==true
	 * @throws IllegalArgumentException Si {@link PackCamionBarco#trayectoRealiazado()} ==true
	 * @throws IllegalArgumentException Si se han añadido previamente ya dos trayectos
	 * @throws IllegalArgumentException Si se añade un trayecto {@link TBarco}
	 * @throws IllegalArgumentException Si ya se ha introducido un trayecto {@link TCamion} previamente
	 * @throws IllegalArgumentException Si ya se ha introducido un trayecto {@link TTren} previamente
	 */
	private void comprobacionesToAdd(Simple trayecto) {
		if(trayecto==null) 
			throw new IllegalArgumentException ("El trayecto no puede ser nulo");
		if(containsTrayecto(trayecto)) 
			throw new IllegalArgumentException ("El trayecto ya ha sido incluido");
		if(trayectoRealiazado())
			throw new IllegalArgumentException ("El Pack trayecto ya llegó a su fin");
		if(trayectitos.size()>=TAM_MAX)
			throw new IllegalArgumentException ("El Pack ya se llenó");
		if(trayecto instanceof TBarco)
			throw new IllegalArgumentException ("Trayecto no valido");
		if(trayecto instanceof TTren && simpleTren)
			throw new IllegalArgumentException ("Trayecto en barco ya incluido,introduce uno de camion");
		if(trayecto instanceof TCamion && simpleCamion)
			throw new IllegalArgumentException ("Trayecto en camion ya incluido,introduce uno de barco");
		if(trayecto instanceof TTren) simpleTren=true;
		else simpleCamion=true;
	}
	
	/**
	 * {@inheritDoc}
	 * @see <a href="https://github.com/kostaskougios/cloning">Cloning Library</a>
	 */
	@Override
	public List<Simple> getTrayectosPack() {
		Cloner cloner=new Cloner();
		return cloner.deepClone(trayectitos);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsTrayecto(Simple trayecto) {
		return trayectitos.contains(trayecto);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean trayectoRealiazado() {
		return (inicioPack && finalPack);
	}
	
	/**
	 * {@inheritDoc}
	 * El coste trayecto en {@link PackCamionTren} viene dado por los costes de 
	 * que los que son de tipo {@link TTren} y {@linkTCamion} su precio
	 * es de 10 euros por kilometros recorrido en el trayecto,sin costes fijos.
	 * @see TTren
	 * @see TBarco
	 * @see TCamion
	 */
	@Override
	public double costeTrayecto() {
		double costePack=0.0;
		Iterator<Simple> iteradorTrayec=trayectitos.iterator();
		while(iteradorTrayec.hasNext()) {
			Simple trayecAnalisis=iteradorTrayec.next();
			costePack+=PRECIO_KM*trayecAnalisis.getDistancia()*MILLAS_MARINAS;			
		}
		return costePack;
	}




}
