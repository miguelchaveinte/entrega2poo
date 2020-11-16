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
	private Contenedor nivel1;
	private Contenedor nivel2;
	private Contenedor nivel3;
	private Contenedor nivel4;
	private String estadoPlaza;

	/**
	 * Constructor sin argumentos (tiene sentido uno sin argumentos?????)
	 */
	public Muelle(){
		
	}
	/**
	 * Inicializador de muelle con parametros
	 * @param identificador
	 * @param coordenada
	 * @param estado
	 * @param numPlazas
	 * @throws Exception 
	 */
	
	//TODO: THROWS EXCEPTION SI QUE NO SE AJUSTA
	public Muelle(int identificador,GPSCoordinate coordenada,char estado,int numPlazas) throws Exception {
		if(Integer.toString(identificador).length()==2 && coordenada!=null) {
			this.identificador=identificador;
			this.coordenada=coordenada;
			this.numPlazas=numPlazas;
			setEstado(estado);	
			setPlazas(numPlazas);
		}
		else {
			throw new IllegalArgumentException("El identificador de muelle debe ser un número de 2 digitos y la coordenada no nula");
		}
	}

	public void setEstado(char estado) throws Exception {
		if (estado!='O' && estado!='F') {
			throw new Exception("Estado no valido");
		}
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
	
	public String getEstadoPlaza() {
		return estadoPlaza;
	}
	/**
	 * Guardamos un arraylist de las plazas
	 * @param numPlazas
	 * @throws Exception
	 */
	public void setPlazas(int numPlazas) throws Exception {
		if(numPlazas<=0) {
			throw new Exception("El número de plazas no puede ser menor o igual a 0");
		}
		plazas=new ArrayList<Muelle>();
		for(int i=0;i<numPlazas;i++) {
			Muelle descripcionPlazas=new Muelle();
			descripcionPlazas.setNivel1(new Contenedor());
			descripcionPlazas.setNivel2(new Contenedor());
			descripcionPlazas.setNivel3(new Contenedor());
			descripcionPlazas.setNivel4(new Contenedor());
			descripcionPlazas.setEstadoPlaza("Vacia");
			plazas.add(descripcionPlazas);
		}
	}
	
	public GPSCoordinate getCoordenada() {
		return coordenada;
	}
	//TODO:PARA CADA TIPO DE ESTADO UN METODOXXX
	public void setEstadoPlaza(String estado) {
		estadoPlaza=estado;
	}
	public void setNivel1(Contenedor contenedor){
		//PUEDE ADMITIR NULLS
		nivel1=contenedor;
	}
	public void setNivel2(Contenedor contenedor){
		//PUEDE ADMITIR NULLS
		nivel2=contenedor;
	}
	public void setNivel3(Contenedor contenedor){
		//PUEDE ADMITIR NULLS
		nivel3=contenedor;
	}
	public void setNivel4(Contenedor contenedor){
		//PUEDE ADMITIR NULLS
		nivel4=contenedor;
	}
	public void asignarPlaza(Contenedor contenedor,int plaza) {
		if (contenedor==null || plaza<0) {
			throw new IllegalArgumentException("El contenedor no puede ser vacio ni la plaza<0");
		}
		String estado=plazas.get(plaza).estadoPlaza;
		if (estado=="Llena") {
			for(int iterador=0;iterador<plazas.size();iterador++) {
				if(plazas.get(iterador).estadoPlaza.equals("Semi-llena")){
					asignarPlaza(contenedor,iterador);
				}
				if(plazas.get(iterador).estadoPlaza.equals("Vacia")) {
					asignarPlaza(contenedor,iterador);
				}
			}
		}
		else {
			if(estado=="Semi-llena"){
				if(plazas.get(plaza).nivel2.getIdentificador(plazas.get(plaza).nivel2)==null) {
					plazas.get(plaza).setNivel2(contenedor);
				}
				else {
					if (plazas.get(plaza).nivel3.getIdentificador(plazas.get(plaza).nivel3)==null) {
						plazas.get(plaza).setNivel3(contenedor);
					}
					else {
						plazas.get(plaza).setNivel4(contenedor);
						plazas.get(plaza).setEstadoPlaza("Llena");
					}
				}
			}
			else {//vacia
				plazas.get(plaza).setNivel1(contenedor);
				if(contenedor.getTecho(contenedor)) {
					plazas.get(plaza).setEstadoPlaza("Semi-llena");
				}
				else {
					plazas.get(plaza).setEstadoPlaza("Llena");
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
	 * @throws Exception 
	 */
	public Contenedor sacarContenedor(String identificador) throws Exception {
		//TODO: ELSE IF -> Y SI NO COINCIDE CON NINGUNO ELSE Y RETURN new Contenedor() o return null??;
		int indexPlaza=getPlaza(identificador);
		if (indexPlaza==-1){
			throw new Exception("El contenedor no se ha encontrado");
		}
		if((plazas.get(indexPlaza).nivel1.getIdentificador(plazas.get(indexPlaza).nivel1)).equals(identificador)){
			Contenedor retorno=plazas.get(indexPlaza).nivel1;
			if	(retorno.getTecho(retorno)){
				plazas.get(indexPlaza).setNivel1(plazas.get(indexPlaza).nivel2);
				plazas.get(indexPlaza).setNivel2(plazas.get(indexPlaza).nivel3);
				plazas.get(indexPlaza).setNivel3(plazas.get(indexPlaza).nivel4);
				plazas.get(indexPlaza).setNivel4(new Contenedor());
				if (plazas.get(indexPlaza).nivel1.getIdentificador(plazas.get(indexPlaza).nivel1)==null) {
					plazas.get(indexPlaza).setEstadoPlaza("Vacia");
					return retorno;
				}
				else {
					if (plazas.get(indexPlaza).nivel2.getIdentificador(plazas.get(indexPlaza).nivel2)==null) {
						if(plazas.get(indexPlaza).nivel1.getTecho(plazas.get(indexPlaza).nivel1)) {
							plazas.get(indexPlaza).setEstadoPlaza("Semi-llena");
							return retorno;
						}
						else {
							plazas.get(indexPlaza).setEstadoPlaza("Llena");
							return retorno;
						}
					}
					else if (plazas.get(indexPlaza).nivel3.getIdentificador(plazas.get(indexPlaza).nivel3)==null) {
						if(plazas.get(indexPlaza).nivel2.getTecho(plazas.get(indexPlaza).nivel2)) {
							plazas.get(indexPlaza).setEstadoPlaza("Semi-llena");
							return retorno;
						}
						else {
							plazas.get(indexPlaza).setEstadoPlaza("Llena");
							return retorno;
						}
					}
					else {
						if(plazas.get(indexPlaza).nivel3.getTecho(plazas.get(indexPlaza).nivel3)) {
							plazas.get(indexPlaza).setEstadoPlaza("Semi-llena");
							return retorno;
						}
						else {
							plazas.get(indexPlaza).setEstadoPlaza("Llena");
							return retorno;
						}
					}
				}
			}
			else {
				plazas.get(indexPlaza).setNivel1(new Contenedor());
				plazas.get(indexPlaza).setEstadoPlaza("Vacia");
				return retorno;
			}
		}
		else if((plazas.get(indexPlaza).nivel2.getIdentificador(plazas.get(indexPlaza).nivel2)).equals(identificador)){
			Contenedor retorno=plazas.get(indexPlaza).nivel2;
			if	(retorno.getTecho(retorno)){
				plazas.get(indexPlaza).setNivel2(plazas.get(indexPlaza).nivel3);
				plazas.get(indexPlaza).setNivel3(plazas.get(indexPlaza).nivel4);
				plazas.get(indexPlaza).setNivel4(new Contenedor());
				if (plazas.get(indexPlaza).nivel2.getIdentificador(plazas.get(indexPlaza).nivel2)==null) {
					plazas.get(indexPlaza).setEstadoPlaza("Semi-llena");
					return retorno;
				}
				else if (plazas.get(indexPlaza).nivel3.getIdentificador(plazas.get(indexPlaza).nivel3)==null) {
					if(plazas.get(indexPlaza).nivel2.getTecho(plazas.get(indexPlaza).nivel2)) {
						plazas.get(indexPlaza).setEstadoPlaza("Semi-llena");
						return retorno;
					}
					else {
						plazas.get(indexPlaza).setEstadoPlaza("Llena");
						return retorno;
					}
				}
				else {
					plazas.get(indexPlaza).setEstadoPlaza("Semi-llena");
					return retorno;
				}
			}	
			else {
				plazas.get(indexPlaza).setNivel2(new Contenedor());
				plazas.get(indexPlaza).setEstadoPlaza("Semi-llena");
				return retorno;
			}
		}
		else if((plazas.get(indexPlaza).nivel3.getIdentificador(plazas.get(indexPlaza).nivel3)).equals(identificador)){
			Contenedor retorno=plazas.get(indexPlaza).nivel3;
			if	(retorno.getTecho(retorno)){
				plazas.get(indexPlaza).setNivel3(plazas.get(indexPlaza).nivel4);
				plazas.get(indexPlaza).setNivel4(new Contenedor());
				if(plazas.get(indexPlaza).nivel3.getTecho(plazas.get(indexPlaza).nivel3)) {
					plazas.get(indexPlaza).setEstadoPlaza("Semi-llena");
					return retorno;
				}
				else {
					plazas.get(indexPlaza).setEstadoPlaza("Llena");
					return retorno;
				}
			}
			else {
				plazas.get(indexPlaza).setNivel4(new Contenedor());
				plazas.get(indexPlaza).setEstadoPlaza("Semi-llena");
				return retorno;
			}
		}
		else if((plazas.get(indexPlaza).nivel4.getIdentificador(plazas.get(indexPlaza).nivel4)).equals(identificador)){
			Contenedor retorno=plazas.get(indexPlaza).nivel4;
			plazas.get(indexPlaza).setNivel4(new Contenedor());
			plazas.get(indexPlaza).setEstadoPlaza("Semi-llena");
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
			if (estado=="Vacia")
				vacias++;
			else if (estado=="Semi-llena")
				semi++;
			else
				llenas++;
		}
		return (vacias+"/"+semi+"/"+llenas);
	}

	
	//TODO:los puedo quitar??????????' solo lo utilizo ahora en estadoPlaza() y se va a modificar!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	/**
	 * 
	 * 
	public Contenedor getNivel1() {
		// TODO Auto-generated method stub
		return null;
	}
	public Contenedor getNivel2() {
		// TODO Auto-generated method stub
		return null;
	}
	public Contenedor getNivel3() {
		// TODO Auto-generated method stub
		return null;
	}
	public Contenedor getNivel4() {
		// TODO Auto-generated method stub
		return null;
	}
	 */
	public static boolean comprobarIdentificador(String identificador) {
		boolean correcto=true;
		if(identificador.length()!=11) {
			correcto=false;
			return correcto;
		}
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
			return correcto;
		}
		else {
			correcto=false;
			return correcto;
		}
	}
	public int getPlaza(String identificador) throws Exception {
		boolean correcto=comprobarIdentificador(identificador);
		if (correcto){
			int index=-1;
			for(int iterador=0;iterador<plazas.size();iterador++) {
				if((plazas.get(iterador).nivel1.getIdentificador(plazas.get(iterador).nivel1)).equals(identificador)){
					index=iterador;
					break;
				}
				if((plazas.get(iterador).nivel2.getIdentificador(plazas.get(iterador).nivel2)).equals(identificador)){
					index=iterador;
					break;
				}
				if((plazas.get(iterador).nivel3.getIdentificador(plazas.get(iterador).nivel3)).equals(identificador)){
					index=iterador;
					break;
				}
				if((plazas.get(iterador).nivel4.getIdentificador(plazas.get(iterador).nivel4)).equals(identificador)){
					index=iterador;
					break;
				}
			}
			return index;
		}
		else
			throw new Exception("Identificador no valido");
	}
	public String getNivelPlaza(String identificador) throws Exception {
		//TODO:MEJORA Y NO RETORNAR TEXTO .../...
		int indexPlaza=getPlaza(identificador);
		String nivel;
		if (indexPlaza==-1){
			throw new Exception("El contenedor no se ha encontrado");
		}
		if((plazas.get(indexPlaza).nivel1.getIdentificador(plazas.get(indexPlaza).nivel1)).equals(identificador)){
			nivel="Ese contenedor se encuentra en la plaza "+indexPlaza+" y en el nivel 1";
			return nivel;
		}
		else if((plazas.get(indexPlaza).nivel2.getIdentificador(plazas.get(indexPlaza).nivel2)).equals(identificador)){
			nivel="Ese contenedor se encuentra en la plaza "+indexPlaza+" y en el nivel 2";
			return nivel;
		}
		else if((plazas.get(indexPlaza).nivel3.getIdentificador(plazas.get(indexPlaza).nivel3)).equals(identificador)){
			nivel="Ese contenedor se encuentra en la plaza "+indexPlaza+" y en el nivel 3";
			return nivel;
		}
		else if((plazas.get(indexPlaza).nivel4.getIdentificador(plazas.get(indexPlaza).nivel4)).equals(identificador)){
			nivel="Ese contenedor se encuentra en la plaza "+indexPlaza+" y en el nivel 4";
			return nivel;
		}
		else
			return ("Ese contenedor no se encuentra en ningún nivel");
	}


}
