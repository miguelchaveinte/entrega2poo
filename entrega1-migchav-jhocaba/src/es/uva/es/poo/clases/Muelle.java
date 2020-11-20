package es.uva.es.poo.clases;

import es.uva.inf.poo.maps.GPSCoordinate;
import es.uva.es.poo.clases.Contenedor;
import java.lang.*; 
//import java.util.ArrayList;
//import java.util.List;
import java. util. *;
import java.util.stream.IntStream;


/**
 * Clase que almacena los datos relativos a los muelles
 * @author jhocaba
 * @author migchav
 *
 */
public class Muelle {
	
	private int identificador;
	private GPSCoordinate coordenada;
	private boolean estado;	
	private int numPlazas;
	private List<Muelle> plazas;
	private Contenedor nivelUno;
	private Contenedor nivelDos;
	private Contenedor nivelTres;
	private Contenedor nivelCuatro;
	private String estadoPlaza;

	/**
	 * Constructor sin argumentos (tiene sentido uno sin argumentos?????)
	 */
	public Muelle(){
		
	}

	
	/**
	 * 
	 * @param identificador
	 * @param coordenada
	 * @param estado
	 * @param numPlazas
	 * @throws IllegalArgumentException
	 */
	public Muelle(int identificador,GPSCoordinate coordenada,char estado,int numPlazas) {
		if(Integer.toString(identificador).length()!=2 )throw new IllegalArgumentException("El identificador de muelle debe ser un número de 2 digitos");
		if(coordenada==null) throw new IllegalArgumentException("la coordenada debe no ser  nula ");
		this.identificador=identificador;
		this.coordenada=coordenada;
		this.numPlazas=numPlazas;
		setEstado(estado);	
		setPlazas(numPlazas);
	}

	/**
	 * 
	 * @param estado
	 * @throws IllegalArgumentException
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
	public int getIdMuelle() {
		return identificador;
	}
	public boolean getEstado() {
		return estado;
	}
	
	public int getNumPlazas() {
		return numPlazas;
	}

	public List<Muelle> getListPlazas(){
		return plazas;
	}
	public String getEstadoPlaza() {
		return estadoPlaza;
	}
	/**
	 * Guardamos un arraylist de las plazas
	 * @param numPlazas
	 * @throws IllegalArgumentException
	 */
	public void setPlazas(int numPlazas) {
		if(numPlazas<=0) {
			throw new IllegalArgumentException("El número de plazas no puede ser menor o igual a 0");
		}
		plazas=new ArrayList<Muelle>();
		for(int i=0;i<numPlazas;i++) {
			Muelle descripcionPlazas=new Muelle();
			descripcionPlazas.setNivelUno(new Contenedor());
			descripcionPlazas.setNivelDos(new Contenedor());
			descripcionPlazas.setNivelTres(new Contenedor());
			descripcionPlazas.setNivelCuatro(new Contenedor());
			descripcionPlazas.setEstPlazaVacia();
			plazas.add(descripcionPlazas);
		}
	}
	
	public GPSCoordinate getCoordenada() {
		return coordenada;
	}
	//TODO:PARA CADA TIPO DE ESTADO UN METODOXXX
	public void setEstPlazaVacia() {
		estadoPlaza="vacia";
	}
	public void setEstPlazaSemi() {
		estadoPlaza="semillena";
	}
	public void setEstPlazaLlena() {
		estadoPlaza="llena";
	}
	public void setNivelUno(Contenedor contenedor){
		if(contenedor==null) throw new IllegalArgumentException("El contenedor no puede ser null");
		nivelUno=contenedor;
	}
	public void setNivelDos(Contenedor contenedor){
		if(contenedor==null) throw new IllegalArgumentException("El contenedor no puede ser null");
		nivelDos=contenedor;
	}
	public void setNivelTres(Contenedor contenedor){
		if(contenedor==null) throw new IllegalArgumentException("El contenedor no puede ser null");
		nivelTres=contenedor;
	}
	public void setNivelCuatro(Contenedor contenedor){
		if(contenedor==null) throw new IllegalArgumentException("El contenedor no puede ser null");
		nivelCuatro=contenedor;
	}
	
	public Contenedor getNivelUno() {
		return nivelUno;
	}
	public Contenedor getNivelDos() {
		return nivelDos;
	}
	public Contenedor getNivelTres() {
		return nivelTres;
	}
	public Contenedor getNivelCuatro() {
		return nivelCuatro;
	}
	
