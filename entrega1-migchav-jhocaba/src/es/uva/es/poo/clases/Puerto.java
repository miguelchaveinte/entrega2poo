package es.uva.es.poo.clases;
<<<<<<< HEAD
/**
 * 
 * @author migchav
 *
 */
=======
import java.util.ArrayList;

/**
 * Clase que proporciona la gesti�n de un puerto de una localidad
 * @author jhocaba
 * @author migchav
 */

>>>>>>> branch 'master' of https://gitlab.inf.uva.es/migchav/entrega1-migchav-jhocaba.git
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
