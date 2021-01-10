package es.uva.es.poo.clases;
import java. util. *;

/**
 * Clase abstracta que almacena la información relativa a los contenedores como por ejemplo sus atributos
 * identificativos,carga,volumen y sus trayectos. Partiendo de esta base podemos llegar a realizar
 * con esta clase algunas acciones como mover un contenedor de lugar({@link Contenedor#hacerTrayecto(Puerto)} 
 * y {@link Contenedor#hacerViajes(Trayecto)}),calcular los costes de los trayectos y obtener todas sus
 * caracteristicas. En esta aplicación consideramos tres grandes tipos de contenedores: 
 * {@link Estandar}, {@link Refrigerado} y {@link FlatRack} que comparten gran parte de las características
 * pero tienen diferencias en cuanto almacenamiento,transporte y características físicas del contenedor
 * 
 * @see Estandar
 * @see Refrigerado
 * @see FlatRack
 * 
 * @author migchav,jhocaba
 */

public abstract class Contenedor {
	//Constantes
	static final double PESOLIBRA = (double)110231/50000;
	static final double PESOKILO = (double)50000/110231;
	static final double VOLUMENPIES = (double)353147/10000;
	static final double VOLUMENMETROS = (double)10000/353147;
	
	//Atributos
	private String identificador;
	private double peso;
	private double carga;		
	private double volumen; 	
	private boolean estado;		//Transito = False -- Recogida = True
	private boolean techo;
	private List<Trayecto> trayectos;
	private Puerto destinoFinal;

	
	/**
	 * Crea un nuevo contenedor con un identificador, peso y su unidad,carga,volumen y su unidad,boolean de si tiene techo, distinguido.
	 * Además de estos parametros el contenedor se inicializada a estado de transito hasta que se ejecute un trayecto global y sus 
	 * diferentes viajes lleguen hasta el puerto destinoFinal; y el packActivado({@link Trayecto#getTipoPack()}) para los descuentos en los trayectos por haber 
	 * contratado un {@link PackCamionBarco} o un {@link PackCamionTren} se setea a un array de codigo binario 0 que no ejecuta descuento.
	 * @param identificador La cadena con el que se identifica cada contenedor.(Caracterizada por el codigo del dueño (3 letras mayusculas),
	 * una letra(U,J o Z), que indica el equipamiento,un número de serie de 6 dígitos, un dígito de control obtenido de un algoritmo.
	 * @param peso El numero de peso de la tara
	 * @param unidPeso La unidad del @param peso que puede ser Kg(La k en mayusculas - kilogramos) o lb (libras)
	 * @param carga La máxima carga útil permitida
	 * @param volumen El volumen del contenedor
	 * @param unidVol La unidad del @param volumen que puede ser m3(metros cubicos) o ft3(pies cubicos)
	 * @param techo El boolean que indica si tiene o no techo (true para decir que SI tiene techo .Por el contrario false).
	 * @throws IllegalArgumentException Si el digito de control del identificador sea diferente del obtenido
	 * @throws IllegalArgumentException Si la carga<0
	 * @throws IllegalArgumentException Si el @param identificador no cumple los requisitos expuestos
	 * @throws IllegalArgumentException Si el peso<0
	 * @throws IllegalArgumentException Si el volumen<0
	 * @throws IllegalArgumentException Si la unidad de peso no son las expuestas
	 * @throws IllegalArgumentException Si la unidad de volumen no son las expuestas
	 * @see Contenedor#comprobarIdentificador(String)
	 * @see Contenedor#obtenerDigitoControl(String)
	 * @see Contenedor#comprobarUnidadesPeso(double, String)
	 * @see Contenedor#comprobarUnidadesVolumen(double, String)
	 * @see Contenedor#setTransito()
	 * @see Contenedor#setTecho()
	 * @see Contenedor#setNoTecho()
	 */
	public Contenedor(String identificador,double peso,String unidPeso,double carga,double volumen,String uniVol,boolean techo)  {	
		comprobarIdentificador(identificador);
		this.identificador=identificador;
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
		trayectos=new ArrayList<>();
	}
	