	public void asignarPlaza(Contenedor contenedor,int plaza) {
		if(contenedor==null || contenedor.getIdentificador()==null) throw new IllegalArgumentException("El contenedor no puede ser nulo");
		if(plaza<0) throw new IllegalArgumentException("La plaza no debe ser <0");
		String estado=plazas.get(plaza).estadoPlaza;
		if (estado=="llena") {
			for(int iterador=0;iterador<plazas.size();iterador++) {
				if(plazas.get(iterador).estadoPlaza.equals("semillena")){
					asignarPlaza(contenedor,iterador);
					break;
				}
				if(plazas.get(iterador).estadoPlaza.equals("vacia")) {
					asignarPlaza(contenedor,iterador);
					break;
				}
			}
		}
		else {
			if(estado=="semillena"){
				if(plazas.get(plaza).getNivelDos().getIdentificador()==null) {
					plazas.get(plaza).setNivelDos(contenedor);
				} 
				else {
					if (plazas.get(plaza).getNivelTres().getIdentificador()==null) {
						plazas.get(plaza).setNivelTres(contenedor);
					}
					else {
						plazas.get(plaza).setNivelCuatro(contenedor);
						plazas.get(plaza).setEstPlazaLlena();
					}
				}
			}
			else {//vacia
				plazas.get(plaza).setNivelUno(contenedor);
				if(contenedor.getTecho()) {
					plazas.get(plaza).setEstPlazaSemi();
				}
				else {
					plazas.get(plaza).setEstPlazaLlena();
				}
			}
		}
	}
	/**
	 * Sacamos un contenedor dado su identificador.
	 * Primero le buscamos  en que plaza se encuentra y posteriormente
	 * analizamos el nivel de dicha plaza y obtenemos el objeto contenedor que nos pedian.
	 * @param identificador
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Contenedor sacarContenedor(String identificador)  {
		//TODO: ELSE IF -> Y SI NO COINCIDE CON NINGUNO ELSE Y RETURN new Contenedor() o return null??;
		int indexPlaza=getPlaza(identificador);
		if (indexPlaza==-1){
			throw new IllegalArgumentException("El contenedor no se ha encontrado");
		}
		if((plazas.get(indexPlaza).getNivelUno().getIdentificador())==identificador){
			Contenedor retorno=plazas.get(indexPlaza).getNivelUno();
			if	(retorno.getTecho()){
				plazas.get(indexPlaza).setNivelUno(plazas.get(indexPlaza).getNivelDos());
				plazas.get(indexPlaza).setNivelDos(plazas.get(indexPlaza).getNivelTres());
				plazas.get(indexPlaza).setNivelTres(plazas.get(indexPlaza).getNivelCuatro());
				plazas.get(indexPlaza).setNivelCuatro(new Contenedor());
				if (plazas.get(indexPlaza).getNivelUno().getIdentificador()==null) {
					plazas.get(indexPlaza).setEstPlazaVacia();
					return retorno;
				}
				else {
					if (plazas.get(indexPlaza).getNivelDos().getIdentificador()==null) {
						if(plazas.get(indexPlaza).getNivelUno().getTecho()) {
							plazas.get(indexPlaza).setEstPlazaSemi();
							return retorno;
						}
						else {
							plazas.get(indexPlaza).setEstPlazaLlena();
							return retorno;
						}
					}
					else if (plazas.get(indexPlaza).getNivelTres().getIdentificador()==null) {
						if(plazas.get(indexPlaza).getNivelDos().getTecho()) {
							plazas.get(indexPlaza).setEstPlazaSemi();
							return retorno;
						}
						else {
							plazas.get(indexPlaza).setEstPlazaLlena();
							return retorno;
						}
					}
					else {
						if(plazas.get(indexPlaza).getNivelTres().getTecho()) {
							plazas.get(indexPlaza).setEstPlazaSemi();
							return retorno;
						}
						else {
							plazas.get(indexPlaza).setEstPlazaLlena();
							return retorno;
						}
					}
				}
			}
			else {
				plazas.get(indexPlaza).setNivelUno(new Contenedor());
				plazas.get(indexPlaza).setEstPlazaVacia();
				return retorno;
			}
		}
		else if((plazas.get(indexPlaza).getNivelDos().getIdentificador())==identificador){
			Contenedor retorno=plazas.get(indexPlaza).getNivelDos();
			if	(retorno.getTecho()){
				plazas.get(indexPlaza).setNivelDos(plazas.get(indexPlaza).getNivelTres());
				plazas.get(indexPlaza).setNivelTres(plazas.get(indexPlaza).getNivelCuatro());
				plazas.get(indexPlaza).setNivelCuatro(new Contenedor());
				if (plazas.get(indexPlaza).getNivelDos().getIdentificador()==null) {
					plazas.get(indexPlaza).setEstPlazaSemi();
					return retorno;
				}
				else if (plazas.get(indexPlaza).getNivelTres().getIdentificador()==null) {
					if(plazas.get(indexPlaza).getNivelDos().getTecho()) {
						plazas.get(indexPlaza).setEstPlazaSemi();
						return retorno;
					}
					else {
						plazas.get(indexPlaza).setEstPlazaLlena();
						return retorno;
					}
				}
				else {
					plazas.get(indexPlaza).setEstPlazaSemi();
					return retorno;
				}
			}	
			else {
				plazas.get(indexPlaza).setNivelDos(new Contenedor());
				plazas.get(indexPlaza).setEstPlazaSemi();
				return retorno;
			}
		}
		else if((plazas.get(indexPlaza).getNivelTres().getIdentificador())==identificador){
			Contenedor retorno=plazas.get(indexPlaza).getNivelTres();
			if	(retorno.getTecho()){
				plazas.get(indexPlaza).setNivelTres(plazas.get(indexPlaza).getNivelCuatro());
				plazas.get(indexPlaza).setNivelCuatro(new Contenedor());
				if(plazas.get(indexPlaza).getNivelTres().getTecho()) {
					plazas.get(indexPlaza).setEstPlazaSemi();
					return retorno;
				}
				else {
					plazas.get(indexPlaza).setEstPlazaLlena();
					return retorno;
				}
			}
			else {
				plazas.get(indexPlaza).setNivelCuatro(new Contenedor());
				plazas.get(indexPlaza).setEstPlazaSemi();
				return retorno;
			}
		}
		else if((plazas.get(indexPlaza).getNivelCuatro().getIdentificador())==identificador){
			Contenedor retorno=plazas.get(indexPlaza).getNivelCuatro();
			plazas.get(indexPlaza).setNivelCuatro(new Contenedor());
			plazas.get(indexPlaza).setEstPlazaSemi();
			return retorno;
		}
		else 
			return new Contenedor();		
	}
	public String estadoPlazas() {
		//TODO:RETURN EN FORMA DE STRING O MEJOR EN INT PARA OTRAS COSAS!!!!!!!!!!
		Iterator<Muelle> itrPlazas=plazas.iterator();
		int vacias=0;
		int semi=0;
		int llenas=0;
		while(itrPlazas.hasNext()) {
			Muelle plaza=itrPlazas.next();
			String estado = plaza.estadoPlaza;
			if (estado=="vacia")
				vacias++;
			else if (estado=="semillena")
				semi++;
			else
				llenas++;
		}
		return (vacias+"/"+semi+"/"+llenas);
	}

	
	
	/**
	 * 
	 * @param identificador
	 * @return
	 * @throws IllegalArgumentException
	 */
	public int getPlaza(String identificador)  {
		Contenedor identificadorCorrecto=new Contenedor(identificador,"500-Kg",200.0,"100-m3",true);
		int index=-1;
		for(int iterador=0;iterador<plazas.size();iterador++) {
			if((plazas.get(iterador).getNivelUno().getIdentificador())==identificador){
				index=iterador;
				break;
			}
			if((plazas.get(iterador).getNivelDos().getIdentificador())==identificador){
				index=iterador;
				break;
			}
			if((plazas.get(iterador).getNivelTres().getIdentificador())==identificador){
				index=iterador;
				break;
			}
			if((plazas.get(iterador).getNivelCuatro().getIdentificador())==identificador){
				index=iterador;
				break;
			}
		}
		return index;
		}
		//TODO: INDEX ==-1 NO SE ENCUENTRA creo que esta implementado en las clases que lo piden

	
	/**
	 * 
	 * @param identificador
	 * @return
	 * 
	 * @throws IllegalArgumentException
	 */
	public String getNivelPlaza(String identificador)  {
		//TODO:MEJORA Y NO RETORNAR TEXTO .../...
		int indexPlaza=getPlaza(identificador);
		String nivel;
		if (indexPlaza==-1){
			throw new IllegalArgumentException("El contenedor no se ha encontrado");
		}
		if((plazas.get(indexPlaza).getNivelUno().getIdentificador())==identificador){
			nivel="Ese contenedor se encuentra en la plaza "+indexPlaza+" y en el nivel 1";
			return nivel;
		}
		else if((plazas.get(indexPlaza).getNivelDos().getIdentificador())==identificador){
			nivel="Ese contenedor se encuentra en la plaza "+indexPlaza+" y en el nivel 2";
			return nivel;
		}
		else if((plazas.get(indexPlaza).getNivelTres().getIdentificador())==identificador){
			nivel="Ese contenedor se encuentra en la plaza "+indexPlaza+" y en el nivel 3";
			return nivel;
		}
		else if((plazas.get(indexPlaza).getNivelCuatro().getIdentificador())==identificador){
			nivel="Ese contenedor se encuentra en la plaza "+indexPlaza+" y en el nivel 4";
			return nivel;
		}
		else
			return ("Ese contenedor no se encuentra en ningún nivel");
	}


}
