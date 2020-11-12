package es.uva.es.poo.clases;
import es.uva.inf.poo.maps.GPSCoordinate;


/**
 * Clase en la que se almacena la información relativa a los viajes realizados 
 * por cada contenedor
 * @author jhocaba
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
	 * Inicialización sin argumentos(tiene sentido sin argumentos????????)
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
	public Trayecto(String muelleOrigen,String puertoOrigen,int fechaIni,String muelleDestino,String puertoDestino,int fechaFin,Contenedor contenedor) {
		//USO DE THIS: Distinguir el atributo del argumento formal
		//TODO: SET ?? 
		//TODO: DAR MUELLO ORIGEN ,PUERTO ORIGEN...
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
	 * Conocer si la fecha de fin es superior a una dada
	 * Si es mayor, la funcion devolverá true
	 *  
	 * @param fecha 
	 * @throws Cualquier excepcion es lanzada por la clase LocalDate, asi como fechas nulas 
	 * o fechas incorrectas debido a un año bisiesto.
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
	
	public double getDistancia(GPSCoordinate destino) {
		//Implementar bien la coordenada origen
		//TODO:COORDENADAS????????????????
		if (destino == null) {	//no hace falta no? ya en getDistanceTo
			throw new IllegalArgumentException("La coordenada no puede ser nula");
		}
		
		double distancia;	
		GPSCoordinate coordenadaOrigen = new GPSCoordinate(40,40); //Grados decimales
		distancia = coordenadaOrigen.getDistanceTo(destino);
		return (distancia/1.852);//para pasarlo a millas marinas
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
		//Uso de ChronoUnit.DAYS para obtener el numero de dias entre las fechas indicadas
		return (precioMilla*origen.getDistancia(destino)*(ChronoUnit.DAYS.between(getFechaFin(), getFechaIni()))*precioDia);
	}
}











