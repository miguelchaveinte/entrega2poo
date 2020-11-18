package es.uva.es.poo.pruebas;

import es.uva.es.poo.clases.*;
import es.uva.inf.poo.maps.GPSCoordinate;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;

public class PuertoTest {

	@Test
	public void testConstructor() {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		
		assertNotNull(prueba);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorMasLongitud() {
		String identidad="ES-MADD";
		Puerto prueba=new Puerto(identidad);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorMenosLongitud() {
		String identidad="ES-MA";
		Puerto prueba=new Puerto(identidad);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorMinusculaPais() {
		String identidad="Es-MAD";
		Puerto prueba=new Puerto(identidad);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorMinusculaLocalidad() {
		String identidad="ES-MAd";
		Puerto prueba=new Puerto(identidad);
	}
	
	@Test(expected=NullPointerException.class) 
	public void testConstructorNull() {
		String identidad=null;
		Puerto prueba=new Puerto(identidad);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testConstructorSinGuion() {
		String identidad="ESMADD";
		Puerto prueba=new Puerto(identidad);
	}
	@Test
	public void getLocalidad() {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		
		assertEquals(prueba.getLocalidad(),"MAD");
	}
	@Test
	public void getPais() {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		
		assertEquals(prueba.getPais(),"ES");
	}
	@Test
	public void a�adirMuelle() throws Exception {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(12,coordenada,'O',50);
		prueba.a�adirMuelle(a�adir);
		
		assertEquals(prueba.muellesOperativos().get(0),a�adir);
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testa�adirMuelleNull() {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		Muelle a�adir=null;
		prueba.a�adirMuelle(a�adir);
	}
	@Test
	public void eliminarMuelle() throws Exception {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(12,coordenada,'O',50);
		prueba.a�adirMuelle(a�adir);
		prueba.eliminarMuelle(20);
		prueba.eliminarMuelle(12);	
		assertTrue(prueba.muellesOperativos().isEmpty());
	}
	@Test(expected=IllegalArgumentException.class) 
	public void eliminarMuelleIdentidadNoValida() throws Exception {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(12,coordenada,'O',50);
		prueba.a�adirMuelle(a�adir);
		prueba.eliminarMuelle(100);
	}
	@Test
	public void getCompleto() throws Exception {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(12,coordenada,'O',50);
		prueba.a�adirMuelle(a�adir);
		boolean completo=prueba.getCompleto();
		prueba.eliminarMuelle(12);
		boolean completo2=prueba.getCompleto();
		assertFalse(completo);
		assertTrue(completo2);
	}
	
	@Test
	public void muellesOperativos() throws Exception {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(12,coordenada,'O',50);
		prueba.a�adirMuelle(a�adir);
		assertFalse(prueba.muellesOperativos().isEmpty());
		prueba.eliminarMuelle(12);
		Muelle a�adir2=new Muelle(12,coordenada,'F',50);
		prueba.a�adirMuelle(a�adir2);
		assertTrue(prueba.muellesOperativos().isEmpty());
	}
	@Test
	public void muellesEspacio() throws Exception {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(12,coordenada,'O',50);
		prueba.a�adirMuelle(a�adir);
		assertFalse(prueba.muellesEspacio().isEmpty());
		prueba.eliminarMuelle(12);
		assertTrue(prueba.muellesEspacio().isEmpty());
	}
	
	@Test
	public void muellesCerca() throws Exception {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(12,coordenada,'O',50);
		prueba.a�adirMuelle(a�adir);
		GPSCoordinate coordenadaDistancia=new GPSCoordinate(40.0,40.0);
		GPSCoordinate coordenadaDistancia2=new GPSCoordinate(40.5,40.5);
		double distancia=4000.0;
		assertFalse(prueba.muellesCerca(coordenadaDistancia, distancia).isEmpty());
		assertFalse(prueba.muellesCerca(coordenadaDistancia2, distancia).isEmpty());
		double distanciaNo=25.0;
		assertTrue(prueba.muellesCerca(coordenadaDistancia, distanciaNo).isEmpty());
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void muellesCercaCoordenadaNull() throws Exception {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(12,coordenada,'O',50);
		prueba.a�adirMuelle(a�adir);
		GPSCoordinate coordenadaDistancia=null;
		double distancia=4000.0;
		prueba.muellesCerca(coordenadaDistancia, distancia);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void muellesCercaDistanciaNoValida() throws Exception {
		String identidad="ES-MAD";
		Puerto prueba=new Puerto(identidad);
		GPSCoordinate coordenada=new GPSCoordinate(40.5,40.5);
		Muelle a�adir=new Muelle(12,coordenada,'O',50);
		prueba.a�adirMuelle(a�adir);
		GPSCoordinate coordenadaDistancia=new GPSCoordinate(40.0,40.0);
		double distancia=-4000.0;
		prueba.muellesCerca(coordenadaDistancia, distancia);
	}
	
	
	
}

