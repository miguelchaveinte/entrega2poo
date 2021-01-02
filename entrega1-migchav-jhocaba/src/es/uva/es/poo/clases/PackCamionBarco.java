package es.uva.es.poo.clases;
import java.time.LocalDate;

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
	private int[] tipoPack;
	private int tipoTrayecto;
	private int codigoSimple;
	
	/**
	 * Crea un Trayecto Combinado de tipo PackCamionBarco, es decir,puede utilizar cualquiera
	 * de los tres tipos de transporte simple: {@link TTren},{@link TCamion} o {@link TBarco},
	 * pero solo aquellos que son de tipo {@link TBarco} tienen un 15% de descuento en el coste.
	 * En cuanto a nivel interno su tipoPack de descuento es {1,0,1}, para poder realizar los 
	 * costes oportunos segun el tipo(posicion 0 del array es el transporte por barco,la 1 por tren
	 * y la 2 por camion).
	 * @param tipoTrayecto Codigo que adopta el tipo de medio de transporte que se va a hacer para
	 * ese viaje de origen-destino. (0 del array es el transporte por barco,1 por tren
	 * y 2 por camion). Sino se corresponde con alguno de estos tres numeros lanzará excepción.
	 * @param muelleOrigen El muelle de origen
	 * @param puertoOrigen El puerto de origen
	 * @param fechaIni La fecha de inicio de trayecto (Formato: aaaa-mm-dd)
	 * @param muelleDestino El muelle de destino
	 * @param puertoDestino El puerto de destino
	 * @param fechaFinFecha La fecha de fin de trayecto (Formato: aaaa-mm-dd)	
	 * @throws IllegalArgumentException Si @param tipoTrayecto !=(0||1||2)
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
	public PackCamionBarco(int tipoTrayecto,Muelle muelleOrigen,Puerto puertoOrigen,String fechaIni,Muelle muelleDestino,Puerto puertoDestino,String fechaFin) {
		super(muelleOrigen, puertoOrigen, fechaIni, muelleDestino,puertoDestino,fechaFin);
		if(tipoTrayecto!=2 && tipoTrayecto!=1 && tipoTrayecto!=0) throw new IllegalArgumentException("El tipo trayecto no es ni 0, ni 1,ni 2, es decir ni barco ni tren ni camion");
		this.tipoTrayecto=tipoTrayecto;
		tipoPack= new int []{1,0,1};
		codigoSimple=tipoTrayecto;
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
	 * @see <a href="https://github.com/kostaskougios/cloning">Cloning Library</a>
	 */
	@Override
	public int[] getTipoPack() {
		Cloner cloner=new Cloner();
		return cloner.deepClone(tipoPack);
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
		if(tipoTrayecto==2) {
			Trayecto trayectoCamion=new TCamion(super.getMuelleOrigen(), super.getPuertoOrigen(), super.getFechaIni().toString(), super.getMuelleDestino(), super.getPuertoDestino(),super.getFechaFin().toString());
			return trayectoCamion.costeTrayecto();
		}
		else if (tipoTrayecto==0){
			Trayecto trayectoBarco=new TBarco(super.getMuelleOrigen(), super.getPuertoOrigen(), super.getFechaIni().toString(), super.getMuelleDestino(), super.getPuertoDestino(),super.getFechaFin().toString());
			return trayectoBarco.costeTrayecto()*DESCUENTO_BARCO;
		}
		else {
			Trayecto trayectoTren=new TTren(super.getMuelleOrigen(), super.getPuertoOrigen(), super.getFechaIni().toString(), super.getMuelleDestino(), super.getPuertoDestino(),super.getFechaFin().toString());
			return trayectoTren.costeTrayecto();
		}		
	}
}
