package es.uva.es.poo.clases;

import java.time.LocalDate;

/**
 * Clase abstracta que genera un {@link Trayecto} Simple. La característica que resalta de
 * estos es que aplican el coste del trayecto según el tipo de medio de transporte utilizado.
 * Las posibilidades de estos son según el medio de transporte: {@link TBarco}, {@link TCamion}
 * y {@link TTren}
 * @see Trayecto
 * @see TTren
 * @see TBarco
 * @see TCamion
 * @author migchav,jhocaba
 *
 */
public abstract class Simple extends Trayecto {
	/**
	 * Crea un Trayecto Simple
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
	public Simple(Muelle muelleOrigen,Puerto puertoOrigen,String fechaIni,Muelle muelleDestino,Puerto puertoDestino,String fechaFin) {
		super(muelleOrigen, puertoOrigen, fechaIni, muelleDestino,puertoDestino,fechaFin);
	}

	/**
	 * Obtenemos el numero que codifica que tipo de transporte se esta utilizando para ese transporte.
	 * @return El numero que indica el medio de transporte:0 es el correspodiente al transporte por barco,
	 * el 1 al transporte por tren y el 2 al transporte por camión.
	 * @see TTren#getCodigoSimple()
	 * @see TBarco#getCodigoSimple()
	 * @see TCamion#getCodigoSimple()
	 */
	public abstract int getCodigoSimple();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract double costeTrayecto();
	/**
	 * {@inheritDoc}
	 */
}
