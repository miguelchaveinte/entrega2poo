package es.uva.es.poo.clases;

import java.util.ArrayList;

/**
 * Clase que proporciona la gestión de un puerto de una localidad
 * @author jhocaba
 * @author migchav
 */

public class Puerto {
	//Atributos
	
	private String país;
	private String localidad;
	
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
		this.país = array[0];
		this.localidad = array[1];
	}
	
	
	
	
	
	
}
