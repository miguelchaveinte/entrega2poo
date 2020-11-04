package es.uva.es.poo.trayecto;

import es.uva.inf.poo.maps.GPSCoordinate;

/**
 * Clase en la que se almacena la información relativa a los viajes realizados 
 * por cada contenedor
 * @author Jhon
 *
 */

public class Trayecto {	
	
	private String muelleOrigen;
	private String puertoOrigen;
	private int fechaIni;
	private String muelleDestino;
	private String puertoDestino;
	private int fechaFin;
	
	
	/**
	 * Inicialización sin argumentos
	 */
	public Trayecto() {
		
	}
	
	/**
	 * Inicialización a partir de los arguementos
	 * Almacena todas las instancias necesarias
	 * @param muelleOrigen
	 * @param puertoOrigen
	 * @param fechaIni
	 * @param muelleDestino
	 * @param puertoDestino
	 * @param fechaFin
	 */
	public Trayecto(String muelleOrigen,String puertoOrigen,int fechaIni,String muelleDestino,String puertoDestino,int fechaFin) {
		//USO DE THIS: Distinguir el atributo del argumento formal
		this.muelleOrigen = muelleOrigen; 
		this.puertoOrigen = puertoOrigen;
		this.fechaIni = fechaIni ;
		this.puertoDestino = puertoDestino;
		this.puertoDestino = puertoDestino;
		this.fechaFin = fechaFin;
	}
	
	/**
	 * Conocer si la fecha de fin es mayor a una dada
	 *  
	 * @param fecha
	 * @throws IllegalArgumentException en el caso de que no se reciba ninguna fecha 
	 * o se reciba una fecha incorrecta 
	 * 
	 * @return resultado 
	 */
	public boolean Fechacorrecta(int fecha) {
		//TODO: reconocer que una fecha sea incorrecta
		if (fecha == 0) {
			throw new IllegalArgumentException("Fecha nula o fecha incorrecta");
		}
		boolean resultado = false;
		if (fechaFin > fecha) {
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
	
	public double GetDistancia(GPSCoordinate destino) {
		//Implementar bien la coordenada origen
		if (destino == null) {
			throw new IllegalArgumentException("La coordenada no puede ser nula");
		}
		
		double distancia;	
		GPSCoordinate coordenadaOrigen = new GPSCoordinate(40,40); //Grados decimales
		distancia = coordenadaOrigen.getDistanceTo(destino);
		return distancia;
	}

}











