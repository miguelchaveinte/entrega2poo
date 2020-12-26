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

public abstract class Contenedor {
	//Constantes
	static final double PESOLIBRA = 110231/50000;
	static final double PESOKILO = 50000/110231;
	static final double VOLUMENPIES = 353147/10000;
	static final double VOLUMENMETROS = 10000/353147;
	
	//Atributos
	private String identificador;
	private double peso;
	private double carga;		
	private double volumen; 	
	private boolean estado;		//Transito = False -- Recogida = True
	private boolean techo;
	private List<Trayecto> trayectos;
	private int [] packActivado;
	private Puerto destinoFinal;


	
	//TODO:ACTUALIZAR JAVADOC!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	/**
	 * Inicialización a partir de argumentos, almacenando todas las instancias necesarias.
	 * @param identificador - Cadena con el que se identifica cada contenedor
	 * @throws IllegalArgumentException en el caso de que el digito de control del identificador sea diferente del obtenido
	 * @throws IllegalArgumentException si la carga es negativa
	 */
	public Contenedor(String identificador,double peso,String unidPeso,double carga,double volumen,String uniVol,boolean techo)  {	
		if(comprobarIdentificador(identificador)) {
			this.identificador=identificador;
		}
		int codigoControlBueno=obtenerDigitoControl(identificador);

		int codigoArgumento =Character.getNumericValue(identificador.charAt(identificador.length()- 1));
		if(codigoControlBueno!=codigoArgumento)throw new IllegalArgumentException("Identificador no valido(codigo control no valido)");
		if(techo) {
			setTecho();
		}
		else setNoTecho();
		comprobarUnidadesPeso(peso,unidPeso);
		if (carga<0) throw new IllegalArgumentException("Carga no puede ser negativa");
		this.carga=carga;
		comprobarUnidadesVolumen(volumen,uniVol);
		setTransito();
		trayectos=new ArrayList<Trayecto>();
		packActivado= new int []{0,0,0};
	}

	//TODO:PONER ESTO BIEN Y JAVADOC????????????!!!!!
	public abstract int getEspacio();
	public abstract int[] getCodigoTransporte();
	
