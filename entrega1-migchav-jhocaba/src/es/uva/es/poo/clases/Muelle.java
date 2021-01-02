package es.uva.es.poo.clases;


import es.uva.inf.poo.maps.GPSCoordinate;
import java. util. *;
import com.rits.cloning.Cloner;



/**
 * Clase que almacena los datos de la gestion de los muelles. Mediante un codigo de infraestructura,
 * identificador,coordenada GPS,estado y numero de plazas podemos hacer las gestionespara
 * almacenar,obtener y transportar contenedores en él de una forma eficiente en cuanto al espacio.
 * @author migchav,jhocaba
 *
 */
public class Muelle   {
	
	private int identificador;
	private GPSCoordinate coordenada;
	private boolean estado;	
	private int numPlazas;
	private List<List<Contenedor>> plazas;
	private int [] estadoPlaza;
	private int [] infraestructura;

	/**
	 * Crea un nuevo puerto con un identificador, infraestructura de transporte y numero de plazas  distinguido.
	 * @param infraestructura Código de tres cifras que identifica las infraestructuraras
	 * disponibles en el muelle. Esta codificación debera atender al siguiente formato binario:
	 * primera posicion transporte por barco,segunda posicion transporte por tren y la tercera transporte por camion.
	 * Se admiten la combinacion de transportes pero no que ninguno este disponible. Ejemplo:100 - seria un muelle con
	 * solo posibilidad de barco,011 - posibilidad de tren y camion y no barco,111 posibilidad de transporte en los tres medios.
	 * @param identificador El número de dos digitos que identifica al Muelle
	 * @param coordenada La coordenada GPS del muelle
	 * @param estado El estado operativo o fuera de servicio ('O'=>OPERATIVO,'F'=>fuera de servicio)
	 * @param numPlazas El número de plazas del muelle
	 * @throws IllegalArgumentException Si @param coordenada ==null
	 * @throws IllegalArgumentException Si @param estado !=('O' || 'F')
	 * @throws IllegalArgumentException Si @param numPlazas <1
	 * @throws IllegalArgumentException Si @param identificador.length !=2
	 * @throws IllegalArgumentException Si @param infraestructura.length !=3
	 * @throws IllegalArgumentException Si @param infraestructura ==000
	 * @throws IllegalArgumentException Si @param infraestructura !=(100||010||001||110||011||101||111)
	 */
	public Muelle(int infraestructura,int identificador,GPSCoordinate coordenada,char estado,int numPlazas) {
		if(Integer.toString(identificador).length()!=2 )throw new IllegalArgumentException("El identificador de muelle debe ser un número de 2 digitos");
		if(coordenada==null) throw new IllegalArgumentException("la coordenada debe no ser  nula ");
		if(Integer.toString(infraestructura).length()!=3 )throw new IllegalArgumentException("El identificador de infraestructura debe ser un número de 3 digitos");
		if(infraestructura==000) throw new IllegalArgumentException("El identificador de infraestructura no puede ser que no admita ningun transporte");
		this.identificador=identificador;
		this.coordenada=coordenada;
		this.numPlazas=numPlazas;
		plazas=new ArrayList<>();
		estadoPlaza=new int[numPlazas];
		setEstado(estado);	
		String codigoInfra=String.valueOf(infraestructura);
		this.infraestructura=new int[3];
		for(int i=0; i<3; i++){
			int	codigoCaracter=Character.getNumericValue(codigoInfra.charAt(i));
			if(codigoCaracter!=0 && codigoCaracter!=1) throw new IllegalArgumentException("El identificador de infraestructura debe ser un codigo de 0 y 1");
			this.infraestructura[i] = codigoCaracter;
		}
		setPlazas(numPlazas);
	}

	/**
	 * Setea el estado del muelle a true si esta operativo o a false si esta a false
	 * @param estado A operativo o fuera de servicio ('O'=>OPERATIVO,'F'=>fuera de servicio)
	 * @throws IllegalArgumentException Si {@param estado}!='O' && {@param estado}!='F'
	 */
	public void setEstado(char estado) {
		if (estado!='O' && estado!='F') throw new IllegalArgumentException("Estado no valido");
		if (estado =='O') {
				this.estado=true;
		}
		else {
			this.estado=false;
		}
	}
	
