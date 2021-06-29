package es.uva.es.poo.clases;

import java.time.LocalDate;
import java.util.*;

/**
 * Clase abstracta que genera un {@link Trayecto} Combinado, los cuales ofertan varios
 * trayectos simples en formato Pack para mejorar su coste. Estos lo que hacen es 
 * agrupar en un trayecto global ,de inicio y fin marcado por el constructor del Pack, los
 * diferentes trayectos que se realizan y aquellos que sean 
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
	 * Añade el trayecto que se pasa como parámetro a la lista de los trayectos del Pack.
	 * Este debe cumplir que no sea null, y sea compatible con el Pack seleccionado.
	 * Se admiten un trayecto Camion y un trayecto en tren o en barco según el pack.
	 * @param trayecto El trayecto a añadir
	 */
	public abstract void addTrayecto(Simple trayecto);
	
	/**
	 * Retorna la lista clonada de los trayectos que almacena el Pack
	 * @see <a href="https://github.com/kostaskougios/cloning">Cloning Library</a>
	 * @see Simple
	 * @return La lista de los trayectos incluidos en el Pack
	 */
	public abstract List<Simple> getTrayectosPack();
	
	/**
	 * Indica si un trayecto ya está contenido en el Pack
	 * @param trayecto El trayacto para ver si esta contenido
	 * @return true si el trayecto ya esta contenido en el Pack.Si es al contrario,false.
	 * @see ArrayList#contains(Object)
	 */
	public abstract boolean containsTrayecto(Simple trayecto);
	
	/**
	 * Indica si el trayecto Pack ha sido completado,es decir, si hay un trayecto cuyo
	 * origen es el origen del Pack y si hay un trayecto cuyo destino es el destino del Pack.
	 * @return true si se cumplen ambas condiciones anteriores,es decir,si el Pack ya 
	 * ha sido cubierto de inicio a fin , sino retorna false
	 */
	public abstract boolean trayectoRealiazado();
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract double costeTrayecto();
}
