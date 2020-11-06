package es.uva.es.poo.clases;

import es.uva.inf.poo.maps.GPSCoordinate;
import es.uva.es.poo.clases.Contenedor;
import java.lang.*; 
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que almacena los datos relativos a los muelles
 * @author jhocaba
 * @author migchav
 *
 */
public class Muelle {
	
	private int identificador;
	private GPSCoordinate muelle;
	private boolean estado;	
	private int numPlazas;
	private List<Contenedor> plazas;
	private Contenedor nivel1;
	private Contenedor nivel2;
	private Contenedor nivel3;
	private Contenedor nivel4;

	/**
	 * Constructor sin argumentos
	 */
	public Muelle(){
		
	}
	
	public Muelle(int identificador,GPSCoordinate muelle,char estado,int numPlazas) {
		this.identificador=identificador;
		this.muelle=muelle;
		this.numPlazas=numPlazas;
		setEstado(estado);	
		setPlazas(numPlazas);
	}

	public void setEstado(char estado) {
		if (estado =='O') {
				this.estado=true;
		}
		else {
			this.estado=false;
		}
	}
	public boolean getEstado() {
		return estado;
	}
	
	public int getNumPlazas() {
		return numPlazas;
	}
	public String getEstadoPlaza() {
		//TODO: ANALISIS DEL ARRAY LIST
	}
	public void setPlazas(int numPlazas) {
		plazas=new ArrayList<Contenedor>();
		for(int i=0;i<numPlazas;i++) {
			Muelle descripcionPlazas=new Muelle();
			descripcionPlazas.setNivel1(Contenedor());
			descripcionPlazas.setNivel2(Contenedor());
			descripcionPlazas.setNivel3(Contenedor());
			descripcionPlazas.setNivel4(Contenedor());
			plazas.add(descripcionPlazas);
		}
	}
	public void setNivel1(Contenedor contenedor){
		//si es null????? IGUAL CREO
		//TODO: OBTENER IDENTIFICADOR
	}
}
