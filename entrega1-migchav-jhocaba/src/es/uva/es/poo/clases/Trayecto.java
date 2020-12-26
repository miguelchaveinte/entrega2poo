package es.uva.es.poo.clases;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter; 

import es.uva.inf.poo.maps.GPSCoordinate;


/**
 * Clase en la que se almacena la información relativa a los viajes realizados 
 * por cada contenedor
 * @author jhocaba
 * @author migchav
 *
 */

public abstract class Trayecto {	
	
	private Muelle muelleOrigen;
	private Puerto puertoOrigen;
	private LocalDate fechaIni;
	private Muelle muelleDestino;
	private Puerto puertoDestino;
	private LocalDate fechaFin;
	private Puerto puertoFinal;
	

	
	/**
	 * Inicialización a partir de los argumentos, almacenando todas las instancias necesarias.
	 * @param muelleOrigen - Muelle de origen
	 * @param puertoOrigen - Puerto de origen
	 * @param fechaIni - Fecha de inicio de trayecto (Formato: aaaa-mm-dd)
	 * @param muelleDestino - Muelle de destino
	 * @param puertoDestino - Puerto de destino
	 * @param fechaFinFecha de fin de trayecto (Formato: aaaa-mm-dd)	
	 * @throws IllegalArgumentException-si muelleOrigen==null
	 * @throws IllegalArgumentException-si muelleDestino==null
	 * @throws IllegalArgumentException-si puertoOrigen==null
	 * @throws IllegalArgumentException-si puertoDestino==null
	 */
	public Trayecto(Muelle muelleOrigen,Puerto puertoOrigen,String fechaIni,Muelle muelleDestino,Puerto puertoDestino,String fechaFin) {
		if(muelleOrigen==null) throw new IllegalArgumentException("El muelle no debe ser  nulo ");
        if(muelleDestino==null) throw new IllegalArgumentException("El muelle no debe ser  nulo ");
        if(puertoOrigen==null) throw new IllegalArgumentException("El puerto no debe ser  nulo ");
        if(puertoDestino==null) throw new IllegalArgumentException("El puerto no debe ser  nulo ");
		this.muelleOrigen = muelleOrigen; 
		this.puertoOrigen = puertoOrigen;
		this.fechaIni = LocalDate.parse(fechaIni);
		this.muelleDestino=muelleDestino;
		this.puertoDestino = puertoDestino;
		this.fechaFin = LocalDate.parse(fechaFin);
	}
	/**
	 * Guarda el puerto destino del trayecto
	 * @param destino - Puerto destino del trayecto
	 * @throws IllegalArgumentException en el caso de que el puerto final de destino sea nulo
	 * @return el nombre del puerto destino final
	 */
	public void setPuertoFinal(Puerto destino) {
		if(destino==null)  throw new IllegalArgumentException("Puerto destino final de trayecto no puede ser null");
		puertoFinal=destino;
	}
	/**
	 * Obtener el puerto final de un trayecto
	 * @return El nombre del puerto 
	 */
	public Puerto getPuertoFinal() {
		return puertoFinal;
	}
	
	/**
	 * Consulta dato Muelle de origen
	 * @return el nombre del muelle origen
	 */
	public Muelle getMuelleOrigen() {
		return muelleOrigen;
	}
	/**
	 * Consulta dato Puerto de origen
	 * @return el nombre del puerte origen
	 */
	public Puerto getPuertoOrigen() {
		return puertoOrigen;
	}
	
	/**
	 * Consulta dato Muelle de destino
	 * @return el nombre del muelle origen
	 */
	public Muelle getMuelleDestino() {
		return muelleDestino;
	}
	/**
	 * Consulta dato Puerto de Destino
	 * @return el nombre del puerto destino
	 */
	public Puerto getPuertoDestino() {
		return puertoDestino;
	}
	/**
	 * Consulta dato de la fecha inicio
	 * @return el valor de la fecha inicio del trayecto
	 */
	public LocalDate getFechaIni() {
		return fechaIni;
	}
	/**
	 * Consulta dato de la fecha fin
	 * @return el valor de la fecha final del trayecto
	 */
	public LocalDate getFechaFin() {
		return fechaFin;
	}

	
	/**
	 * Conocer si la fecha de fin de trayecto es superior a una dada
	 * Si es superior, la funcion devolverá true
	 * @param fecha - Fecha 
	 * @throws DateTimeParseException - Cualquier excepcion será lanzada por la clase LocalDate 
	 * o fechas incorrectas debido a un año bisiesto. 
	 * @return resultado 
	 */
	public boolean fechaCorrecta(LocalDate fecha) {
		if (fecha==null)
			throw new IllegalArgumentException("La fecha no puede ser nula");
		boolean resultado = false;
		
		if (this.fechaFin.isAfter(fecha)) {
			resultado = true;
		}
		
		return resultado;
	}
	
	
	/**
	 * Calcular distancia de un viaje en millas marinas a partir del metodo getDistanceTo de la clase GPSCoordinate
	 * @throws IllegalArgumentException - Cualquier excepcion sera lanzada por la clase GPSCoordinate
	 * @return distancia en km entre coordenadas
	 */
	
	public double getDistancia() {
		double distancia;	
		GPSCoordinate coordenadaOrigen = getMuelleOrigen().getCoordenada();
		GPSCoordinate coordenadaDestino=getMuelleDestino().getCoordenada();
		distancia = coordenadaOrigen.getDistanceTo(coordenadaDestino);
		return (distancia/1.852);//para pasarlo a millas marinas
	}
	/**
	 * Agrupa en una cadena la informacion relativa a la localidad, pais y fecha tanto
	 * del origen como del destino. 
	 * Uso del metodo "format" de la clase DateTimeFormatter para formatear las fechas de acuerdo con la configuración
	 * establecida (dd/MM/YYYY)
	 * @return cadena que agrupa de forma legible dicha informacion
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
	
	//TODO:ACTUALIZAR JAVADOCS METODOS ABSTRACTOS DONDE???!!!!!!!
	/**
	 * Calcula el coste en euros para un determinado trayecto. 
	 * El coste viene dado por el precio de la milla y precio por dia del trayecto, ambos deben ser >0, 
	 * y la distancia entre los puntos origen y destino.
	 * @param precioMilla - Coste en euros de 1 unidad de distancia marina.
	 * @param precioDia - Coste en euros de 1 dia de trayecto.
	 * @throws IllegalArgumentException si se reciben precios negativos
	 * @return el coste en euros total del trayecto.
	 */

	public abstract double costeTrayecto();
	public abstract int [] getTipoPack();
	public abstract int getCodigoSimple();
	public abstract String getInicioFech();
	public abstract String getFinFech();
}