	/**
	 * Obtenemos el codigo de infraestructura en un array de tamaño tres, en el que la posicion
	 * 0 es el correspodiente al transporte por barco,la 1 al transporte por tren y la 2 al 
	 * transporte por camión.
	 * @return El array codificado en binario para los tres transportes siendo 1 que ese 
	 * transporte es valido y 0 que no soporta ese transporte.
	 */
	public int [] getInfraestructuraMuelle() {
		Cloner cloner=new Cloner();
		return cloner.deepClone(infraestructura);
	}
	
	/**
	 * Setea el estado de la plaza dada por @param posicion  a 0 (que significa que 
	 * no esta llena) en el vector que guardamos los estado de todas las plazas.
	 * @param posicion La plaza que queremos modificar su estado
	 */
	public void setVaciaYSemi(int posicion) {
		estadoPlaza[posicion]=0;
	}
	
	/**
	 * Setea el estado de la plaza dada por @param posicion  a 1 (que significa que 
	 * esta llena) en el vector que guardamos los estado de todas las plazas.
	 * @param posicion La plaza que queremos modificar su estado
	 */
	public void setLlena(int posicion) {
		estadoPlaza[posicion]=1;
	}
	
	/**
	 * Retorna el identificador del muelle
	 * @return identificador El código del muelle
	 */

	public int getIdMuelle() {
		return identificador;
	}
	/**
	 * Retorna true si el muelle esta operativo o false si esta fuera de servicio
	 * @return estado La situacion operativa del muelle
	 */
	public boolean getEstado() {
		return estado;
	}
	/**
	 * Obtenemos el numero de plazas
	 * @return El numero de plazas del muelle
	 */
	public int getNumPlazas() {
		return numPlazas;
	}
	
	/**
	 * Retorna la lista completa de plazas del muelle con los contenedores que contiene
	 * 
	 * @return La lista de lista que contiene los contenedores cada plaza previo clonado 
	 * profundidad.
	 * @see <a href="https://github.com/kostaskougios/cloning">Cloning Library</a>
	 */
	public List<List<Contenedor>> getListPlazas(){
		Cloner cloner=new Cloner();
		return cloner.deepClone(plazas);
	}
	/**
	 * Retorna el codigo del estado de la plaza
	 * @param posicion La plaza de la que queremos obtener el estado
	 * @return El codigo asociado a esa plaza.Será 0 si no está llena y hay posibilidad
	 * de almacenar algún contenedor más.Por el contrario será 1.
	 */
	public int getEstadoPlaza(int posicion) {
		return estadoPlaza[posicion];
	}
	/**
	 * Guardamos un arraylist de las plazas inicializado a vacio según el número de plazas del muelle
	 * @param numPlazas Las plazas que tiene el muelle
	 * @throws IllegalArgumentException Si el numPlazas<=0
	 */
	public void setPlazas(int numPlazas) {
		if(numPlazas<=0) {
			throw new IllegalArgumentException("El número de plazas no puede ser menor o igual a 0");
		}
		for(int i=0;i<numPlazas;i++) {
			List <Contenedor>nivelContenedores=new ArrayList<>(4);
			setVaciaYSemi(i);
			plazas.add(nivelContenedores);
		}
	}
	/**
	 * Retorna coordenada GPS del muelle
	 * @return coordenada La posición GPS del muelle.
	 */
	public GPSCoordinate getCoordenada() {
		return coordenada;
	}

