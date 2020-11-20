package es.uva.es.poo.clases;
import es.uva.inf.poo.maps.GPSCoordinate;
import java. util. *;
import es.uva.es.poo.clases.*;
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
	 * @throws IllegalArgumentException
	 */
	public Contenedor(String identificador,String peso,double carga,String volumen,boolean techo)  {	
		comprobarIdentificador(identificador);
		int codigoControlBueno=obtenerDigitoControl(identificador);

		int codigoArgumento =Character.getNumericValue(identificador.charAt(identificador.length()- 1));
		if(codigoControlBueno!=codigoArgumento)throw new IllegalArgumentException("Identificador no valido(codigo control no valido)");
		code=codigoControlBueno;
		if(techo) {
			setTecho();
		}
		else setNoTecho();
		comprobarUnidadesPeso(peso);
		if (carga<0) throw new IllegalArgumentException("Carga no puede ser negativa");
		this.carga=carga;
		comprobarUnidadesVolumen(volumen);
		setTransito();
		trayectos=new ArrayList<Trayecto>();
	}
	/**
	 * 
	 * @param identificador
	 * @throws IllegalArgumentException
	 */
	public void comprobarIdentificador(String identificador) {
		if(identificador.length()!=11)throw new IllegalArgumentException("Identificador no valido");
		StringBuilder codigoString=new StringBuilder();
		for (int i= 0; i<3; i++) {
			codigoString = codigoString.append(identificador.charAt(i));
		}
		String codigo=codigoString.toString();
		char equipamiento = identificador.charAt(3);
		
		StringBuilder serieString=new StringBuilder();
		for(int i=4; i< identificador.length() - 1; i++) {
			serieString = serieString.append(identificador.charAt(i));
		}
		String serie=serieString.toString();
	
		if (codigo.equals(codigo.toUpperCase()) && (equipamiento=='U'||equipamiento=='J'||equipamiento=='Z') && serie.length()==6) {
			this.identificador=identificador;
			this.codigo=codigo;
			this.equipamiento=equipamiento;
			this.serie=Integer.parseInt(serie);
		}
		else {
			throw new IllegalArgumentException("Identificador no valido");
		}
	}
	
	/**
     * Obtener el digito de control del contenedor
     * @return digito de control
     */
    public int obtenerDigitoControl(String identificador) {
        //utilizo un mapa para guardar las letra con sus correspondientes valor
        Map<String, Integer> tabla = new HashMap<String, Integer>();
        tabla.put("A", 10);tabla.put("B", 12);tabla.put("C", 13);tabla.put("D", 14);tabla.put("E", 15);tabla.put("F", 16);tabla.put("G", 17);tabla.put("H", 18);tabla.put("I", 19);tabla.put("J", 20);tabla.put("K", 21);tabla.put("L", 23);tabla.put("M", 24);tabla.put("N", 25);tabla.put("O", 26);tabla.put("P", 27);tabla.put("Q", 28);tabla.put("R", 29);tabla.put("S", 30);tabla.put("T", 31);tabla.put("U", 32);tabla.put("V", 34);tabla.put("W", 35);tabla.put("X", 36);tabla.put("Y", 37);tabla.put("Z", 38);
        //En este vector guardo los valores de cada caracter y la serie de numeros
        int vector[] = new int[10];
        for (int i =0; i<identificador.length()-1; i++) {
            if (i < 4) {
                vector[i] = tabla.get(Character.toString(identificador.charAt(i))); //Obtengo el valor del String correspondiente y lo almaceno en el vector
            }
            else {
                vector[i] = Integer.parseInt(String.valueOf(identificador.charAt(i))); //Convierto cada caracter a entero y lo almaceno
            }
        }
        //Multiplicar cada valor por 2^pos
        double suma = 0;
        for (int i=0; i<vector.length; i++) {
            suma += vector[i] * Math.pow(2, i);
        }
        double resultado = suma/11;
        resultado = Math.round(resultado);
        resultado = resultado * 11;
        int codigoControl=(int)(suma - resultado);
        if(codigoControl==10) return 0;
        return codigoControl;
    }
	
    
    /**
	 * Para almacenar el peso, utilizamos un String y el metodo split para detectar si se tratan de Kg o de lb (Ej: 3000-kg || 500-lb)
	 * @param Peso del contenedor
	 */
	public void comprobarUnidadesPeso(String peso) { //3000-Kg 400lb
		String [] array = peso.split("-");
		double pesoContenedor = (double) Integer.parseInt(array[0]);
		if( array[1].equals("Kg")){
			setPesoKilo(pesoContenedor);
		}
		else if(array[1].equals("lb")){
			conviertePesoKilo(pesoContenedor);	
		}
		else { throw new IllegalArgumentException("String Peso no correcto unidades ");
		}	
	}
	
	/**
	 * Cambiar el peso de libras a kilogramos
	 * @param pesocontenedor
	 * @throws IllegalArgumentException
	 */
	public void conviertePesoKilo(double pesoContenedor) {
		if(pesoContenedor<0) throw new IllegalArgumentException("Peso no puede ser negativo");
		double nuevoPeso = pesoContenedor * (50000/110231);
		setPesoKilo(nuevoPeso);
	}
	
	/**
	 * Guardar peso
	 * @param peso
	 * @throws IllegalArgumentException
	 */
	public void setPesoKilo(double peso) {
		if(peso<0) throw new IllegalArgumentException("Peso no puede ser negativo");
		this.peso = peso;
	}
	
	/**
	* Para almacenar el volumen, utilizamos un String y el metodo split para detectar si se tratan de m3 o de ft3 (Ej: 30-m3 || 40-ft3)
	* @param Peso del contenedor
	* @throws IllegalArgumentException
	*/
	public void comprobarUnidadesVolumen(String volumen) { //30-m3 40ft3
		String [] array = volumen.split("-");
		double volumenContenedor = (double) Integer.parseInt(array[0]);
		if(array[1].equals("m3")){
			setVolumenMetros(volumenContenedor);
		}
		else if(array[1].equals("ft3")){
			convierteVolumenMetros(volumenContenedor);	
		}
		else { throw new IllegalArgumentException("String Volumen no correcto unidades o <0");
		}	
	}
	
	/**
	 * Cambiar el volumen de pies cúbicos a metros cúbicos
	 * @param volumencontenedor
	 */
	public void convierteVolumenMetros(double volumenContenedor) {
		if(volumenContenedor<0) throw new IllegalArgumentException("Volumen no puede ser negativo");
		double nuevoVolumen = volumenContenedor * ((10000/353147));
		setPesoKilo(nuevoVolumen);
	}
		
	/**
	 * Guardar volumen
	 * @param volumen en metros cúbicos
	 * @throws IllegalArgumentException
	 */
	public void setVolumenMetros(double volumen) {
		if(volumen<0) throw new IllegalArgumentException("Volumen no puede ser negativo");
		this.volumen = volumen;
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
	
	public void hacerTrayecto(Puerto destino) 
	{
		//TODO??: OBTENER MUELLE ORIGEN Y ESO , MIRAR TODOIST
		//estado en trayecto
		Trayecto destinoFinal=new Trayecto();
		destinoFinal.setPuertoFinal(destino);
		trayectos.add(destinoFinal);
	}
	/***
	 * 
	 * @param contenedor
	 * @param inicio
	 * @param puertoFin
	 * @param muelleFin
	 * @param fechaInicio
	 * @param fechaFin
	 * @throws IllegalArgumentException
	 */
	public void hacerViajes(Contenedor contenedor,Puerto puertoOrigen,Puerto puertoDestino,Muelle muelleDestino,String fechaInicio,String fechaFin)  {
		List<Muelle> listaMuelles=puertoOrigen.getListaMuelles();
		int posicionMuelle=-1;
		int i;
		for (i=0;i<listaMuelles.size();i++) {
			Muelle analisis=listaMuelles.get(i);
			int plaza=analisis.getPlaza(contenedor.getIdentificador(contenedor));
			if(plaza==-1) continue;
			posicionMuelle=i;
			break;
		}
		if (posicionMuelle==-1) throw new IllegalArgumentException("El contenedor no esta en ese puerto");
		Muelle muelleOrigen=listaMuelles.get(posicionMuelle);
		if(puertoOrigen.equals(trayectos.get(0).getPuertoFinal()))throw new IllegalArgumentException("Ya se había llegado al puerto destino del trayecto.Inicie un nuevo trayecto global");
		trayectos.add(new Trayecto(muelleOrigen,puertoOrigen,fechaInicio,muelleDestino,puertoDestino,fechaFin));
		if(puertoDestino.equals(trayectos.get(0).getPuertoFinal())) contenedor.setRecogida();
	}
	

	/**
	 * 
	 * @param precioMilla
	 * @param precioDia
	 * @return
	 * @throws IllegalArgumentException
	 */
	
	//TODO:NO MIRAR EL PRIMER TRAYECTO
	public double Precio(int precioMilla,int precioDia) {
		if(precioMilla<=0 || precioDia<=0)
			throw new IllegalArgumentException("Los precios no pueden ser<=0");
		double sumaTrayectos=0;
		Iterator<Trayecto> itrTrayectos=trayectos.iterator();
		while(itrTrayectos.hasNext()) {
			Trayecto analisis=itrTrayectos.next();
			if(analisis.getMuelleOrigen()==null) continue;
			double precio = analisis.costeTrayecto(precioMilla, precioDia);
			sumaTrayectos+=precio;
		}
		return sumaTrayectos;
	}
}

	
	
