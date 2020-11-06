package es.uva.es.poo.clases;

import java.util.ArrayList;

/**
 * Clase que proporciona la gesti�n de un puerto de una localidad
 * @author jhocaba
 * @author migchav
 */

public class Puerto {
	//Atributos
	
	private String pa�s;
	private String localidad;
	
	/**
	 * Inicializacion sin argumentos
	 */
	
	public Puerto() {
		
	}
	
	/**
	 * Inicializaci�n a partir de argumentos
	 * @param 
	 */
	public Puerto(String identidad) {
		String [] array = identidad.split("-");
		this.pa�s = array[0];
		this.localidad = array[1];
	}
	
	
	
	
	
	
}
