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
		String [] array = identidad.split("-");
		this.pais = array[0];
		this.localidad = array[1];
		muelles=new ArrayList<Muelle>();
	}
	public String getLocalidad() {
		return localidad;
	}
	public String getPais() {
		return pais;
	}
	public void añadirMuelle(Muelle añadir) {
		muelles.add(añadir);
	}
	public void eliminarMuelle(int eliminar) {
		for (int i=0;i<muelles.size();i++) {
			if((muelles.get(i).getIdMuelle())==eliminar){
				muelles.remove(i);
			}
		}
	}
	
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
