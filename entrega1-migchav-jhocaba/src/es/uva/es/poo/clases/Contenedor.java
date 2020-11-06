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
	private double volumen; 	//Por defecto recibimos metros cúbicos
	private boolean estado;		//Transito = False -- Recogida = True
	private boolean techo;
	
	/**
	 * Inicializacion sin argumentos
	 */
	
	public Contenedor() {
		
	}
	
	/**
	 * Inicialización a partir de argumentos
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
	 * Cambiar el estado de un contenedor para reflejar que está en recogida
	 */
	public void SetRecogida() {
		this.estado = true;
	}

	/**
	 * Cambiar el estado de un contenedor para reflejar que está en tránsito
	 */
	public void SetTransito() {
		this.estado = false;
	}
	
	/**
	 * Cambiar a contenedor tiene techo
	 */
	
	public void SetTecho() {
		this.techo = true;
	}
	
	/**
	 * Cambiar a contenedor no tiene techo
	 */
	public void SetNoTecho() {
		this.techo = false;
	}
	
	/**
	 * Obtener el volumen del contenedor en metros cúbicos
	 * @return volumen en metros cuadrados
	 */
	public double GetVolumenMetros() {
		return this.volumen;
	}
	
	/**
	 * Almacenar el volumen en metros cúbicos
	 * @param volumen
	 */
	public void SetVolumenMetros(double volumen) {
		this.volumen = volumen;
	}
	
	/**
	 * Obtener el volumen del contenedor en pies cúbicos
	 * @return volumen en pies cúbicos
	 */
	public double GetVolumenPies() {
		double piescubicos = GetVolumenMetros() * (353147/10000);
		return piescubicos;
	}
	
	/**
	 * Cambiar el volumen a partir del valor del volumen en pies cubicos
	 * @param volumen
	 */
	public void SetVolumenPies(double volumen) {
		double nuevoVolumen = volumen * (10000/353147);
		SetVolumenMetros(nuevoVolumen);
	}
	
	/**
	 * Obtener el peso del contenedor en Kilogramos
	 * @return peso en Kilogramos
	 */
	public double GetPesoKilo() {
		return this.peso;
	}
	 
	/**
	 * Almacenar el peso en kilos
	 */
	public void SetPesoKilo(double peso) {
		this.peso = peso;
	}
	
	/**
	 * Obtener el peso en libras
	 * @return peso en libras
	 */
	public double GetPesoLibra() {
		double libras = GetPesoKilo() * (110231/50000);
		return libras;
	}
	
	/**
	 * Cambiar el peso a partir del valor del peso en libras
	 * @param peso 
	 */
	public void SetPesoLibra(double peso) {
		double nuevoPeso = GetPesoKilo() * (50000/110231);
		SetPesoKilo(nuevoPeso);
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
	
	
}
