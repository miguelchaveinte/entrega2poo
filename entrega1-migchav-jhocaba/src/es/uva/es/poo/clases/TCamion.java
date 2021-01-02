package es.uva.es.poo.clases;

import java.time.LocalDate;
import com.rits.cloning.Cloner;
import es.uva.inf.poo.maps.GPSCoordinate;

/**
 * Clase que implementa la creación de un {@link Trayecto} {@link Simple} que utiliza como
 * medio de transporte el Camion para transportar el contenedor. Su diferencia en cuanto al
 * coste de trayecto es que este viene dado que es de 20 euros fijos más 12,5 euros por cada 
 * kilometro recorrido para transportarlo.
 * @see Trayecto
 * @see Simple
 * @author migchav,jhocaba
 */

public class TCamion extends Simple{
	static final int COSTE_FIJO=200;
	static final double COSTE_KM=4.5;
	private int [] tipoPack;
	private int codigoSimple;
	
	/**
	 * Crea un Trayecto Simple de tipo TCamion, es decir,utiliza el camion como
	 * medio de transporte para transportar el contenedor.
	 * En cuanto a nivel interno de codificación su codigo simple es 1 y su tipoPack de descuento
	 * es {0,0,0}, lo que quiere decir que una vez realizado un trayecto de este tipo no se aplicará
	 * un descuento en los trayectos posteriores.
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
	 * @see Simple
	 */
	
 	public TCamion(Muelle muelleOrigen,Puerto puertoOrigen,String fechaIni,Muelle muelleDestino,Puerto puertoDestino,String fechaFin) {
		super(muelleOrigen, puertoOrigen, fechaIni, muelleDestino,puertoDestino,fechaFin);
		tipoPack= new int []{0,0,0};
		codigoSimple=2;

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
	 */
	@Override
	public double costeTrayecto() {
		GPSCoordinate coordenadaOrigen = getMuelleOrigen().getCoordenada();
		GPSCoordinate coordenadaDestino=getMuelleDestino().getCoordenada();
		return COSTE_FIJO+COSTE_KM*coordenadaOrigen.getDistanceTo(coordenadaDestino);
	}
	
}
