package es.uva.es.poo.clases;

import java.time.LocalDate;

/**
 * Clase abstracta que genera un {@link Trayecto} Combinado, los cuales ofertan varios
 * trayectos simples en formato Pack para mejorar su coste. Estos se distinguen porque el trayecto
 * que generan es compatible con el Pack(ya que un pack soporta trayectos 
 * no agrupados para descuentos)tienen descuento en el coste y tambien aquellos 
 * trayectos posteriores,incluso si son del tipo {@link Simple},si ese trayecto es compatible con el Pack 
 * que generamos tambien obtiene ese descuento, hasta que el contenedor reciba otro tipo de Pack 
 * o un trayecto {@link Simple} no compatible con el Pack.
 * @see PackCamionBarco
 * @see PackCamionTren
 * @author migchav,jhocaba
 *
 */

public abstract class Combinado extends Trayecto {
	
	/**
	 * Crea un Trayecto Combinado
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
	 */
	public Combinado(Muelle muelleOrigen,Puerto puertoOrigen,String fechaIni,Muelle muelleDestino,Puerto puertoDestino,String fechaFin) {
		super(muelleOrigen, puertoOrigen, fechaIni, muelleDestino,puertoDestino,fechaFin);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract double costeTrayecto();
	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract int [] getTipoPack();
	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract int getCodigoSimple(); 
}
