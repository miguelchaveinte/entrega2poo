package es.uva.es.poo.clases;

import es.uva.inf.poo.maps.GPSCoordinate;

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
	private int[] tipoPack ;
	private int codigoSimple;
	
	/**
	 * Crea un Trayecto Combinado de tipo PackCamionBarco, es decir,puede utilizar cualquiera
	 * de los tres tipos de transporte simple: {@link TTren},{@link TCamion} o {@link TBarco},
	 * pero solo aquellos que son de tipo {@link TCamion} y {@link TTren} 
	 * su nuevo calculo del precio es de 10 euros por kilometros recorrido en el trayecto,sin costes fijos.
	 * En cuanto a nivel interno su tipoPack de descuento es {0,1,1}, para poder realizar los 
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
	public PackCamionTren(int tipoTrayecto,Muelle muelleOrigen,Puerto puertoOrigen,String fechaIni,Muelle muelleDestino,Puerto puertoDestino,String fechaFin) {
		super(muelleOrigen, puertoOrigen, fechaIni, muelleDestino,puertoDestino,fechaFin);
		if(tipoTrayecto!=2 && tipoTrayecto!=1 &&tipoTrayecto!=0) 
			throw new IllegalArgumentException("El tipo trayecto no es ni 0, ni 1,ni 2, es decir ni barco ni tren ni camion");
		tipoPack= new int []{0,1,1};
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
	public int [] getTipoPack() {
		Cloner cloner=new Cloner();
		return cloner.deepClone(tipoPack);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public double costeTrayecto() {
			GPSCoordinate coordenadaOrigen = getMuelleOrigen().getCoordenada();
			GPSCoordinate coordenadaDestino=getMuelleDestino().getCoordenada();
			return coordenadaOrigen.getDistanceTo(coordenadaDestino)* PRECIO_KM;			
	}
}
