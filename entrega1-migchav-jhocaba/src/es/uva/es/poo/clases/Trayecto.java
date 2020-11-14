package es.uva.es.poo.clases;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter; 
import java.time.Period;
import java.time.temporal.ChronoUnit;

import es.uva.inf.poo.maps.GPSCoordinate;


/**
 * Clase en la que se almacena la informaci�n relativa a los viajes realizados 
 * por cada contenedor
 * @author jhocaba
 * @author migchav
 *
 */

public class Trayecto {	
	
	private Muelle muelleOrigen;
	private Puerto puertoOrigen;
	private LocalDate fechaIni;
	private Muelle muelleDestino;
	private Puerto puertoDestino;
	private LocalDate fechaFin;
	
	
	/**
	 * Inicializaci�n sin argumentos(tiene sentido sin argumentos????????)
	public Trayecto() {
		
	}
	
	/**
	 * Inicializaci�n a partir de los arguementos
	 * Almacena todas las instancias necesarias
	 * @param muelleOrigen
	 * @param puertoOrigen
	 * @param fechaIni en formato aaaa-mm-dd
	 * @param muelleDestino
	 * @param puertoDestino
	 * @param fechaFin en formato aaaa-mm-dd
	 */
	public Trayecto(Muelle muelleOrigen,Puerto puertoOrigen,String fechaIni,Muelle muelleDestino,Puerto puertoDestino,String fechaFin) {
		//USO DE THIS: Distinguir el atributo del argumento formal
		//TODO: SET ?? 
		//TODO: DAR MUELLO ORIGEN ,PUERTO ORIGEN... contenedor a mover!!!!!!!!!!!!!
		this.muelleOrigen = muelleOrigen; 
		this.puertoOrigen = puertoOrigen;
		this.fechaIni = LocalDate.parse(fechaIni);
		this.muelleDestino=muelleDestino;
		this.puertoDestino = puertoDestino;
		this.fechaFin = LocalDate.parse(fechaFin);
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
	 * Conocer si la fecha de fin es superior a una dada
	 * Si es mayor, la funcion devolver� true
	 *  
	 * @param fecha 
	 * @throws Cualquier excepcion es lanzada por la clase LocalDate, asi como fechas nulas 
	 * o fechas incorrectas debido a un a�o bisiesto.
	 * 
	 * @return resultado 
	 */
	public boolean fechaCorrecta(LocalDate fecha) {
		boolean resultado = false;
		
		if (this.fechaFin.isAfter(fecha)) {
			resultado = true;
		}
		
		return resultado;
	}
	
	
	/**
	 * Calcula la distancia de un viaje en millas marinas
	 * 
	 * @throws IllegalArgumentException si recibe una coordenada nula
	 * @return distancia en km entre coordenadas
	 */
	
	public double getDistancia() {
		//Implementar bien la coordenada origen
		//TODO:COORDENADAS????????????????
		//if (destino == null) {	//no hace falta no? ya en getDistanceTo
		//	throw new IllegalArgumentException("La coordenada no puede ser nula");
		//}
		
		double distancia;	
		GPSCoordinate coordenadaOrigen = getMuelleOrigen().getCoordenada();
		GPSCoordinate coordenadaDestino=getMuelleDestino().getCoordenada();
		distancia = coordenadaOrigen.getDistanceTo(coordenadaDestino);
		return (distancia/1.852);//para pasarlo a millas marinas
	}
	/**
	 * Agrupa en una cadena la informaci�n relativa a la localidad,pais y fecha tanto
	 * del origen como del destino
	 * @return cadena que agrupa de forma legible dicha informacion
	 */
	public String infoTrayecto() {
		//localidad y pais en clase puerto

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY"); 
		String inicio=(formatter.format(getFechaIni())); 
        String fin=(formatter.format(getFechaFin())); 
		return("La locadidad del puerto origen es:"+getPuertoOrigen().getLocalidad()+" en el pais de: "+ getPuertoOrigen().getPais()+
				" y la localidad del puerto destino es "+ getPuertoDestino().getLocalidad()+ " que esta en el pais: "
				+ getPuertoDestino().getPais()+ ". La fecha de inicio del trayecto es:"+ inicio+" y la fecha "
				+ "del fin de trayecto es: "+fin);
	}
	
	/**
	 * Calcula el coste en euros para un determinado trayecto. El coste viene dado
	 * por el precio de la milla y precio por dia del trayecto que es dado 
	 * y la distancia entre los puntos origen y destino.
	 * @param precioMilla - Coste en euros de 1 unidad de distancia marina.
	 * @param precioDia - Coste en euros de 1 dia de trayecto.
	 * @return el coste en euros total del trayecto.
	 */
	public double costeTrayecto(int precioMilla,int precioDia) {
		//TODO: precio negativo y origen,destino???????
		GPSCoordinate coordenadaOrigen = getMuelleOrigen().getCoordenada();
		GPSCoordinate coordenadaDestino=getMuelleDestino().getCoordenada();
		//Uso de ChronoUnit.DAYS para obtener el numero de dias entre las fechas indicadas
		return (double) (precioMilla*coordenadaOrigen.getDistanceTo(coordenadaDestino)*((int)ChronoUnit.DAYS.between(getFechaFin(), getFechaIni()))*precioDia);
	}
}