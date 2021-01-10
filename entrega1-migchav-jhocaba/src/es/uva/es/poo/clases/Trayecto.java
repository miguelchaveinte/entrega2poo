package es.uva.es.poo.clases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import es.uva.inf.poo.maps.GPSCoordinate;


/**
 * Clase abstracta que almacena aquella información relevante de cada uno de los viajes 
 * que realiza un {@link Contenedor}. Esta informacion básicamente consiste en el {@link Puerto}
 * y {@link Muelle} origen y destino de ese viaje como su fecha de inicio y fin prevista. Con 
 * todos estos datos podemos calcular toda la información relativa al viaje,como la distancia
 * que recorre el contenedor.Además podemos calcular cuanto cuesta ese viaje, pero dicha 
 * implementación viene dada por los tipos de trayectos que tenemos según medio de transporte y coste/descuento:
 * {@link Simple} que agrupa a su vez a {@link TTren}, {@link TBarco} y {@link TCamion}; y por otro lado 
 * {@link Combinado} que contiene los Packs {@link PackCamionBarco} y {@link PackCamionTren} que lo que hace
 * es ofertar una combinación de los trayectos {@link Simple} a un mejor precio.
 * 
 * @see Simple
 * @see TTren
 * @see TBarco
 * @see TCamion
 * 
 * @see Combinado
 * @see PackCamionBarco
 * @see PackCamionTren
 * 
 * @see LocalDate
 * 
 * @author migchav,jhocaba
 */

public abstract class Trayecto {	
	
	static final double MILLAS_MARINAS=1.852;
	private Muelle muelleOrigen;
	private Puerto puertoOrigen;
	private LocalDate fechaIni;
	private Muelle muelleDestino;
	private Puerto puertoDestino;
	private LocalDate fechaFin;
	
	
	/**
	 * Crea un nuevo Trayecto, almacenando todas las instancias necesarias.
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
	public Trayecto(Muelle muelleOrigen,Puerto puertoOrigen,String fechaIni,Muelle muelleDestino,Puerto puertoDestino,String fechaFin) {
		if(muelleOrigen==null) 
			throw new IllegalArgumentException("El muelle no debe ser  nulo ");
        if(muelleDestino==null)
        	throw new IllegalArgumentException("El muelle no debe ser  nulo ");
        if(puertoOrigen==null) 
        	throw new IllegalArgumentException("El puerto no debe ser  nulo ");
        if(puertoDestino==null) 
        	throw new IllegalArgumentException("El puerto no debe ser  nulo ");
       
		if(!puertoOrigen.puertoContainsMuelle(muelleOrigen)) 
			throw new IllegalArgumentException("El muelle origen no esta en el puerto origen");
		
		if(!puertoDestino.puertoContainsMuelle(muelleDestino)) 
			throw new IllegalArgumentException("El muelle destino no esta en el puerto destino");
        
        this.muelleOrigen = muelleOrigen; 
		this.puertoOrigen = puertoOrigen;
		this.fechaIni = LocalDate.parse(fechaIni);
		this.muelleDestino=muelleDestino;
		this.puertoDestino = puertoDestino;
		this.fechaFin = LocalDate.parse(fechaFin);
	}

	
	/**
	 * Calcula el coste en euros para un determinado trayecto. 
	 * El coste viene dado por el tipo de trayecto que se trate.
	 * @see Simple#costeTrayecto()
	 * @see TTren#costeTrayecto()
	 * @see TBarco#costeTrayecto()
	 * @see TCamion#costeTrayecto()
	 * 
	 * @see Combinado#costeTrayecto()
	 * @see PackCamionBarco#costeTrayecto()
	 * @see PackCamionTren#costeTrayecto()
	 * @return el coste en euros total del trayecto.
	 */
	public abstract double costeTrayecto();
	

	
	/**
	 * Consulta dato Muelle de origen
	 * @return El nombre del muelle origen
	 */
	public Muelle getMuelleOrigen() {
		return muelleOrigen;
	}
	/**
	 * Consulta dato Puerto de origen
	 * @return El nombre del puerte origen
	 */
	public Puerto getPuertoOrigen() {
		return puertoOrigen;
	}
	
	/**
	 * Consulta dato Muelle de destino
	 * @return El nombre del muelle origen
	 */
	public Muelle getMuelleDestino() {
		return muelleDestino;
	}
	/**
	 * Consulta dato Puerto de Destino
	 * @return El nombre del puerto destino
	 */
	public Puerto getPuertoDestino() {
		return puertoDestino;
	}
	/**
	 * Consulta dato de la fecha inicio
	 * @return El valor de la fecha inicio del trayecto en formato LocalDate
	 * @see LocalDate
	 */
	public LocalDate getFechaIni() {
		return fechaIni;
	}
	/**
	 * Consulta dato de la fecha fin
	 * @return El valor de la fecha final del trayecto en formato LocalDate
	 * @see LocalDate
	 */
	public LocalDate getFechaFin() {
		return fechaFin;
	}

	/**
	 * Conocer si la fecha de fin de trayecto es superior a una dada
	 * Si es superior, la funcion devolverá true
	 * @param fecha La fecha para analizar si es superior a la de fin de trayecot 
	 * @throws DateTimeParseException - Cualquier excepcion será lanzada por la clase LocalDate 
	 * o fechas incorrectas debido a un año bisiesto. 
	 * @return resultado Será true si la fecha fin de trayecto es superior a @param fecha. Sino retorna false. 
	 */
	public boolean fechaCorrecta(LocalDate fecha) {
		if (fecha==null)
			throw new IllegalArgumentException("La fecha no puede ser nula");
		boolean resultado = false;
		
		if (fechaFin.isAfter(fecha)) {
			resultado = true;
		}
		
		return resultado;
	}
	
	/**
	 * Calcula la distancia de un viaje en millas marinas a partir del metodo getDistanceTo de la clase GPSCoordinate
	 * @throws IllegalArgumentException - Cualquier excepcion sera lanzada por la clase GPSCoordinate
	 * @return La distancia en millas marinas entre coordenadas
	 */
	
	public double getDistancia() {
		double distancia;	
		GPSCoordinate coordenadaOrigen = getMuelleOrigen().getCoordenada();
		GPSCoordinate coordenadaDestino=getMuelleDestino().getCoordenada();
		distancia = coordenadaOrigen.getDistanceTo(coordenadaDestino);
		return (distancia/MILLAS_MARINAS);//para pasarlo a millas marinas
	}
	
	/**
	 * Agrupa en una cadena la informacion relativa a la localidad, pais y fecha tanto
	 * del origen como del destino. 
	 * Uso del metodo "format" de la clase DateTimeFormatter para formatear las fechas de acuerdo con la configuración
	 * establecida (dd/MM/YYYY) {@link DateTimeFormatter#format(java.time.temporal.TemporalAccessor)}
	 * @return La cadena que agrupa de forma legible dicha informacion
	 */
	public String infoTrayecto() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY"); 
		String inicio=(formatter.format(getFechaIni())); 
        String fin=(formatter.format(getFechaFin())); 
		return("La locadidad del puerto origen es:"+getPuertoOrigen().getLocalidad()+" en el pais de: "+ getPuertoOrigen().getPais()+
				" y la localidad del puerto destino es "+ getPuertoDestino().getLocalidad()+ " que esta en el pais: "
				+ getPuertoDestino().getPais()+ ". La fecha de inicio del trayecto es:"+ inicio+" y la fecha "
				+ "del fin de trayecto es: "+fin);
	}
	
}