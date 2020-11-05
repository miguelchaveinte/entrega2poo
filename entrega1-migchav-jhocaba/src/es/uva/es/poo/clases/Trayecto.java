package es.uva.es.poo.clases;

import es.uva.inf.poo.maps.GPSCoordinate;

/**
 * Clase en la que se almacena la información relativa a los viajes realizados 
 * por cada contenedor
 * @author Jhon
 * @author migchav
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
		//TODO: SET ?? 
		this.muelleOrigen = muelleOrigen; 
		this.puertoOrigen = puertoOrigen;
		this.fechaIni = fechaIni ;
		this.muelleDestino=muelleDestino;
		this.puertoDestino = puertoDestino;
		this.fechaFin = fechaFin;
	}
	/**
	 * Consulta dato Muelle de origen
	 * @return el nombre del muelle origen
	 */
	public String getMuelleOrigen() {
		return muelleOrigen;
	}
	/**
	 * Consulta dato Puerto de origen
	 * @return el nombre del puerte origen
	 */
	public String getPuertoOrigen() {
		return puertoOrigen;
	}
	
	/**
	 * Consulta dato Muelle de destino
	 * @return el nombre del muelle origen
	 */
	public String getMuelleDestino() {
		return muelleDestino;
	}
	/**
	 * Consulta dato Puerto de Destino
	 * @return el nombre del puerto destino
	 */
	public String getPuertoDestino() {
		return puertoDestino;
	}
	/**
	 * Consulta dato de la fecha inicio
	 * @return el valor de la fecha inicio del trayecto
	 */
	public int getFechaIni() {
		return fechaIni;
	}
	/**
	 * Consulta dato de la fecha fin
	 * @return el valor de la fecha final del trayecto
	 */
	public int getFechaFin() {
		return fechaFin;
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
		//TODO: Convertir fechas a numeros
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
		if (destino == null) {	//no hace falta no? ya en getDistanceTo
			throw new IllegalArgumentException("La coordenada no puede ser nula");
		}
		
		double distancia;	
		GPSCoordinate coordenadaOrigen = new GPSCoordinate(40,40); //Grados decimales
		distancia = coordenadaOrigen.getDistanceTo(destino);
		return distancia;
	}
	/**
	 * Agrupa en una cadena la información relativa a la localidad,pais y fecha tanto
	 * del origen como del destino
	 * @return cadena que agrupa de forma legible dicha informacion
	 */
	public String infoTrayecto() {
		//localidad y pais en clase puerto
		return("La locadidad del puerto origen es:"+getLocalidad()+" en el pais de: "+ getPais()+
				" y la localidad del puerto destino es "+ getLocalidad()+ " que esta en el pais: "
				+ getPais()+ ". La fecha de inicio del trayecto es:"+ getFechaIni()+" y la fecha "
				+ "del fin de trayecto es: "+getFechaFin());
	}
	
	/**
	 * Calcula el coste en euros para un determinado trayecto. El coste viene dado
	 * por el precio de la milla y precio por dia del trayecto que es dado 
	 * y la distancia entre los puntos origen y destino.
	 * @param precioMilla - Coste en euros de 1 unidad de distancia marina.
	 * @param precioDia - Coste en euros de 1 dia de trayecto.
	 * @return el coste en euros total del trayecto.
	 */
	public int costeTrayecto(int precioMilla,int precioDia) {
		//TODO: precio negativo y origen,destino???????
		GPSCoordinate destino = new GPSCoordinate(40,40); //???????????????
		return precioMilla*origen.GetDistancia(destino)*(getFechaFin()-getFechaIni())*precioDia;
	}
}











