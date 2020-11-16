package es.uva.es.poo.clases;
import es.uva.inf.poo.maps.GPSCoordinate;
import java. util. *;
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
	private List<Trayecto> trayectos;
	//private Object contenedor;
	
	/**
	 * Inicializacion sin argumentos
	 */
	
	public Contenedor() {
		
	}
	
	/**
	 * Inicialización a partir de argumentos
	 * @param identificador - Cadena con el que se identifica cada contenedor
	 * @throws Exception 
	 */
	public Contenedor(String identificador,double peso,double carga,double volumen) throws Exception {	
		//TODO: llamar a lo de carga,code,estado,techos............
		boolean correcto=Muelle.comprobarIdentificador(identificador);
		if (correcto){
			this.identificador=identificador;
			
			StringBuilder codigoString=new StringBuilder();
			for (int i= 0; i<3; i++) {
				codigoString = codigoString.append(identificador.charAt(i));
			}
			codigo=codigoString.toString();
			
			equipamiento = identificador.charAt(3);
			
			StringBuilder serieString=new StringBuilder();
			for(int i=4; i< identificador.length() - 1; i++) {
				serieString = serieString.append(identificador.charAt(i));
			}
			String sb=serieString.toString();
			serie=Integer.parseInt(sb);
			
			code = identificador.charAt(identificador.length()- 1);
			this.peso=peso;
			this.carga=carga;
			this.volumen=volumen;
			trayectos=new ArrayList<Trayecto>();
		}
		else
			throw new Exception("Identificador no valido");
		//this.contenedor=Contenedor.this;
		//TODO: transito o en recogida,techo?????
	}
	
	public String getIdentificador(Contenedor contenedor) {
		if (contenedor==null) {
			throw new IllegalArgumentException("El contenedor no puede ser vacio ni la plaza<0");
		}
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
		if (contenedor==null) {
			throw new IllegalArgumentException("El contenedor no puede ser vacio ni la plaza<0");
		}
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
	
	public void hacerTrayecto(Muelle muelleOrigen,Puerto puertoOrigen,String fechaIni,
			Muelle muelleDestino,Puerto puertoDestino,String fechaFin) 
	{
		trayectos.add(new Trayecto(muelleOrigen,puertoOrigen,fechaIni,
			muelleDestino, puertoDestino, fechaFin));
	}
	
	/**
	 * Obtener el precio del transporte total de un contenedor a partir de 
	 * sus trayectos
	 */
	public double Precio(int precioMilla,int precioDia) {
		double sumaTrayectos=0;
		Iterator<Trayecto> itrTrayectos=trayectos.iterator();
		while(itrTrayectos.hasNext()) {
			Trayecto analisis=itrTrayectos.next();
			double precio = analisis.costeTrayecto(precioMilla, precioDia);
			sumaTrayectos+=precio;
		}
		return sumaTrayectos;
	}
}

	
	
