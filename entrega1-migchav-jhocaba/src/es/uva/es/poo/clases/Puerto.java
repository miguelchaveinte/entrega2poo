package es.uva.es.poo.clases;

import es.uva.inf.poo.maps.GPSCoordinate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que proporciona la gestión de un puerto de una localidad
 * @author jhocaba
 * @author migchav
 */

public class Puerto {
	//Atributos
	
	private String pais;
	private String localidad;
	private List<Muelle> muelles;
	
	/**
	 * Inicializacion sin argumentos
	 */
	
	public Puerto() {
		
	}
	
	/**
	 * Inicializacion a partir de argumentos
	 * @param identidad - Define el los atributos localidad 
	 * y pais mediante el formato(PP-LLL) siendo PP el codigo de pais 
	 * y LLL el codigo de localidad- no puede ser null
	 * @throws IllegalArgumentException - cuando el argumento no cumple las condiciones PP-LLL
	 * @throws NullPointerException - cuando identidad==null
	 */
	public Puerto(String identidad) {
		if (identidad==null) throw new NullPointerException ("Identidad no puede ser nula");
		if(identidad.length()!=6)throw new IllegalArgumentException("Identidad no correcta");
		if (identidad.charAt(2)=='-') {
			String [] array = identidad.split("-");
			if(array[0].equals(array[0].toUpperCase()) && array[1].equals(array[1].toUpperCase())){
				this.pais = array[0];
				this.localidad = array[1];
				muelles=new ArrayList<Muelle>();
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
		return muelles;
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
	 * 
	 * @param IdMuelleToEliminar
	 * @throws IllegalArgumentException-si 
	 */
	public void eliminarMuelle(int IdMuelleToEliminar) {
		if(Integer.toString(IdMuelleToEliminar).length()!=2) throw new IllegalArgumentException("El identificador de muelle debe ser un número de 2 digitos");
		boolean eliminado=false;
		for (int i=0;i<muelles.size();i++) {
			if((muelles.get(i).getIdMuelle())==IdMuelleToEliminar){
				muelles.remove(i);
				eliminado=true;
				break;
			}
		}
		if (eliminado!=true) throw new IllegalArgumentException("El muelle no se ha eliminado porque no se ha encontrado");
	}
	/***
	 * 
	 * @return boolean completo=false... analizando todos los muelles del puerto esten o no operativos
	 */
	public boolean getCompleto() {
		boolean completo=false;
		Iterator<Muelle> itrMuelles=muelles.iterator();
		while(itrMuelles.hasNext()) {
			Muelle analisis=itrMuelles.next();
			//llama a string estado plaza???????????????
			String estado = analisis.estadoPlazas();
			String [] array = estado.split("/");
			if(array[0]!="0") {
				return completo;
			}
			else if(array[1]!="0"){
				return completo;
			}
			else {
				continue;
			}
		}
		completo=true;
		return completo;
	}
	
	public List<Muelle> muellesOperativos() {
		List<Muelle> lista=new ArrayList<Muelle>();
		Iterator<Muelle> itrMuelles=muelles.iterator();
		while(itrMuelles.hasNext()) {
			Muelle analisis=itrMuelles.next();
			boolean estado=analisis.getEstado();
			if (estado)
				lista.add(analisis);
		}
		return lista;
	}
	/**
	 * 
	 * @return lista muelles con espacio esten o no esten operativos
	 */
	public List<Muelle> muellesEspacio(){
		List<Muelle> lista=new ArrayList<Muelle>();
		Iterator<Muelle> itrMuelles=muelles.iterator();
		while(itrMuelles.hasNext()) {
			Muelle analisis=itrMuelles.next();
			String estado = analisis.estadoPlazas();
			String [] array = estado.split("/");
			if(array[0]!="0") {
				lista.add(analisis);
			}
			else if(array[1]!="0"){
				lista.add(analisis);
			}
			else {
				continue;
			}
		}
		return lista;
	}
	
	public List<Muelle> muellesCerca(GPSCoordinate punto,double distancia){
		if(punto==null) throw new IllegalArgumentException("La coordenada no puede ser null ");
		if(distancia<0) throw new IllegalArgumentException("La distacia no puede ser <0");
		List<Muelle> lista=new ArrayList<Muelle>();
		Iterator<Muelle> itrMuelles=muelles.iterator();
		while(itrMuelles.hasNext()) {
			Muelle analisis=itrMuelles.next();
			GPSCoordinate localizacion=analisis.getCoordenada();
			if (localizacion.getDistanceTo(punto)<distancia) {
				lista.add(analisis);
			}
		}
		return lista;
	}

	
	
}

