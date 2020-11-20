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
	 * Inicialización a partir de argumentos
	 * @param 
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
	public List<Muelle> getListaMuelles(){
		return muelles;
	}
	public String getLocalidad() {
		return localidad;
	}
	public String getPais() {
		return pais;
	}
	/**
	 * 
	 * @param añadir
	 * @throws IllegalArgumentException
	 */
	public void añadirMuelle(Muelle añadir) {
		//añadir.setPuertoPertenece(this.Puerto);
		if(añadir==null)throw new IllegalArgumentException("El muelle no puede ser vacio");
		muelles.add(añadir);
			
	}
	
	/**
	 * 
	 * @param eliminar
	 * @throws IllegalArgumentException
	 */
	public void eliminarMuelle(int eliminar) {
		if(Integer.toString(eliminar).length()!=2) throw new IllegalArgumentException("El identificador de muelle debe ser un número de 2 digitos");
		boolean eliminado=false;
		for (int i=0;i<muelles.size();i++) {
			if((muelles.get(i).getIdMuelle())==eliminar){
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