	/**
	 * Mete el contenedor dado en la plaza indicada si la infraestructura del muelle permite posteriormente transportar el contenedor.
	 * Si esta está llena o el contenedor es FlatRack y necesita dos plazas y la que nos pasan no cumple ese principio
	 * buscamos una nueva plaza si es posible.Además se actualiza el estado de dicha plaza tras introducir el contenedor.
	 * Si es un Contenedor Estandar o Refrigerado que necesitan de espacio un nivel y la plaza dada esta llena, 
	 * buscamos una plaza con preferencia de que este en estado semillena, y si eso no es posible asignamos una vacia con preferencia
	 * a que este junta a plazas llenas o semillenas para que si recibimos un FlatRack poder tener dos plazas libres juntas en el futuro.
	 * Si el contenedor es FlatRack buscaremos dos plazas consecutivas vacias. Si estas nuestro puerto no puede satisfacer estas
	 * peticiones lanzaremos una excepcion de que no podemos almacenar ese contenedor.
	 * @param contenedor El contenedor que nos piden almacenar en el muelle
	 * @param plaza La plaza en el cual se va a incluir (indice desde 0 hasta numPlazas-1)
	 * @throws IllegalArgumentException No se puede transportar el contenedor por la infraestructura del muelle
	 * @throws IllegalArgumentException El contenedor ya está almacenado en el muelle
	 * @throws IllegalArgumentException No se puede almacenar el contenedor por falta de espacio
	 * @throws IllegalArgumentException El contenedor nulo
	 * @throws IllegalArgumentException La plaza<0
	 * @see Muelle#actualizarPlaza(Contenedor, int, int, int)
	 * @see Muelle#buscaEspacio(int)
	 * @see Muelle#comprobarFlatRack(int)
	 * @see Muelle#correctoInfraestructura(Contenedor)
	 */
	public void asignarPlaza(Contenedor contenedor,int plaza) {
		if(contenedor==null || contenedor.getIdentificador()==null) throw new IllegalArgumentException("El contenedor no puede ser nulo");
		if(plaza<0) throw new IllegalArgumentException("La plaza no debe ser <0");
		//Comprobar infraestructura adecuada
		correctoInfraestructura(contenedor);
		//Comprobar que el contenedor no esta ya en el muelle
		if(getPlaza(contenedor.getIdentificador())!=-1)throw new IllegalArgumentException("El contenedor ya esta contenido en el muelle");		
		int statePlaza=getEstadoPlaza(plaza);
		//Comprobar que si quiere dos plazas la de al lado tb esta libre
		int contenedorDosPlazas=0;
		int plazaNueva=plaza;
		String comprobacion="";
		if(contenedor.getEspacio()==2) {
			comprobacion=comprobarFlatRack(plaza);
			String[] argumentosFlat =comprobacion.split("/");
			plazaNueva=Integer.parseInt(argumentosFlat[0]);
			contenedorDosPlazas=Integer.parseInt(argumentosFlat[1]);
		}
		if(plazaNueva!=plaza) asignarPlaza(contenedor,plazaNueva);//Hemos encontrado una nueva plaza,que la que nos dieron no se podia
		else { //Se podia con la plaza que nos dieron o no era contenedor FlatRack, guardamos contenedor y actualizamos estado de la plaza.
			actualizarPlaza(contenedor,plaza,statePlaza,contenedorDosPlazas);

		}
	}
	
	/**
	 * Revisa si el contenedor de espacio uno podemos almacenarle en la plaza dada y sino intentamos buscar
	 * una donde meterle y volvemos a llamar a esta funcion.Una vez esto, guardamos el contenedor en dicha 
	 * posicion y actualizamos su estado de plaza.
	 * @param contenedor El contenedor a almacenar
	 * @param plaza La plaza en donde guardar el contenedor
	 * @param statePlaza El estado de @param plaza
	 * @param contenedorDosPlazas Si el contenedor es FlackRack hay que almacenarlo en @param plaza y en 
	 * otra plaza que puede ser la posterior o la anterior
	 * @throws IllegalArgumentException Si el contenedor no puede almacenarse porque esta lleno el muelle
	 * (Excepcion salta en {@link Muelle#buscaEspacio(int)})
	 * @see Muelle#buscaEspacio(int)
	 */
	private void actualizarPlaza(Contenedor contenedor,int plaza,int statePlaza,int contenedorDosPlazas) {
		int plazaNueva;
		if(statePlaza==1) {
			plazaNueva=buscaEspacio(1);
			actualizarPlaza(contenedor,plazaNueva,getEstadoPlaza(plazaNueva),0);
		}
		else {
			if(contenedor.getEspacio()==1) {
				plazas.get(plaza).add(contenedor);
				if(plazas.get(plaza).size()==4) setLlena(plaza);
			}
			else {
				plazas.get(plaza).add(contenedor);
				plazas.get(plaza+contenedorDosPlazas).add(contenedor);
				setLlena(plaza);
				setLlena(plaza+contenedorDosPlazas);
			}
		}
	}
	
	/**
	 * Comprueba si el contenedor que se quiere introducir cumple las condiciones de infraestructura
	 * del muelle. Sino es así salta excepción
	 * @param contenedor El contenedor que queremos almacenar
	 */
	private void correctoInfraestructura(Contenedor contenedor) {
		boolean esPosibleTrans=false;
		int i=0;
		while(!esPosibleTrans && i<3) {
			if(infraestructura[i]==contenedor.getCodigoTransporte()[i] && infraestructura[i]==1) {
				esPosibleTrans=true;
			}
			i++;
		}
		if(!esPosibleTrans) throw new IllegalArgumentException("No es posible asignar ese contenedor ya que el muelle no puede realizar el transporte");
	}
	