	/**
	 * Comprobar que el identificador sea correcto.
	 * Uso de StringBuilder para modificar el tamaño de las cadenas de caracteres al ir añadiendo caracteres, 
	 * y la correspondiente asignacion de los atributos en el caso de que se cumplan todas las condiciones. 
	 * @param identificador - Identificador del contenedor
	 * @throws IllegalArgumentException si el identificador no tiene la longitud correcta
	 * @throws IllegalArgumentException si alguna de las tres letras iniciales no son mayusculas, o si la cuarta letra no se corresponde 
	 * con los caracteres - 'U', 'J', 'Z' o si la longitud de la serie es distinta de 6.
	 */
	public boolean comprobarIdentificador(String identificador) {
		boolean correcto;
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
			//TODO:NO SE PUEDE INICIALIZAR DONDE ESTAMOS COMPROBANDO
			correcto=true;
		}
		else {
			throw new IllegalArgumentException("Identificador no valido");
		}
		return correcto;
	}
	
	/**
     * Obtener el digito de control del contenedor a partir del identificador.
     * Uso de mapa para guardar cada letra con su correspondiente valor, además de un vector en el que se 
     * van almacenando los respectivos valores y con los que se calculará la suma necesaria. 
     * @param identificador - Identificador del contenedor
     * @return digito de control
     */
    private int obtenerDigitoControl(String identificador) {
    	comprobarIdentificador(identificador);
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
        double resultado =(int) suma/11;
        resultado = resultado * 11;
        int codigoControl=(int)(suma - resultado);
        if(codigoControl==10) return 0;
        return codigoControl;
    }
	/**
	 * Setea el Puerto destino del trayecto global que realizará el contenedor
	 * @param puerto - puerto destino del trayecto global(conjunto)
	 */
    public void setDestinoFinal(Puerto puerto) {
    	destinoFinal=puerto;
    }
	/**
	 * Obtiene el Puerto destino del trayecto global que realiza el contenedor
	 * @return puerto - puerto destino del trayecto global(conjunto)
	 */
    public Puerto getDestinoFinal() {
    	return destinoFinal;
    }
    
    //TODO:CAMBIAR JAVADOC!!!!!!!!!!!!!!!
    /**
	 * Para almacenar el peso, utilizamos un String y el metodo split para detectar si se trata de kilogramos o de libras. 
	 * Modelo de uso correcto de la entrada peso-(3000-kg || 500-lb)
	 *
	 * @param Peso - Peso del contenedor 
	 * @return IllegalArgumentException si el {@param peso} esta vacio
	 * @return IllegalArgumentException si las unidades del {@param peso} no son Kg o lb
	 */
	public void comprobarUnidadesPeso(double peso, String unidPeso) {
		if (unidPeso == "Kg") {
    		setPesoKilo(peso);
    	}
    	else if(unidPeso == "lb") {
    		conviertePesoKilo(peso);	
    	}
    	else { throw new IllegalArgumentException("String Peso no correcto unidades");
    	}
	}
	
	/**
	 * Cambiar el peso de libras a kilogramos
	 * @param pesocontenedor - Peso del contenedor en libras
	 * @throws IllegalArgumentException en el caso de que peso sea negativo
	 */
	public void conviertePesoKilo(double pesoContenedor) {
		if(peso<0) throw new IllegalArgumentException("Peso no puede ser negativo");
		double nuevoPeso = peso * PESOKILO;
		setPesoKilo(nuevoPeso);
	}
	
	/**
	 * Guardar peso 
	 * @param peso - Peso del contenedor ya en Kilos
	 * @throws IllegalArgumentException en el caso de que peso sea negativo
	 */
	public void setPesoKilo(double peso) {
		if(peso<0) throw new IllegalArgumentException("Peso no puede ser negativo");
		this.peso = peso;
	}
	//TODO:ACTUALIAZAR JAVADOC!!!!!!!!!!!!!!!!
	/**
	 * Para almacenar el volumen, utilizamos el mismo metodo que para almacenar el peso para detectar si se trata de 
	 * metros cubicos o de pies cubicos. 
	 * Modelo de uso correcto de la entrada peso-(3000-m3 || 500-ft3)
	 * @param Volumen - Volumen del contenedor 
	 * @return IllegalArgumentException si el {@param volumen} esta vacio
	 * @return IllegalArgumentException si las unidades del {@param volumen} no son m3 o ft3
	 */
	public void comprobarUnidadesVolumen(double volumen, String unidVol) { 
		if (unidVol == "m3") {
			setVolumenMetros(volumen);
		}
		else if(unidVol == "ft3") {
			convierteVolumenMetros(volumen);	
		}
		else { throw new IllegalArgumentException("String Volumen no correcto unidades o <0");
		}
	}
	
	/**
	 * Cambiar el volumen de pies cubicos a metros cubicos
	 * @param volumenContenedor - Volumen del contenedor en pies cubicos
	 * @throws IllegalArgumentException en el caso de que el volumen sea negativo
	 */
	public void convierteVolumenMetros(double volumenContenedor) {
		if(volumen<0) throw new IllegalArgumentException("Volumen no puede ser negativo");
		double nuevoVolumen = volumen * VOLUMENMETROS;
		setVolumenMetros(nuevoVolumen);
	}
		
	/**
	 * Guardar volumen
	 * @param volumen - Volumen del contenedor en metros cúbicos
	 * @throws IllegalArgumentException en el caso de que el volumen sea negativo
	 */
	public void setVolumenMetros(double volumen) {
		if(volumen<0) throw new IllegalArgumentException("Volumen no puede ser negativo");
		this.volumen = volumen;
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
		return getVolumenMetros() * VOLUMENPIES;
	}
	

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
		return getPesoKilo() * PESOLIBRA;
	}

	
	/**
	 * Obtener el identificador del contenedor	
	 * @param contenedor - Contenedor inicializado
	 * @throws IllegalArgumentException si el contenedor esta vacio o si la plaza es negativa
	 * @return identificador del contenedor
	 */
	public String getIdentificador() {
		return identificador;
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
	 * Retorna el estado(transito o en recogida) del contenedor
	 * @return
	 */
	public boolean getEstado() {
		return estado;
	}
	
	/**
	 * Cambiar el contenedor para reflejar que tiene techo
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
	
	/**
	 * Obtener si el contenedor tiene techo o no
	 * Devuelve true si tiene y false en caso contrario
	 * @param contenedor - Contenedor inicializado
	 * @throws IllegalArgumentException si el contenedor esta vacio o si la plaza es negativa
	 * @return techo o no techo
	 */
	public boolean getTecho() {
		return techo;
	}
	
	/**
	 * Establecer el puerto destino del trayecto global
	 * @param destino - Puerto destino del trayecto
	 * @throws IllegalArgumentException-Puerto destino del trayecto global ==null
	 */
	public void hacerTrayecto(Puerto destino) 
	{
		if(destino==null) throw new IllegalArgumentException("El puerto destino no debe ser nulo");
		setDestinoFinal(destino);
	}
	
	
	//TODO:ACTUALIZAR JAVADOC!!!!!!!!!!!!!!!!!!!
	/**
	 * diferentes viajes que son reliu por cont hatsa llagr desti finsl del try glbl
	 * @param contenedor - Contenedor inicializado
	 * @param puertoOrigen - Puerto origen del trayecto
	 * @param puertoFin - Puerto del fin de trayecto
	 * @param muelleFin - Muelle del fin de trayecto
	 * @param fechaInicio - Fecha de inicio del trayecto
	 * @param fechaFin - Fecha de fin de trayecto
	 * @throws IllegalArgumentException en el caso de que el contenedor no se encuentre en el puerto
	 * @throws IllegalArgumentException si coinciden el puerto destino coincide con el puerto fin del trayecto global.
	 * Se deberia haber realizado un nuevo trayecto global
	 * 
	 */

	public void hacerViajes(Trayecto trayecto) {
		if(trayecto==null)throw new IllegalArgumentException("trayecto nulo");
		//comprobar que el primero ya tiene el puerto destino
		if(getDestinoFinal()==null)throw new IllegalArgumentException("debe inicializar el destino final global del contenedor");
		//comprobar que el puerto origen no es el destino
		if(trayecto.getPuertoOrigen().equals(getDestinoFinal()))throw new IllegalArgumentException("Ya se había llegado al puerto destino del trayecto.Inicie un nuevo trayecto global");
		//que el contenedor este en ese muelle/puerto
		//esto en un metodo private?? ->
		List<Muelle> listaMuelles=trayecto.getPuertoOrigen().getListaMuelles();
		int posicionMuelle=-1;
		int i=0;
		while(posicionMuelle==-1 && i<listaMuelles.size()) {
			Muelle analisis=listaMuelles.get(i);
			int plaza=analisis.getPlaza(this.getIdentificador());
			if(plaza!=-1) posicionMuelle=i;
			i++;
		}
		if (posicionMuelle==-1) throw new IllegalArgumentException("El contenedor no esta en ese puerto");
		
		if(trayecto instanceof Compuesto) {
			trayectos.add(trayecto);
			packActivado=trayecto.getTipoPack();
		}
		else {
			int [] codigoCamionTren=new int []{0,1,1};
			if(packActivado[trayecto.getCodigoSimple()]==1) {
				if(packActivado.equals(codigoCamionTren)) {
					PackCamionTren trayectoCasteado= new PackCamionTren(trayecto.getCodigoSimple(),trayecto.getMuelleOrigen(), trayecto.getPuertoOrigen(), trayecto.getInicioFech(), trayecto.getMuelleDestino(), trayecto.getPuertoDestino(),trayecto.getFinFech());
					trayectos.add(trayectoCasteado);
				}
				else {
					PackCamionBarco trayectoCasteado= new PackCamionBarco(trayecto.getCodigoSimple(),trayecto.getMuelleOrigen(), trayecto.getPuertoOrigen(), trayecto.getInicioFech(), trayecto.getMuelleDestino(), trayecto.getPuertoDestino(),trayecto.getFinFech());
					trayectos.add(trayectoCasteado);
				}
			}
			else {
				trayectos.add(trayecto);
				packActivado=trayecto.getTipoPack();
			}
		}
	}
	
	//TODO:QUITAR LUEGO .SOLO PARA PRUEBAS
	public List<Trayecto> getLista(){
		return trayectos;
	}
	
	//TODO:ACTUALIZAR JAVADOC???????????!!!!!!!!!!
	/**
	 * Calcular el precio total del trayecto global, es decir, la suma de costes de cada viaje.
	 * @param precioMilla - Coste en euros de 1 unidad de distancia marina. 
	 * @param precioDia - Coste en euros de 1 dia de trayecto.
	 * @throws IllegalArgumentException si el precio de milla o el precio por dia son negativos
	 * @return 
	 */
	
	public double Precio() {
		double sumaTrayectos=0.0;
		Iterator<Trayecto> itrTrayectos=trayectos.iterator();
		while(itrTrayectos.hasNext()) {
			Trayecto analisis=itrTrayectos.next();
			sumaTrayectos+=analisis.costeTrayecto();
		}
		return sumaTrayectos;	
	}
	
}

	
	