	/**
	 * Comprobar que el identificador sea correcto.
	 * @param identificador El identificador del contenedor
	 * @throws IllegalArgumentException Si el identificador no tiene la longitud correcta
	 * @throws IllegalArgumentException Si alguna de las tres letras iniciales no son mayusculas, o si la cuarta letra no se corresponde 
	 * con los caracteres - 'U', 'J', 'Z' o si la longitud de la serie es distinta de 6.
	 */
	private void comprobarIdentificador(String identificador) {
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
	
		if (!(codigo.equals(codigo.toUpperCase()) && (equipamiento=='U'||equipamiento=='J'||equipamiento=='Z') && serie.length()==6)) {
			throw new IllegalArgumentException("Identificador no valido");
		}
	}
	
	/**
     * Obtener el digito de control correcto del contenedor a partir del identificador 
     * mediante el algoritmo: {@link <a href="https://en.wikipedia.org/wiki/ISO_6346">Algoritmo codigo control</a>}
     * @param identificador El identificador del contenedor
     * @return codigoControl El número que corresponde al codigo del dueño,equipamiento y serie del contenedor.
     */
    private int obtenerDigitoControl(String identificador) {
    	comprobarIdentificador(identificador);
        //utilizo un mapa para guardar las letra con sus correspondientes valor
        Map<String, Integer> tabla = new HashMap<>();
        tabla.put("A", 10);tabla.put("B", 12);tabla.put("C", 13);tabla.put("D", 14);tabla.put("E", 15);tabla.put("F", 16);tabla.put("G", 17);tabla.put("H", 18);tabla.put("I", 19);tabla.put("J", 20);tabla.put("K", 21);tabla.put("L", 23);tabla.put("M", 24);tabla.put("N", 25);tabla.put("O", 26);tabla.put("P", 27);tabla.put("Q", 28);tabla.put("R", 29);tabla.put("S", 30);tabla.put("T", 31);tabla.put("U", 32);tabla.put("V", 34);tabla.put("W", 35);tabla.put("X", 36);tabla.put("Y", 37);tabla.put("Z", 38);
        //En este vector guardo los valores de cada caracter y la serie de numeros
        int[] vector = new int[10];
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
        int resultado =(int) suma/11;
        resultado = resultado * 11;
        int codigoControl=(int)(suma - resultado);
        if(codigoControl==10) codigoControl=0;
        return codigoControl;
    }
    
    
	/**
	 * Espacio relativo al número de plazas que ocupa el contenedor según su tipo
	 * @return 1 si solo necesita una plaza o 2 si necesita dos plazas
	 */
	public abstract int getEspacio();
	
	/**
	 * Obtenemos el codigo de infraestructura del contenedor en un array de tamaño tres, en el que la posicion
	 * 0 es el correspodiente al transporte por barco,la 1 al transporte por tren y la 2 al 
	 * transporte por camión.
	 * @return El array codificado en binario para los tres transportes siendo 1 que ese 
	 * transporte es valido y 0 que no soporta ese transporte.
	 * @see <a href="https://github.com/kostaskougios/cloning">Cloning Library</a>
	 */
	public abstract int[] getCodigoTransporte();
    
    
    
	/**
	 * Obtiene el Puerto destino del trayecto global que realiza el contenedor
	 * @return El puerto destino del trayecto global(conjunto)
	 */
    public Puerto getDestinoFinal() {
    	return destinoFinal;
    }
    
    
    
	/**
	 * Obtiene la carga util del contenedor
	 * @return carga 
	 */
	public double getCarga() {
		return carga;
	}
	
