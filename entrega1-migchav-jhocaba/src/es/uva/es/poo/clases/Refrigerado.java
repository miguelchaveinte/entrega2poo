package es.uva.es.poo.clases;

import com.rits.cloning.Cloner;

/**
 * Clase que implementa el modelo de Contenedor Refrigernado,el cual mantiene la temperatura 
 * de las mercancias y tiene un pequeño motor integrada que debe ser conenectado a las 
 * instalaciones del muelle. Este mantiene las características de {@link Contenedor} 
 * y la diferencia de los otros modelos es que este solo puede ser transportado por camión 
 * o barco (codigoTransporte:{1,0,1}), ocupa un nivel de una plaza y tiene techo.
 * @see Contenedor
 * @author migchav,jhocaba
 *
 */

public class Refrigerado extends Contenedor {
	private int espacio;
	private int[] codigoTransporte;
	/**
	 * Crea un nuevo contenedor Estandar.
	 * @param identificador La cadena con el que se identifica cada contenedor.(Caracterizada por el codigo del dueño (3 letras mayusculas),
	 * una letra(U,J o Z), que indica el equipamiento,un número de serie de 6 dígitos, un dígito de control obtenido de un algoritmo.
	 * @param peso El numero de peso de la tara
	 * @param unidPeso La unidad del @param peso que puede ser Kg(La k en mayusculas - kilogramos) o lb (libras)
	 * @param carga La máxima carga útil permitida
	 * @param volumen El volumen del contenedor
	 * @param unidVol La unidad del @param volumen que puede ser m3(metros cubicos) o ft3(pies cubicos)
	 * @throws IllegalArgumentException Si el digito de control del identificador sea diferente del obtenido
	 * @throws IllegalArgumentException Si la carga<0
	 * @throws IllegalArgumentException Si el @param identificador no cumple los requisitos expuestos
	 * @throws IllegalArgumentException Si el peso<0
	 * @throws IllegalArgumentException Si el volumen<0
	 * @throws IllegalArgumentException Si la unidad de peso no son las expuestas
	 * @throws IllegalArgumentException Si la unidad de volumen no son las expuestas
	 * @see Contenedor#Contenedor(String, double, String, double, double, String, boolean)
	 * @see Contenedor#comprobarIdentificador(String)
	 * @see Contenedor#obtenerDigitoControl(String)
	 * @see Contenedor#comprobarUnidadesPeso(double, String)
	 * @see Contenedor#comprobarUnidadesVolumen(double, String)
	 * @see Contenedor#setTransito()
	 * @see Contenedor#setTecho()
	 */
	public Refrigerado(String identificador,double peso ,String unidPeso,double carga,double volumen,String unidVol) {
		super(identificador,peso,unidPeso,carga, volumen,unidVol,true);
		espacio=1;
		codigoTransporte=new int [] {1,0,1};
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getEspacio() {
		return espacio;
	}
	
	/**
	 * {@inheritDoc}
	 * @see <a href="https://github.com/kostaskougios/cloning">Cloning Library</a>
	 */
	@Override
	public int[] getCodigoTransporte() {
		Cloner cloner=new Cloner();
		return cloner.deepClone(codigoTransporte);
	}
}
