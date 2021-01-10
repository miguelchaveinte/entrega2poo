package es.uva.es.poo.clases;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rits.cloning.Cloner;

/**
 * Clase que implementa la creación de un {@link Trayecto} {@link Combinado} que agrupa en 
 * él los trayectos {@link TCamion} y {@link TBarco} y nos lo ofrece con un coste reducido.
 * En concreto los costes en barco tienen un descuento del 15%. También es posible realizar
 * un trayecto de {@link TTren} pero este no goza de descuento alguno.
 * @see Trayecto
 * @see Combinado
 * @see TTren
 * @see TBarco
 * @see TCamion
 * @author migchav,jhocaba
 */

public class PackCamionBarco extends Combinado {
	static final double DESCUENTO_BARCO=0.85;
	private List<Simple> trayectitos;
	boolean inicioPack;
	boolean finalPack;
	
	/**
	 * Crea un Trayecto Combinado de tipo PackCamionBarco, es decir,puede utilizar cualquiera
	 * de los tres tipos de transporte simple: {@link TTren},{@link TCamion} o {@link TBarco},
	 * pero solo aquellos que son de tipo {@link TBarco} tienen un 15% de descuento en el coste.
	 * y la 2 por camion).
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
	public PackCamionBarco(Muelle muelleOrigen,Puerto puertoOrigen,String fechaIni,Muelle muelleDestino,Puerto puertoDestino,String fechaFin) {
		super(muelleOrigen, puertoOrigen, fechaIni, muelleDestino,puertoDestino,fechaFin);
		trayectitos=new ArrayList<>();
		inicioPack=false;
		finalPack=false;
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addTrayecto(Simple trayecto) {
		if(trayecto==null) 
			throw new IllegalArgumentException ("El trayecto no puede ser nulo");
		if(containsTrayecto(trayecto)) 
			throw new IllegalArgumentException ("El trayecto ya ha sido incluido");
		if(trayectoRealiazado())
			throw new IllegalArgumentException ("El Pack trayecto ya llegó a su fin");
		trayectitos.add(trayecto);
		if(trayecto.getPuertoOrigen().equals(this.getPuertoOrigen())) 
			inicioPack=true;
		if(trayecto.getPuertoDestino().equals(this.getPuertoDestino()))
			finalPack=true;
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
		if(trayecto==null) 
			throw new IllegalArgumentException ("El trayecto no puede ser nulo");
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
	 * El coste trayecto en {@link PackCamionBarco} viene dado por los costes de 
	 * los trayectos simples {@link TTren},{@link TCamion} o {@link TBarco},
	 * pero solo aquellos que son de tipo {@link TBarco} tienen un 15% de descuento en el coste.
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
			if(trayecAnalisis instanceof TBarco) {
				costePack+=trayecAnalisis.costeTrayecto()*DESCUENTO_BARCO;
			}
			else {
				costePack+=trayecAnalisis.costeTrayecto();
			}
		}
		return costePack;
	}
}