    /**
	 * Mediante los parametros del peso y la unidad de este seteamos el peso en kilogramos
	 * @param peso El peso del contenedor
	 * @param unidPeso La unidad asociada al peso (Debe ser Kg(la K en mayusculas-kilogramos) o lb(libras))
	 * @throws IllegalArgumentException si el {@param peso} <0
	 * @throws IllegalArgumentException si las unidades del {@param unidPeso} no son Kg ni lb
	 * @see Contenedor#setPesoKilo(double) 
	 * @see Contenedor#setPesoLibra(double)
	 */
	private void comprobarUnidadesPeso(double peso, String unidPeso) {
		if (unidPeso.equals("Kg")) {
    		setPesoKilo(peso);
    	}
    	else if(unidPeso.equals("lb")) {
    		setPesoLibra(peso);	
    	}
    	else { throw new IllegalArgumentException("String unidPeso no correcto unidades");
    	}
	}
	

	
	/**
	 * Cambiar el peso de libras a kilogramos y posteriormente lo setea
	 * @param pesocontenedor El peso del contenedor en libras
	 * @throws IllegalArgumentException Si @parm pesoContenedor <0
	 * @see Contenedor#setPesoKilo(double)
	 */
	public void setPesoLibra(double pesoContenedor) {
		if(pesoContenedor<0) throw new IllegalArgumentException("Peso no puede ser negativo");
		double nuevoPeso = pesoContenedor * PESOKILO;
		setPesoKilo(nuevoPeso);
	}
	
	/**
	 * Guardar el peso del contenedor en kilogramos
	 * @param peso El peso del contenedor ya en Kilos
	 * @throws IllegalArgumentException en el caso de que peso sea negativo
	 */
	public void setPesoKilo(double peso) {
		if(peso<0) throw new IllegalArgumentException("Peso no puede ser negativo");
		this.peso = peso;
	}

	/**
	 * Mediante los parametros del volumen y la unidad de este seteamos el volumen en metros cubicos
	 * @param volumen El volumen del contenedor 
	 * @param unidVol La unidad correspondiente al volumen elegida(Debe ser m3(metros cubicos) o ft3(pies cubicos))
	 * @throws IllegalArgumentException Si el @param volumen <0
	 * @throws IllegalArgumentException Si @param unidVol no son m3 o ft3
	 * @see Contenedor#setVolumenMetros(double)
	 * @see Contenedor#setVolumenPies(double)
	 */
	private void comprobarUnidadesVolumen(double volumen, String unidVol) { 
		if (unidVol.equals("m3")) {
			setVolumenMetros(volumen);
		}
		else if(unidVol.equals("ft3")) {
			setVolumenPies(volumen);	
		}
		else { throw new IllegalArgumentException("String Volumen no correcto unidades o <0");
		}
	}
	
	/**
	 * Cambiar el volumen de pies cubicos a metros cubicos y lo setea 
	 * @param volumenContenedor EL volumen del contenedor en pies cubicos
	 * @throws IllegalArgumentException en el caso de que el volumen sea negativo
	 * @see Contenedor#setVolumenMetros(double)
	 */
	public void setVolumenPies(double volumenContenedor) {
		if(volumenContenedor<0) throw new IllegalArgumentException("Volumen no puede ser negativo");
		double nuevoVolumen = volumenContenedor * VOLUMENMETROS;
		setVolumenMetros(nuevoVolumen);
	}
		
	/**
	 * Guardar volumen en metros cubicos
	 * @param volumen El volumen del contenedor en metros cúbicos
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
	 * @return false si esta en transito y true si esta en recogida
	 */
	public boolean getEstado() {
		return estado;
	}
	
	/**
	 * Cambiar el contenedor para reflejar que tiene techo
	 */
	
	private void setTecho() {
		techo = true;
	}
	
	/**
	 * Cambiar a contenedor no tiene techo
	 */
	private void setNoTecho() {
		techo = false;
	}
	
	/**
	 * Obtener si el contenedor tiene techo o no
	 * Devuelve true si tiene y false en caso contrario
	 * @return true si tiene techa y false en caso contrario
	 */
	public boolean getTecho() {
		return techo;
	}
	

	/**
	 * Establecer el puerto destino del trayecto global que realizará el contenedor
	 * @param destino El {@link Puerto} destino del trayecto global a realizar por el contenedor
	 * @throws IllegalArgumentException Si puerto destino (@param destino) del trayecto global ==null
	 */
	public void hacerTrayecto(Puerto destino) 
	{
		if(destino==null) throw new IllegalArgumentException("El puerto destino no debe ser nulo");
		destinoFinal=destino;
	}
	
