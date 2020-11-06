package es.uva.es.poo.clases;
import es.uva.inf.poo.maps.GPSCoordinate;

/**
 * Clase relativa a los contenedores
 * 
 * @author jhocaba
 * @author migchav
 *
 */

public class Contenedor {
	//Atributos
	
	private String codigo;
	private char equipamiento;
	private int serie;
	private int code;
	
	private double peso;
	private int carga;			//Por defecto recibimos Kilogramos
	private double volumen; 	//Por defecto recibimos metros c�bicos
	private boolean estado;		//Transito = False -- Recogida = True
	private boolean techo;
	
	/**
	 * Inicializacion sin argumentos
	 */
	
	public Contenedor() {
		
	}
	
	/**
	 * Inicializaci�n a partir de argumentos
	 * @param identificador - Cadena con el que se identifica cada contenedor
	 */
	public Contenedor(String identificador) {	
		int i;
		for (i= 0; i<3; i++) {
			this.codigo = this.codigo + identificador.charAt(i);
		}
		this.equipamiento = identificador.charAt(i);
		
		for(i=4; i< identificador.length() - 1; i++) {
			this.serie = this.serie + identificador.length();
		}
		this.code = identificador.charAt(i);
	}
	
	/**
	 * Cambiar el estado de un contenedor para reflejar que est� en recogida
	 */
	public void setRecogida() {
		this.estado = true;
	}

	/**
	 * Cambiar el estado de un contenedor para reflejar que est� en tr�nsito
	 */
	public void setTransito() {
		this.estado = false;
	}
	
	/**
	 * Cambiar a contenedor tiene techo
	 */
	
	public void setTecho() {
		this.techo = true;
	}
	
	/**
	 * Cambiar a contenedor no tiene techo
	 */
	public void setNoTecho() {
		this.techo = false;
	}
	
	/**
	 * Obtener el volumen del contenedor en metros c�bicos
	 * @return volumen en metros cuadrados
	 */
	public double getVolumenMetros() {
		return this.volumen;
	}
	
	/**
	 * Almacenar el volumen en metros c�bicos
	 * @param volumen
	 */
	public void setVolumenMetros(double volumen) {
		this.volumen = volumen;
	}
	
	/**
	 * Obtener el volumen del contenedor en pies c�bicos
	 * @return volumen en pies c�bicos
	 */
	public double getVolumenPies() {
		double piescubicos = getVolumenMetros() * (353147/10000);
		return piescubicos;
	}
	
	/**
	 * Cambiar el volumen a partir del valor del volumen en pies cubicos
	 * @param volumen
	 */
	public void setVolumenPies(double volumen) {
		double nuevoVolumen = volumen * (10000/353147);
		setVolumenMetros(nuevoVolumen);
	}
	
	/**
	 * Obtener el peso del contenedor en Kilogramos
	 * @return peso en Kilogramos
	 */
	public double getPesoKilo() {
		return this.peso;
	}
	 
	/**
	 * Almacenar el peso en kilos
	 */
	public void setPesoKilo(double peso) {
		this.peso = peso;
	}
	
	/**
	 * Obtener el peso en libras
	 * @return peso en libras
	 */
	public double getPesoLibra() {
		double libras = getPesoKilo() * (110231/50000);
		return libras;
	}
	
	/**
	 * Cambiar el peso a partir del valor del peso en libras
	 * @param peso 
	 */
	public void setPesoLibra(double peso) {
		double nuevoPeso = getPesoKilo() * (50000/110231);
		setPesoKilo(nuevoPeso);
	}
	
	/**
	 * Obtener el precio del transporte total de un contenedor a partir de 
	 * sus trayectos
	 */
	public float Precio() {
		//TODO implementar correctamente
		Trayecto aux = new Trayecto();
		float precio = aux.costeTrayecto(precioMilla, precioDia);

		return precio;
	}

	public String getIndentificador(Contenedor contenedor) {
		// TODO Auto-generated method stub
	}
	
	
}