	/**
	 * Comprueba que la plaza en la cual quieren introducir el contenedor FlatRack cumple las condiones:
	 * Que dicha plaza este vacía y la anterior o la posterior tambien esten vacias ya que este contenedor
	 * necesita de dos plazas completas.
	 * @param plaza La plaza donde se quiere introducir el contenedor
	 * @return String en formato (PLAZA/NUMERO A SUMAR A LA PLAZA) que indica la plaza y la plaza+numero donde
	 * se introducirá el contenedor FlatRack.El número a sumar a la plaza será o +1 o -1 que indicará si es la
	 * plaza posterior o anterior.
	 * @throws IllegalArgumentException Si el contenedor no puede almacenarse porque esta lleno el muelle
	 * (Excepcion salta en {@link Muelle#buscaEspacio(int)})
	 * @see Muelle#buscaEspacio(int)
	 */
	private String comprobarFlatRack(int plaza) {
		int plazaModificada=plaza;
		int contenedorDosPlazas=0;
		if(plaza==0) {
			if((!plazas.get(plaza).isEmpty()|| !plazas.get(plaza+1).isEmpty())) {
				 plazaModificada=buscaEspacio(2);
			}
			contenedorDosPlazas=1;
		}
		else if(plaza==numPlazas-1) {
			if((!plazas.get(plaza).isEmpty()|| !plazas.get(plaza-1).isEmpty())) {
				 plazaModificada=buscaEspacio(2);
			}
			contenedorDosPlazas=-1;
		}
		else {
			if(!plazas.get(plaza).isEmpty() &&(!plazas.get(plaza+1).isEmpty() || !plazas.get(plaza-1).isEmpty())) {
				 plazaModificada=buscaEspacio(2);
			}
			
			contenedorDosPlazas=-1;
			if(plazas.get(plaza+1).isEmpty()) contenedorDosPlazas=1;
		}
		return plazaModificada+"/"+contenedorDosPlazas;
	}
	
	
	/**
	 * Nos busca una nueva plaza para almacenar un nuevo contenedor. 
	 * Si se trata de un contenedor FlatRack buscamos dos plazas consecutivas que sean vacias.
	 * Si por el contrario,es un contenedor que ocupa espacio de uno se llama a {@link Muelle#buscaEspacioOcupaUno()}
	 * para buscar una nueva plaza.
	 * @param espacio El espacio necesario: 1 para aquellos contenedores que necesiten un nivel o 2 si ocupa dos plazas enteras(Contenedor FlatRack)
	 * @return retorno La plaza nueva encontrada segun el caso de preferencia que vienen dado por {@param espacio}
	 * @throws IllegalArgumentException Si el contenedor no puede almacenarse porque esta lleno el muelle
	 * @see Muelle#buscaEspacioOcupaUno()
	 */
	private int buscaEspacio(int espacio) {
		int retorno=-1;
		if(espacio==1) {
			retorno=buscaEspacioOcupaUno();
		}
		else {
			for(int i=0;i<numPlazas-1 && retorno==-1;i++) {
				if( plazas.get(i).isEmpty() && plazas.get(i+1).isEmpty()) {
					retorno=i;	
				}
			}
			if(retorno==-1) throw new IllegalArgumentException("No se puede almacenar ese contenedor por falta de espacio");
		}
		return retorno;
	}
	/**
	 * Para un Contenedor Estandar o Refrigerado que necesitan de espacio un nivel y la plaza dada esta llena, 
	 * buscamos una plaza con preferencia de que este en estado semillena, y si eso no es posible asignamos una vacia con preferencia
	 * a que este junta a plazas llenas o semillenas para que si recibimos un FlatRack poder tener dos plazas libres juntas en el futuro.
	 * @return retorno La plaza nueva encontrada
	 * @throws IllegalArgumentException Si el contenedor no puede almacenarse porque esta lleno el muelle
	 */
	private int buscaEspacioOcupaUno() {
		int semi=-1;
		int retorno=-1;
		int vaciaPrefe=-1;
		boolean posibleAnterior=false;
		for(int i=0;i<numPlazas;i++) {
			if(estadoPlaza[i]==0 && !plazas.get(i).isEmpty()) semi=i;
			else if(plazas.get(i).isEmpty()) {
				retorno=i;
				if(posibleAnterior && !plazas.get(i-1).isEmpty()) vaciaPrefe=i;
			}
			posibleAnterior=true;
		}
		if(retorno==-1) throw new IllegalArgumentException("No se puede almacenar ese contenedor por falta de espacio");
		if(semi!=-1) retorno=semi;
		else if(vaciaPrefe!=-1) retorno=vaciaPrefe;
		return retorno;
	}
	/**
	 * Sacamos un contenedor dado su identificador.
	 * Primero lo buscamos en que plaza y nivel se encuentra mediante {@link Muelle#getNivelPlaza(String)}.
	 * Una vez desglosado el resultado de la función accedemos a la lista de las plazas donde guardamos 
	 * los contenedores y lo quitamos de esta y actualizamos el estado de la plaza. Si se trata de 
	 * un contenedor FlatRack que tiene espacio de dos plazas tambien hay que quitarlo de la otra plaza
	 * donde se encuentra y actualizar dicha plaza.
	 * @param identificador El identificador del contenedor 
	 * @return extraido El contenedor extraido
	 * @throws IllegalArgumentException El contenedor no se ha encontrado
	 * @see Muelle#getNivelPlaza(String)
	 * @see List#remove(Object)
	 */
	public Contenedor sacarContenedor(String identificador)  {
		String indexPlaza=getNivelPlaza(identificador);
		String[] posicion=indexPlaza.split("/");
		int plaza=Integer.parseInt(posicion[0]);
		int nivel=Integer.parseInt(posicion[1]);
		Contenedor extraido=plazas.get(plaza).remove(nivel);
		if(extraido.getEspacio()==1) {
			setVaciaYSemi(plaza);
		}
		else {
			plazas.get(plaza+1).remove(0);
			setVaciaYSemi(plaza);
			setVaciaYSemi(plaza+1);	
		}
		return extraido;
	}