	/**
	 * Realiza los diferentes viajes que hace un contenedor hasta llegar al destino final del trayecto global.
	 * Cuando recibimos un trayecto de tipo {@link PackCamionBarco} o {@link PackCamionTren} analizamos 
	 * cada uno de sus trayectos Simples ({@link Simple}) para que estos tambien cumplan las condiciones
	 * ademásde que el Pack este finalizado,es decir, si hay un trayecto cuyo
	 * origen es el origen del Pack y si hay un trayecto cuyo destino es el destino del Pack.
	 * @param trayecto Uno de los diferentes trayectos que se ejecutan por el contenedor hasta el destino final
	 * @throws IllegalArgumentException Si @param trayecto==null
	 * @throws IllegalArgumentException Si @param trayecto ya se ha realizado. 
	 * Es decir el conjunto trayectos realizados contains @param trayecto
	 * @throws IllegalArgumentException Si no se ha inicializado el destino final del trayecto global.Es decir
	 * no hay un plan de llegada final del contenedor.
	 * @throws IllegalArgumentException Si el contenedor no se encuentre en el puerto origen del trayecto
	 * @throws IllegalArgumentException Si el puerto origen coincide con el destino final del trayecto global.
	 * Se deberia haber realizado un nuevo trayecto global,ya que sino el contenedor esta en estado de recogida.
	 * @throws IllegalArgumentException Si el contenedor no puede realizar ese transporte por la infraestructura utilizada del trayecto.
	 * @throws IllegalArgumentException Si el muelle origen no soporta la infraestructura de transporte que marca el transporte.
	 * @throws IllegalArgumentException Si el muelle destino no soporta la infraestructura de transporte que marca el transporte.
	 * @throws IllegalArgumentException Si el Pack no está finalizado tal como se explicita en las condiciones anteriores.
	 * @see Contenedor#comprobarTrayectosYMuelle(Trayecto)
	 * @see Combinado#trayectoRealiazado()
	 * @see Trayecto
	 */
	public void hacerViajes(Trayecto trayecto) {
		//Comprueba que cumple las condiciones
		comprobarTrayectosYMuelle(trayecto);
		if(trayecto instanceof Combinado) {
			comprobarCombinado(trayecto);
		}
		else {
			comprobarSimple(trayecto);
		}
		trayectos.add(trayecto);
		if(trayecto.getPuertoDestino().equals(destinoFinal)) this.setRecogida(); //Si hemos llegado al destino final global esta listo para recogerse el contenedor
	}
	/**
	 * Comprueba las condiciones para que el contenedor puede hacer el trayecto
	 * @param trayecto El trayecto a analizar que cumple las condiciones de {@link Contenedor#hacerViajes(Trayecto)}
	 * @throws IllegalArgumentException Si @param trayecto==null
	 * @throws IllegalArgumentException Si @param trayecto ya se ha realizado. 
	 * Es decir el conjunto trayectos realizados contains @param trayecto
	 * @throws IllegalArgumentException Si no se ha inicializado el destino final del trayecto global.Es decir
	 * no hay un plan de llegada final del contenedor.
	 * @throws IllegalArgumentException Si el contenedor no se encuentre en el puerto origen del trayecto
	 * @throws IllegalArgumentException Si el puerto origen coincide con el destino final del trayecto global.
	 * Se deberia haber realizado un nuevo trayecto global,ya que sino el contenedor esta en estado de recogida.
	 */
	private void comprobarTrayectosYMuelle(Trayecto trayecto) {
		if(trayecto==null)
			throw new IllegalArgumentException("Trayecto nulo");
		
		if(trayectos.contains(trayecto))
			throw new IllegalArgumentException("Trayecto ya realizado");
		
		if(destinoFinal==null) 
			throw new IllegalArgumentException("Establezca un destino final del trayecto global");

		if(trayecto.getPuertoOrigen().equals(getDestinoFinal()))
			throw new IllegalArgumentException("Ya se había llegado al puerto destino del trayecto.Inicie un nuevo trayecto global");
		//que el contenedor este en ese muelle/puerto
		List<Muelle> listaMuellesOrigen=trayecto.getPuertoOrigen().getListaMuelles();
		int posicionMuelleOrigen=-1;
		int i=0;

		while(posicionMuelleOrigen==-1 && i<listaMuellesOrigen.size()) {
			Muelle analisis=listaMuellesOrigen.get(i);
			int plaza=analisis.getPlaza(this.getIdentificador());
			if(plaza!=-1) posicionMuelleOrigen=i;
			i++;
		}
		if (posicionMuelleOrigen==-1) 
			throw new IllegalArgumentException("El contenedor no esta en ese puerto");
	}
	
