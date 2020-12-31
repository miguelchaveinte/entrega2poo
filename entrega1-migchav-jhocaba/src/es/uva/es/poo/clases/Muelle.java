package es.uva.es.poo.clases;


import es.uva.inf.poo.maps.GPSCoordinate;
import java. util. *;
import com.rits.cloning.Cloner;



/**
 * Clase que almacena los datos relativos a los muelles.dsf
 * @author jhocaba
 * @author migchav
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

	//TODO:COMENTAR LO DE LA INFRAESTRUCTURA DEL MUELLE
	/**
	 * Inicializador con parametros
	 * @param infraestructura - codigo de tres cifras que identifica las infraestructuraras
	 * disponibles en el muelle. Esta codificación debera atender al siguiente formato binario:
	 * primera posicion transporte por barco,segunda posicion transporte por tren y la tercera transporte por camion.
	 * Se admiten la combinacion de transportes pero no que ninguno este disponible. Ejemplo:100 - seria un muelle con
	 * solo posibilidad de barco,011 - posibilidad de tren y camion y no barco,111 posibilidad de transporte en los tres medios.
	 * @param identificador- numero de dos digitos que identifica al Muelle
	 * @param coordenada - coordenada GPS del muelle
	 * @param estado- estado operativo o fuera de servicio ('O'=>OPERATIVO,'F'=>fuera de servicio)
	 * @param numPlazas - numero de plazas del muelle
	 * @throws IllegalArgumentException-si {@param coordenada} ==null
	 * @throws IllegalArgumentException-si {@param estado} !=('O' || 'F')
	 * @throws IllegalArgumentException-si {@param numPlazas} <1
	 * @throws IllegalArgumentException-si {@param identificador.length} !=2
	 * @throws IllegalArgumentException-si {@param infraestructura.length} !=3
	 * @throws IllegalArgumentException-si {@param infraestructura} ==000
	 */
	public Muelle(int infraestructura,int identificador,GPSCoordinate coordenada,char estado,int numPlazas) {
		if(Integer.toString(identificador).length()!=2 )throw new IllegalArgumentException("El identificador de muelle debe ser un número de 2 digitos");
		if(coordenada==null) throw new IllegalArgumentException("la coordenada debe no ser  nula ");
		//TODO:nuevos test!!!!!!!!!!!!!!!!!!!
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
	 * @param estado- estado operativo o fuera de servicio ('O'=>OPERATIVO,'F'=>fuera de servicio)
	 * @throws IllegalArgumentException-si {@param estado}!='O' && {@param estado}!='F'
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
	//TODO:GET INFRAESTRUCTURA???????????? no hace falta
	
	public int [] getInfraestructuraMuelle() {
		Cloner cloner=new Cloner();
		return cloner.deepClone(infraestructura);
	}
	//TODO: TEST DE ESTAS DOS DE ABAJO!!!!!!!!!!!!!
	
	/**
	 * Setea el estado de la plaza dada por {@param posicion}  a 0 (que significa que 
	 * no esta llena) en el vector que guardamos los estado de todas las plazas.
	 * @param posicion - plaza que queremos modificar su estado
	 */
	public void setVaciaYSemi(int posicion) {
		estadoPlaza[posicion]=0;
	}
	
	/**
	 * Setea el estado de la plaza dada por {@param posicion}  a 1 (que significa que 
	 * esta llena) en el vector que guardamos los estado de todas las plazas.
	 * @param posicion - plaza que queremos modificar su estado
	 */
	public void setLlena(int posicion) {
		estadoPlaza[posicion]=1;
	}
	
	/**
	 * Retorna el identificador del muelle
	 * @return Identificador del muelle
	 */

	public int getIdMuelle() {
		return identificador;
	}
	/**
	 * Retorna true si el muelle esta operativo o false si esta fuera de servicio
	 * @return estado del muelle
	 */
	public boolean getEstado() {
		return estado;
	}
	/**
	 * Retorn el numero de plazas
	 * @return el int de plazas del muelle
	 */
	public int getNumPlazas() {
		return numPlazas;
	}
	/**
	 *Retorna Lista de todas las plazas con sus niveles y su estado (vacia,semillena o llena)
	 * 
	 * @return lista de las plazas
	 */
	public List<List<Contenedor>> getListPlazas(){
		Cloner cloner=new Cloner();
		return cloner.deepClone(plazas);
	}
	/**
	 * Retorna el string del estado de la plaza
	 * @return "vacia" si la plaza no tiene contenedores,"semillena" si tiene contenedores
	 * y espacio o llena  si no admite mas espacio
	 */
	public int getEstadoPlaza(int posicion) {
		return estadoPlaza[posicion];
	}
	/**
	 * Guardamos un arraylist de las plazas inicializado a vacio
	 * @param numPlazas- numero dde plaza a creal
	 * @throws IllegalArgumentException-si el numPlazas<=0
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
	 * Retorna coordenada gps del muelle
	 * @return coordenada gps
	 */
	public GPSCoordinate getCoordenada() {
		return coordenada;
	}

	//TODO:ACTUALIZAR JAVADOC
	/**
	 * Mete el contenedor dado en la plaza indicada si la infraestructura del muelle permite posteriormente transportar el contenedor.
	 * Si esta está llena o el contenedor es FlatRack y necesita dos plazas buscamos una nueva plaza si es posible.
	 * Si es un Contenedor Estandar o Refrigerado que necesitan de espacio un nivel buscamos una plaza con preferencia 
	 * de que este en estado semillena, y si eso no es posible asignamos una vacia. Si el contenedor es FlatRack 
	 * buscaremos dos plazas consecutivas vacias. Si estas nuestro puerto no puede satisfacer estas
	 * peticiones lanzaremos una excepcion de que no podemos almacenar ese contenedor.
	 * @param contenedor- que se va a meter en la plaza
	 * @param plaza-plaza en el cual se va a incluir (indice desde 0 hasta numPlazas-1)
	 * @throws IllegalArgumentException - no se va a poder transportar el contenedor posteriormente por la infraestructura del muelle
	 * @throws IllegalArgumentException - no se puede almacenar el contenedor por falta de espacio
	 * @throws IllegalArgumentException - contenedor nulo
	 * @throws IllegalArgumentException - plaza<0
	 */
	public void asignarPlaza(Contenedor contenedor,int plaza) {
		if(contenedor==null || contenedor.getIdentificador()==null) throw new IllegalArgumentException("El contenedor no puede ser nulo");
		if(plaza<0) throw new IllegalArgumentException("La plaza no debe ser <0");	
		correctoInfraestructura(contenedor);
		
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
		if(plazaNueva!=plaza) asignarPlaza(contenedor,plazaNueva);
		else {
			actualizarPlaza(contenedor,plaza,statePlaza,contenedorDosPlazas);

		}
	}
	
	//TODO:JAVADOCS DE LOS PRIVATE ESTOS DE AQUI->
	
	private void actualizarPlaza(Contenedor contenedor,int plaza,int statePlaza,int contenedorDosPlazas) {
		int plazaNueva;
		if(statePlaza==1) {
			plazaNueva=buscaEspacio(1);
			asignarPlaza(contenedor,plazaNueva);
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
	
	
	
	//TODO:JAVADOC!!!!!!!!!!!!!!!!
	/**
	 * Nos busca una ...
	 * @param espacio
	 * @return retorno - plaza nueva encontrada segun el caso de preferencia que vienen dado por {@param espacio}
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
	 * Primero le buscamos  en que plaza se encuentra y posteriormente
	 * analizamos el nivel de dicha plaza y obtenemos el objeto contenedor que nos pedian.
	 * @param identificador-identificador del contenedor 
	 * @return el contenedor extraido
	 * @throws IllegalArgumentException -identificador no valido
	 * @throws IllegalArgumentException -contenedor no encontrado
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
	 * asociado al muelle sus numero de plazas segun tippo
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
	 * Indice de la plaza donde hemos encontrado el contenedor(index empieza desde 0)
	 * @param identificadoridentificador del contenedor 
	 * @return index de la plaza en la que se encuentra el contenedor
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
	 * Retorna un string que nos dice la plaza y el nivel de esta de un contenedor dado
	 * con el siguiente formato: "plaza/nivel"
	 * @param identificador-identificador del contenedor 
	 * @return string que nos especifica en la plaza y el nivel donde se ha encontrado el contenedor
	 * @throws IllegalArgumentException-si el contenedor no se ha encontrado
	 * @throws IllegalArgumentException-identificador contenedor no valido
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
