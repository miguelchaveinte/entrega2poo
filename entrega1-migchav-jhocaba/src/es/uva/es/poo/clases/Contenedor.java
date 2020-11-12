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
	
	private String identificador;
	private String codigo;
	private char equipamiento;
	private int serie;
	private int code;
	
	private double peso;
	private double carga;			//Por defecto recibimos Kilogramos
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
	public Contenedor(String identificador,double peso,double carga,double volumen) {	
		//TODO: llamar a lo de carga,code,estado,techos............
		this.identificador=identificador;
		for (int i= 0; i<3; i++) {
			this.codigo = this.codigo + identificador.charAt(i);
		}
		equipamiento = identificador.charAt(3);
		
		for(int i=4; i< identificador.length() - 1; i++) {
			this.serie = this.serie + identificador.length();
		}
		code = identificador.charAt(identificador.length()- 1);
		this.peso=peso;
		this.carga=carga;
		this.volumen=volumen;
		//TODO: transito o en recogida,techo?????
	}
	
	public String getIdentificador(Contenedor contenedor) {
		return contenedor.identificador;
	}

	
	/**
	 * Cambiar el estado de un contenedor para reflejar que está en recogida
	 */
	public void setRecogida() {
		estado = true;
	}

	/**
	 * Cambiar el estado de un contenedor para reflejar que está en tránsito
	 */
	public void setTransito() {
		estado = false;
	}
	
	/**
	 * Cambiar a contenedor tiene techo
	 */
	
	public void setTecho() {
		techo = true;
	}
	
	/**
	 * Cambiar a contenedor no tiene techo
	 */
	public void setNoTecho() {
		techo = false;
	}
	
	public boolean getTecho(Contenedor contenedor) {
		return contenedor.techo;
	}
	
	/**
	 * Obtener el volumen del contenedor en metros cúbicos
	 * @return volumen en metros cuadrados
	 */
	public double getVolumenMetros() {
		return volumen;
	}
	
	/**
	 * Almacenar el volumen en metros cúbicos
	 * @param volumen
	 */
	public void setVolumenMetros(double volumen) {
		this.volumen = volumen;
	}
	
	/**
	 * Obtener el volumen del contenedor en pies cúbicos
	 * @return volumen en pies cúbicos
	 */
	public double getVolumenPies() {
		double piescubicos = getVolumenMetros() * (353147/10000);
		return piescubicos;
	}
	
	/**
	 * Cambiar el volumen a partir del valor del volumen en pies cubicos
	 * @param volumen
	 */
	/**
	 * 
	public void setVolumenPies(double volumen) {
		double nuevoVolumen = volumen * (10000/353147);
		setVolumenMetros(nuevoVolumen);
	}
	*/
	
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
	public void setPesoLibra(double peso) {
		double nuevoPeso = getPesoKilo() * (50000/110231);
		setPesoKilo(nuevoPeso);
	}
	*/
	
	/**
	 * Obtener el precio del transporte total de un contenedor a partir de 
	 * sus trayectos
	 */
	public float Precio() {
		//TODO implementar correctamente
		//TODO:PRECIO MILLA Y PRECIO DIA
		Trayecto aux = new Trayecto();
		float precio = aux.costeTrayecto(precioMilla, precioDia);

		return precio;
	}



	
	
}
