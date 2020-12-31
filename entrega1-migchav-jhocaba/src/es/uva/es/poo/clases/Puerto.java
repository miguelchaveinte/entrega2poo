package es.uva.es.poo.clases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rits.cloning.Cloner;

import es.uva.inf.poo.maps.GPSCoordinate;



/**
 * Clase que proporciona la gestión de un puerto de una localidad. Los servicios que 
 * proporciona dicha clase van desde la propia creacion de este,añadir/eliminar muelles
 * a dicho puerto,ofrecer la lista de los muelles a su cargo, de los muelles operativos o
 * que tengan aún espacio para almacenar contenedores o los que estan en una ubicacion cercana.
 * @author jhocaba
 * @author migchav
 */

public class Puerto  {
	//Atributos
	
	private String pais;
	private String localidad;
	private List<Muelle> muelles;
		
	/**
	 * Crea un nuevo puerto con un identificador distinguido.
	 * @param identidad El argumento identificativo del puerto que codifica el pais y la 
	 * localiad mediante el formato(PP-LLL) siendo PP el codigo de pais 
	 * y LLL el codigo de localidad - no puede ser null
	 * @throws IllegalArgumentException Si el identificador no cumple las condiciones PP-LLL
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
	 * Devuelve la lista clonada en profundidad de todos los muelles gestionados por el puerto.
	 * @see <a href="https://github.com/kostaskougios/cloning">Cloning Library</a>
	 * @return Lista de muelles gestionados por el puerte clonada en profundidad.
	 */
	public List<Muelle> getListaMuelles(){
		Cloner cloner=new Cloner();
		return cloner.deepClone(muelles);
	}
	
	/**
	 * Dado un muelle por @param muelleContenido indica si este se encuentra bajo
	 * la gestion del puerto
	 * @param muelleContenido El muelle a analizar si esta entre los que gestiona el puerto
	 * @see List#contains(Object)
	 * @return true si el puerto gestiona a @param muelleContenido. Por el contrario retorna false.
	 * @throws Si @param muelleContenido==null 
	 */
	public boolean puertoContainsMuelle(Muelle muelleContenido) {
		if(muelleContenido==null)throw new IllegalArgumentException("El muelle no puede ser nulo");
		return muelles.contains(muelleContenido);
	}
	/**
	 * Devuelve el String correspondiente al código localidad 
	 * @return localidad El codigo sacado del identificador 
	 */
	public String getLocalidad() {
		return localidad;
	}
	/**
	 * Devuelve el string correspondiente al codigo del pais 
	 * @return pais El codigo sacado del identificador
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Incluye en la gestion del puerto el @param muelleToAdd si este no es null ni está 
	 * ya gestionado por este puerto.
	 * @param muelleToAdd El muelle a incluir - no puede ser null
	 * @throws IllegalArgumentException Si @param muelleToAdd==null
	 * @throws IllegalArgumentException Si @param muelleToAdd ya esta siendo gestionado
	 * por este puerto. 
	 * @see Puerto#puertoContainsMuelle(Muelle)
	 */
	public void addMuelle(Muelle muelleToAdd) {
		if(muelleToAdd==null)throw new IllegalArgumentException("El muelle no puede ser vacio");
		if(puertoContainsMuelle(muelleToAdd))throw new IllegalArgumentException("El muelle ya esta en el puerto");
		muelles.add(muelleToAdd);
			
	}
	
	/**
	 * Elimina un muelle de la gestion del puerto dado el identificador del muelle
	 * pero si y solo si este no contiene contenedores.
	 * @param IdMuelleToEliminar Identificador de dos digitos del muelle
	 * @throws IllegalArgumentException Si @param IdMuelleToEliminar no es de dos digitos
	 * @throws IllegalArgumentException Si @parm IdMuelleToEliminar no esta en el puerto
	 * @throws IllegalArgumentException Si el muelle con @param IdMuelleToEliminar no esta vacio
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
	 * Devuelve un boolean que nos indica si el puerto no tienes más 
	 * capacidad de almacenar contenedores.
	 * @return completo-Retorna false Si una vez analizado todos los muelles
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
			if(array[0]!="0" || array[1]!="0") {
				completo=false;
			}
		}
		return completo;
	}
	/**
	 * Analiza todos los muelles que estan gestionados por el puerto y si estan 
	 * operativos los introduce clonados en profundidas en una lista 
	 * que es la que devolvemos
	 * @return lista-La lista de muelles que cumplen los requisitos
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
	 * Analiza todos los muelles que estan gestionados por el puerto y si estan
	 * con espacio disponible para almacenar algún contenedor más.Si cumple estos 
	 * requisitos los introducimos en una nueva lista que es la se devuelve 
	 * previamente clonados en profundidad  
	 * @return lista- La lista de muelles que cumplen los requisitos
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
			if(array[0]!="0" || array[1]!="0") {
				lista.add(copia);
			}
		}
		return lista;
	}
	/**
	 * Devuelve una lista de los muelles esten o no operativos que se encuentran
	 * a una distancia menor de una cierta coordenada GPS y una cierta distancia.
	 * @param puntoGps La coordenada GPS desde la cual observaremos la distancia a medir
	 * @param distancia La distancia de la cual la distancia de los 
	 * muelles a @param puntoGps debe ser menor.
	 * @return lista - La lista de muelles cuya distancia a @param puntoGps sea < @param distancia
	 * @see es.uva.inf.poo.maps.GPSCoordinate#getDistanceTo(GPSCoordinate)
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

