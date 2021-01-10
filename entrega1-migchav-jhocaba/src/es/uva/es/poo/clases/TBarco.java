package es.uva.es.poo.clases;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Clase que implementa la creación de un {@link Trayecto} {@link Simple} que utiliza como
 * medio de transporte el Barco para transportar el contenedor. Su diferencia en cuanto al
 * coste de trayecto es que este viene dado que es de 4000 euros por día de trayecto.
 * @see Trayecto
 * @see Simple
 * @author migchav,jhocaba
 */

public class TBarco extends Simple {
	static final int COSTE_DIA=4000;
	private int codigoSimple;
	
	/**
	 * Crea un Trayecto Simple de tipo TBarco, es decir,utiliza el barco como
	 * medio de transporte para transportar el contenedor.
	 * En cuanto a nivel interno de codificación su codigo simple es 0.
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
	 * @see Trayecto
	 * @see Simple
	 * @see LocalDate#parse(CharSequence)
	 */
	
	public TBarco(Muelle muelleOrigen,Puerto puertoOrigen,String fechaIni,Muelle muelleDestino,Puerto puertoDestino,String fechaFin) {
		super(muelleOrigen, puertoOrigen, fechaIni, muelleDestino,puertoDestino,fechaFin);
		codigoSimple=0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCodigoSimple() {
		return codigoSimple;
	}

	
	/**
	 * {@inheritDoc}
	 * El coste en el caso de {@link TBarco} viene dado por 4000 euros por dia que 
	 * transcurre.
	 * @see ChronoUnit#between(java.time.temporal.Temporal, java.time.temporal.Temporal)
	 */
	@Override
	public double costeTrayecto() {
		return (double)ChronoUnit.DAYS.between(getFechaIni(),getFechaFin())*COSTE_DIA;
	}



}