	/**
	 * Retorna en un string del tipo: numeroplazasvacias/numeroplazassemillenas/numeroplazasllenas
	 * asociado al muelle sus numero de plazas segun tipo
	 * @return numeroplazasvacias/numeroplazassemillenas/numeroplazasllenas de ese muelle
	 */
	public String estadoPlazas() {
		int vacias=0;
		int semi=0;
		int llenas=0;
		for(int i=0;i<numPlazas;i++) {
			int state=estadoPlaza[i];
			if(state==1) llenas++;
			else {
				if(plazas.get(i).isEmpty()) vacias++;
				else semi++;
			}
		}
		return (vacias+"/"+semi+"/"+llenas);
	}

	
	
	/**
	 * Indice de la plaza donde hemos encontrado el contenedor(index empieza desde 0).Retornará
	 * -1 si el contenedor con dicho @param identificador no se encuentra en el muelle.
	 * @param indentificador del contenedor 
	 * @return index La posicion de la plaza en la que se encuentra el contenedor
	 */

	public int getPlaza(String identificador)  {
		int index=-1;
		int iterador=0;
		while(index==-1 && iterador<plazas.size()) {
			for(int nivel=0;nivel<plazas.get(iterador).size();nivel++) {
					if(plazas.get(iterador).get(nivel).getIdentificador().equals(identificador)) {
						index=iterador;	
				}
			}
			iterador++;
		}
		return index;
	}


	
	/**
	 * Retorna un string que nos dice la plaza y el nivel en el que se encuentra  un contenedor dado
	 * con el siguiente formato: "plaza/nivel"
	 * @param identificador El identificador del contenedor 
	 * @return nivel El string en formato "plaza/nivel" del contenedor
	 * @throws IllegalArgumentException Si el contenedor no se ha encontrado
	 * @see Muelle#getPlaza(String)
	 */
	public String getNivelPlaza(String identificador)  {
		int indexPlaza=getPlaza(identificador);
		String nivel="";
		if (indexPlaza==-1){
			throw new IllegalArgumentException("El contenedor no se ha encontrado");
		}
		int i=0;
		while(i<plazas.get(indexPlaza).size() && nivel.equals("")) {
			if(plazas.get(indexPlaza).get(i).getIdentificador().equals(identificador)) nivel=indexPlaza+"/"+i;
			i++;
		}
		return nivel;
	}


}