	/**
	 * Comprueba más reglas para realizar viajes de un contenedor, en el caso de que
	 * el trayecto propuesto sea un trayecto Simple ({@link Simple})
	 * @param trayecto El trayecto a analizar que cumple las condiciones de {@link Contenedor#hacerViajes(Trayecto)}
	 * @throws IllegalArgumentException Si el contenedor no puede realizar ese transporte por la infraestructura utilizada del trayecto.
	 * @throws IllegalArgumentException Si el muelle origen no soporta la infraestructura de transporte que marca el transporte.
	 * @throws IllegalArgumentException Si el muelle destino no soporta la infraestructura de transporte que marca el transporte.
	 */
	private void comprobarSimple(Trayecto trayecto) {
		if (this.getCodigoTransporte()[((Simple) trayecto).getCodigoSimple()]!=1)
			throw new IllegalArgumentException("El contenedor no puede realizar ese trayecto por la infraestructura");
		if(trayecto.getMuelleOrigen().getInfraestructuraMuelle()[((Simple) trayecto).getCodigoSimple()]!=1) 
			throw new IllegalArgumentException("El muelle origen no soporta este trayecto");
		
		if(trayecto.getMuelleDestino().getInfraestructuraMuelle()[((Simple) trayecto).getCodigoSimple()]!=1)
			throw new IllegalArgumentException("El muelle destino no soporta este trayecto");
	}

	/**
	 * Comprueba más reglas para realizar viajes de un contenedor, en el caso de que
	 * el trayecto propuesto sea un trayecto Combinado ({@link Combinado})
	 * @param trayecto El trayecto a analizar que cumple las condiciones de {@link Contenedor#hacerViajes(Trayecto)}
	 * @throws IllegalArgumentException Si el contenedor no puede realizar ese transporte por la infraestructura utilizada del trayecto.
	 * @throws IllegalArgumentException Si el muelle origen no soporta la infraestructura de transporte que marca el transporte.
	 * @throws IllegalArgumentException Si el muelle destino no soporta la infraestructura de transporte que marca el transporte.
	 */
	private void comprobarCombinado(Trayecto trayecto) {
		if(!((Combinado)trayecto).trayectoRealiazado())
			throw new IllegalArgumentException("No se inicio o finalizó correctamente con un trayecto en los puertos del constructor del pack");
		Iterator<Simple> iteradorTrayec=((Combinado)trayecto).getTrayectosPack().iterator();
		while(iteradorTrayec.hasNext()) {
			Simple trayecAnalisis=iteradorTrayec.next();
			comprobarSimple(trayecAnalisis);
		}
	}

	/**
	 * Calcular el precio total en euros del trayecto global, es decir, la suma de costes de cada viaje.
	 * @return sumaTrayectos El coste total de los viajes que se han realizado para llegar al destino final.
	 */
	
	public double precio() {
		double sumaTrayectos=0.0;
		Iterator<Trayecto> itrTrayectos=trayectos.iterator();
		while(itrTrayectos.hasNext()) {
			Trayecto analisis=itrTrayectos.next();
			sumaTrayectos+=analisis.costeTrayecto();
		}
		return sumaTrayectos;	
	}
	
}
