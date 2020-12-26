package es.uva.es.poo.clases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rits.cloning.Cloner;

import es.uva.inf.poo.maps.GPSCoordinate;



/**
 * Clase que proporciona la gestión de un puerto de una localidad
 * @author jhocaba
 * @author migchav
 */

public class Puerto  {
	//Atributos
	
	private String pais;
	private String localidad;
	private List<Muelle> muelles;
		
	/**
	 * Inicializacion a partir de argumentos
	 * @param identidad - Define el los atributos localidad 
	 * y pais mediante el formato(PP-LLL) siendo PP el codigo de pais 
	 * y LLL el codigo de localidad- no puede ser null
	 * @throws IllegalArgumentException - cuando el argumento no cumple las condiciones PP-LLL
	 * @throws NullPointerException - cuando identidad==null
	 */
	public Puerto(String identidad) {
		if(identidad.length()!=6)throw new IllegalArgumentException("Identidad no correcta");
		if (identidad.charAt(2)=='-') {
			String [] array = identidad.split("-");
			if(array[0].equals(array[0].toUpperCase()) && array[1].equals(array[1].toUpperCase())){
				this.pais = array[0];
				this.localidad = array[1];
				muelles=new ArrayList<>();
			}
			else
				throw new IllegalArgumentException("La identidad del puerto(país y localidad) debe ser en mayusculas");
		}
		else 
			throw new IllegalArgumentException("Identidad no correcta(no contiene: - )");
	}
	/**
	 * Devuelve la lista de todos los muelles del puerto
	 * @return lista de Muelles del puerto
	 */
	public List<Muelle> getListaMuelles(){
		Cloner cloner=new Cloner();
		List<Muelle> listaClonada=cloner.deepClone(muelles);
		return listaClonada;
	}
	/**
	 * Devuelve el string correspondiente al codigo localidad 
	 * @return codigo localidad
	 */
	public String getLocalidad() {
		return localidad;
	}
	/**
	 * Devuelve el string correspondiente al codigo del pais 
	 * @return codigo pais
	 */
	public String getPais() {
		return pais;
	}
	/**
	 * Incluye en el puerto el muelle que se pasa por argumento
	 * @param muelleToAdd - no puede ser null
	 * @throws IllegalArgumentException-si muelleToAdd es null
	 */
	public void addMuelle(Muelle muelleToAdd) {
		if(muelleToAdd==null)throw new IllegalArgumentException("El muelle no puede ser vacio");
		muelles.add(muelleToAdd);
			
	}
	
	/**
	 * Elimina un muelle del puerto pero solo si este no contiene contenedores
	 * @param IdMuelleToEliminar-un int de dos digitos
	 * @throws IllegalArgumentException-si IdMuelleToEliminar no es de dos digitos
	 * @throws IllegalArgumentException-si IdMuelleToEliminar no esta en el puerto
	 * @throws IllegalArgumentException -si el muelle no esta vacio
	 */
	public void eliminarMuelle(int idMuelleToEliminar) {
		if(Integer.toString(idMuelleToEliminar).length()!=2) throw new IllegalArgumentException("El identificador de muelle debe ser un número de 2 digitos");
		boolean eliminado=false;
		int i=0;
		while(i<muelles.size() && !eliminado) {
			if((muelles.get(i).getIdMuelle())==idMuelleToEliminar){	
				if(muelles.get(i).estadoPlazas().equals(String.valueOf(muelles.get(i).getNumPlazas())+"/0/0")) {
					muelles.remove(i);
					eliminado=true;
				}
				else throw new IllegalArgumentException("El muelle no esta vacio-tiene contenedores");
			}
			i++;
		}
		if (!eliminado) throw new IllegalArgumentException("El muelle no se ha eliminado porque no se ha encontrado");
	}
	/***
	 * Devuelve un boolean que nos indica si el puerto no tienes mas 
	 * capacidad de almacenar muelles
	 * @return boolean completo=> false -si una vez analizado todos los muelles
	 * del puerton(tanto operativos como no operativos) sigue habiendo espacio. 
	 * Sino es que esta completo y retorna true
	 */
	public boolean getCompleto() {
		boolean completo=true;
		Iterator<Muelle> itrMuelles=muelles.iterator();
		while(itrMuelles.hasNext() && completo) {
			Muelle analisis=itrMuelles.next();
			String estado = analisis.estadoPlazas();
			String [] array = estado.split("/");
			if(array[0]!="0") {
				completo=false;
			}
			else if(array[1]!="0"){
				completo=false;
			}
		}
		return completo;
	}
	/**
	 * Analiza todos los muelles que estan en el puerto y si estan 
	 * operativos los introduce una lista que es la que nos devuelve
	 * @return lista de Muelles operativos
	 */
	public List<Muelle> muellesOperativos() {
		List<Muelle> lista=new ArrayList<>();
		Iterator<Muelle> itrMuelles=muelles.iterator();
		while(itrMuelles.hasNext()) {
			Cloner cloner=new Cloner();
			Muelle analisis=itrMuelles.next();
			Muelle copia=cloner.deepClone(analisis);
			boolean estado=analisis.getEstado();
			if (estado)
				lista.add(copia);
		}
		return lista;
	}
	/**
	 * Analiza todos los muelles que estan en el puerto y si estan
	 * con espacio disponible para almacenar algun contenedor mas
	 * los introduce en una lista que es la que nos devuelve.
	 * @return lista de muelles con espacio esten o no esten operativos
	 */
	public List<Muelle> muellesEspacio(){
		Cloner cloner=new Cloner();
		List<Muelle> lista=new ArrayList<>();
		Iterator<Muelle> itrMuelles=muelles.iterator();
		while(itrMuelles.hasNext()) {
			Muelle analisis=itrMuelles.next();
			Muelle copia=cloner.deepClone(analisis);
			String estado = analisis.estadoPlazas();
			String [] array = estado.split("/");
			if(array[0]!="0") {
				lista.add(copia);
			}
			else if(array[1]!="0"){
				lista.add(copia);
			}
		}
		return lista;
	}
	/**
	 * Devuelve una lista de los muelles esten o no operativos que se encuentran
	 * a una distancia menor de una cierta coordenada GPS
	 * @param puntoGps - coordenada GPS desde la cual observaremos la distancia a medir
	 * @param distancia - double de la distancia de la cual la distancia de los 
	 * muelles debe ser menor.
	 * @return lista de los muelles a una distancia menor del {@param distancia}
	 * del punto GPS {@param puntoGps}
	 */
	public List<Muelle> muellesCerca(GPSCoordinate puntoGps,double distancia){
		if(puntoGps==null) throw new IllegalArgumentException("La coordenada no puede ser null ");
		if(distancia<0) throw new IllegalArgumentException("La distacia no puede ser <0");
		Cloner cloner=new Cloner();
		List<Muelle> lista=new ArrayList<>();
		Iterator<Muelle> itrMuelles=muelles.iterator();
		while(itrMuelles.hasNext()) {
			Muelle analisis=itrMuelles.next();
			Muelle copia=cloner.deepClone(analisis);
			GPSCoordinate localizacion=analisis.getCoordenada();
			if (localizacion.getDistanceTo(puntoGps)<distancia) {
				lista.add(copia);
			}
		}
		return lista;
	}

	
	
}

